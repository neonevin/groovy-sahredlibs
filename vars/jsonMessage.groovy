import groovy.transform.Field
import groovy.json.JsonBuilder
import groovy.json.JsonOutput

def jsonParseData(jsonObj) {
    def slurObj=new groovy.json.JsonSlurper().parseText(jsonObj)
    return slurObj
}

@NonCPS
def buildMessage() {
def builder = new JsonBuilder()
builder.book {
    title 'Head First Java'
    publisher 'Orielly'
    author 'Kathy Sierra', 'Bert Bates'
    year '2005'
    currency 'USD'
    price 44.95
    format 'pdf', 'print'
}
}

def call() {
    def builder=buildMessage()

//println builder
//println ""
//println builder.toPrettyString()

}
