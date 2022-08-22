/* Copyright 2011 the original author or authors.
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
package org.wslite.http

import org.wslite.util.ObjectHelper

class HTTPRequest {

    URL url
    HTTPMethod method

    int connectTimeout = 0
    int readTimeout = 0
    boolean followRedirects = true
    boolean useCaches
    boolean sslTrustAllCerts
    String sslTrustStoreFile
    String sslTrustStorePassword

    Proxy proxy

    Map headers = new TreeMap(String.CASE_INSENSITIVE_ORDER)

    byte[] data = null

    boolean isConnectTimeoutSet
    boolean isReadTimeoutSet
    boolean isUseCachesSet
    boolean isFollowRedirectsSet
    boolean isSSLTrustAllCertsSet

    @NonCPS void setHeaders(Map map) {
        headers.putAll(map)
    }

    @NonCPS void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout
        isConnectTimeoutSet = true
    }

   @NonCPS  void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout
        isReadTimeoutSet = true
    }

    @NonCPS void setUseCaches(boolean useCaches) {
        this.useCaches = useCaches
        isUseCachesSet = true
    }

    @NonCPS void setFollowRedirects(boolean followRedirects) {
        this.followRedirects = followRedirects
        isFollowRedirectsSet = true
    }

    @NonCPS void setSslTrustAllCerts(boolean sslTrustAllCerts) {
        this.sslTrustAllCerts = sslTrustAllCerts
        isSSLTrustAllCertsSet = true
    }

    @NonCPS String getContentAsString() {
        if (!data) {
            return ''
        }
        return new String(data, getCharset())
    }

    @NonCPS private String getCharset() {
        return getContentTypeHeader().charset ?: HTTP.DEFAULT_CHARSET
    }

    @NonCPS private ContentTypeHeader getContentTypeHeader() {
        return new ContentTypeHeader(headers[HTTP.CONTENT_TYPE_HEADER])
    }

    @Override
    @NonCPS
    String toString() {
        def excludes = ['isConnectTimeoutSet', 'isReadTimeoutSet',
                        'isUseCachesSet', 'isFollowRedirectsSet',
                        'isSSLTrustAllCertsSet', 'sslTrustStorePassword',
                        'data', 'contentAsString', 'charset', 'contentTypeHeader']
        ObjectHelper.dump(this, exclude:excludes)
    }

}
