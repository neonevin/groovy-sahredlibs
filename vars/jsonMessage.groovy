import groovy.transform.Field
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import jenkins.plugins.http_request


class crNextStep {
    def CR_TASK_NEXT_STEP_REQ =  [PHI_DOMAIN_ID: "", PHI_CR_TYPE: "", PHI_CR_NUM: "", DEL_JIRA_STATUS:"",  PHI_ASSIGN_TO:"", PHI_MIGR_TYPE:""]

    crNextStep(phi_domain_id, phi_cr_type, phi_cr_num, del_jira_status,phi_assign_to,phi_migr_type) {
        this.CR_TASK_NEXT_STEP_REQ.PHI_DOMAIN_ID=phi_domain_id
        this.CR_TASK_NEXT_STEP_REQ.PHI_CR_TYPE=phi_cr_type
        this.CR_TASK_NEXT_STEP_REQ.PHI_CR_NUM=phi_cr_num
        this.CR_TASK_NEXT_STEP_REQ.DEL_JIRA_STATUS=del_jira_status
        this.CR_TASK_NEXT_STEP_REQ.PHI_ASSIGN_TO=phi_assign_to
        this.CR_TASK_NEXT_STEP_REQ.PHI_MIGR_TYPE=phi_migr_type
    }
}

@NonCPS
def buildMessage() {
    def cr_next = new crNextStep('HR','CR000012','TEST','DTTLNARESH','','' )
    def builder = new JsonBuilder(cr_next)

}

def sendMessage(){
    def response = httpRequest "http://httpbin.org/response-headers?param1=hello"
    println('Status: '+response.status)
    println('Response: '+response.content)
}
def call() {
    JsonBuilder builder=buildMessage()
    println builder
    println ""
    println builder.toPrettyString()
    sendMessage()
}

