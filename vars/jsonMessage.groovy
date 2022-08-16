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
builder.CR_TASK_NEXT_STEP_REQ {
/*    title 'Head First Java'
    publisher 'Orielly'
    author 'Kathy Sierra', 'Bert Bates'
    year '2005'
    currency 'USD'
    price 44.95
    format 'pdf', 'print'
    */
    PHI_DOMAIN_ID 'HR'
    PHI_CR_NUM 'CR000012'
    DEL_JIRA_STATUS 'TEST'
    PHI_ASSIGN_TO 'DTTLNARESH'
    }
}

def call() {
    JsonBuilder builder=buildMessage()
    println builder
    println ""
    println builder.toPrettyString()
}
/*
def buildMessage() {
    JsonBuilder builder = new JsonBuilder()
    builder.CR_TASK_NEXT_STEP_REQ {
            PHI_DOMAIN_ID 'HR'
            PHI_CR_NUM 'CR000012'
            DEL_JIRA_STATUS 'TEST'
            PHI_ASSIGN_TO 'DTTLNARESH'
    }

}
*/
