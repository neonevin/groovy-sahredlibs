/* Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wslite.rest

import groovy.xml.*
import groovy.json.*
import org.wslite.http.*
import org.wslite.rest.multipart.BodyPart

class ContentBuilder {

    private static final byte[] LINE_SEPARATOR = [13, 10]
    private static final byte[] BOUNDARY_PREFIX = [45, 45]
    byte[] data

    private String contentType
    private String charset
    private String boundary

    private ContentType dataContentType

    private Closure xmlContentClosure
    private List<BodyPart> multipartData

    ContentBuilder(String defaultContentType, String defaultCharset) {
        contentType = defaultContentType
        charset = defaultCharset
    }

    ContentBuilder build(Closure content) {
        Closure c = content.clone()
        c.resolveStrategy = Closure.DELEGATE_FIRST
        c.delegate = this
        c.call()
        c.delegate = content.delegate
        // We defer the processing of the xml closure because we first have to reset the parent closure delegate.
        // If we do not reset the delegate then this object's methods will be called instead of methods/properties
        // of the calling object.
        if (!data && xmlContentClosure) {
            data = closureToXmlString(xmlContentClosure).getBytes(getCharset())
        }
        if (!data && multipartData) {
            data = buildMultipartRequest(multipartData)
        }
        return this
    }

    @NonCPS void type(contentType) {
        this.contentType = contentType?.toString()
    }

    @NonCPS void charset(charset) {
        this.charset = charset?.toString()
    }

    @NonCPS void bytes(content) {
        dataContentType = ContentType.BINARY
        data = content
    }

    @NonCPS void text(content) {
        dataContentType = ContentType.TEXT
        data = content?.toString()?.getBytes(getCharset())
    }

    @NonCPS void urlenc(Map content) {
        dataContentType = ContentType.URLENC
        data = new URLParametersCodec().encode(content)?.getBytes(getCharset())
    }

   @NonCPS  void multipart(String name, byte[] content) {
        multipart(name, content, null, null)
    }

    @NonCPS void multipart(String name, byte[] content, String contentType) {
        multipart(name, content, contentType, null)
    }

    @NonCPS void multipart(String name, byte[] content, String contentType, String filename) {
        dataContentType = ContentType.MULTIPART
        multipartData = multipartData ?: []
        multipartData << new BodyPart(name: name, content: content, contentType: contentType, filename: filename)
    }

    @NonCPS void xml(Closure content) {
        dataContentType = ContentType.XML
        xmlContentClosure = content
    }

    @NonCPS void json() {
        dataContentType = ContentType.JSON
        data = null
    }

   @NonCPS  void json(Map content) {
        dataContentType = ContentType.JSON
        data = JsonOutput.toJson(content)?.getBytes(getCharset())
    }

    @NonCPS void json(List content) {
        dataContentType = ContentType.JSON
        data = JsonOutput.toJson(content)?.getBytes(getCharset())
    }

    @NonCPS String getCharset() {
        return charset ?: HTTP.DEFAULT_CHARSET
    }

    @NonCPS String getContentTypeHeader() {
        ContentTypeHeader contentTypeHeader = new ContentTypeHeader(getContentType())
        if (boundary) {
            return contentTypeHeader.mediaType + '; boundary=' + boundary
        }
        else if (!contentTypeHeader.charset) {
            return contentTypeHeader.mediaType + '; charset=' + getCharset()
        }
        return contentTypeHeader.contentType
    }

    @NonCPS private String getContentType() {
        // Ignore defaultContentType if request includes multipart data
        if (multipartData && ContentType.MULTIPART.equals(dataContentType)) {
            return dataContentType.toString()
        }
        return contentType ?: dataContentType.toString()
    }

    @NonCPS private String closureToXmlString(content) {
        return XmlUtil.serialize(new StreamingMarkupBuilder().bind(content))
    }

    @NonCPS private byte[] buildMultipartRequest(content) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream()

        boundary = ('-' * 4) + 'groovy-wslite-' + (UUID.randomUUID())
        dataContentType = ContentType.MULTIPART

        multipartData.each { BodyPart part ->

            baos <<  BOUNDARY_PREFIX
            baos <<  boundary.bytes
            baos <<  LINE_SEPARATOR
            baos <<  "Content-Disposition: form-data; name=\"${part.name}\"".toString().bytes
            if (part.filename) {
                baos << "; filename=\"${part.filename}\"".toString().bytes
                if (!part.contentType) {
                    baos <<  LINE_SEPARATOR
                    baos <<  "Content-Type: application/octet-stream".toString().bytes
                }
            }
            if (part.contentType) {
                baos << LINE_SEPARATOR
                baos << "Content-Type: ${part.contentType}".toString().bytes
            }
            baos <<  LINE_SEPARATOR
            baos <<  LINE_SEPARATOR
            baos <<  part.content
            baos <<  LINE_SEPARATOR
        }

        baos <<  BOUNDARY_PREFIX
        baos <<  boundary.bytes
        baos <<  BOUNDARY_PREFIX
        baos <<  LINE_SEPARATOR

        return baos.toByteArray()

    }

}
