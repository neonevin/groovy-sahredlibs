import groovy.transform.Field
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
//import org.wslite.rest.*
//@Grab('com.github.groovy-wslite:groovy-wslite:1.1.3')
import org.wslite.rest.RESTClient
import org.wslite.http.auth.*

class crNextStep {
    def CR_TASK_NEXT_STEP_REQ =  [PHI_DOMAIN_ID: "", PHI_CR_TYPE: "", PHI_CR_NUM: "", DEL_JIRA_STATUS:"",  PHI_ASSIGN_TO:"", DTTL_CHANGE_ASSIGN:"",PHI_MIGR_TYPE:"", DTTL_TICKET_ID:"",DTTL_REQUESTER_ID:""]

    crNextStep(phi_domain_id, phi_cr_type, phi_cr_num, del_jira_status,phi_assign_to,dttl_chg_assgn,phi_migr_type,dttl_ticket_id,dttl_reqstr_id) {
        this.CR_TASK_NEXT_STEP_REQ.PHI_DOMAIN_ID=phi_domain_id
        this.CR_TASK_NEXT_STEP_REQ.PHI_CR_TYPE=phi_cr_type
        this.CR_TASK_NEXT_STEP_REQ.PHI_CR_NUM=phi_cr_num
        this.CR_TASK_NEXT_STEP_REQ.DEL_JIRA_STATUS=del_jira_status
        this.CR_TASK_NEXT_STEP_REQ.PHI_ASSIGN_TO=phi_assign_to
        this.DTTL_CHANGE_ASSIGN=dttl_chg_assgn
        this.CR_TASK_NEXT_STEP_REQ.PHI_MIGR_TYPE=phi_migr_type
        this.DTTL_TICKET_ID=dttl_ticket_id
        this.DTTL_REQUESTER_ID=dttl_reqstr_id
    }
}

@NonCPS
def buildMessage() {
    //def cr_next = new crNextStep('HR','MIGR','CR000014','TEST','DTTLNARESH','N','S','PS02-1','nprasannan' )
    def builder = new JsonBuilder(cr_next)

}

@NonCPS
def sendMessage(JsonBuilder jsonMsg,String userid, String password){
     println "in send message 1"

    def client = new RESTClient("http://140.238.207.38:8000/PSIGW/RESTListeningConnector/PSFT_HR/DEL_API_CR_NEXT_STEP.v1/")
    try{
        client.authorization = new HTTPBasicAuthorization(userid, password)
    }catch (Exception e){
        println "auth fail"
    }
    println "in send message 2"
    def response = client.post() {
        type "application/json"  // String or ContentType
        charset "US-ASCII"
        text(jsonMsg.toString())
    }
    println jsonMsg.toString()
    println "HTTP post complete"
    println response.url
    println response.request
    println response.json
    println response.text
}

//def call(String userid, String password) {
def call(script) {
    //JsonBuilder builder=buildMessage()
    JsonBuilder builder=readEnv(script)
    println ""
    println builder.toString()

    //sendMessage(builder,userid,password)
}

/*def jenkinsHttpGet(Map args) {
    def response = args.jenkinsWorkflowScript.invokeMethod 'httpRequest', [[args.url: url]] as Object[]
    if (response.status != 200) {
        jenkinsWorkflowScript.invokeMethod 'echo', [response.content] as Object[]
        throw new HttpResponseException(response.status, 'HTTP error')
    }
    response.content
}*/
@NonCPS
def readEnv(script){
    println $jstatus 
    println $juser
    println $JIRA_ISSUE_KEY

    def cr_next = new crNextStep('HR','MIGR','CR000014','TEST','DTTLNARESH','N','S','PS02-1','nprasannan' )
    def builder = new JsonBuilder(cr_next)
}

public String getGroovyVersion() {
        try {
                return org.codehaus.groovy.runtime.InvokerHelper.version
        }
        catch (Throwable ignore) { }
        return GroovySystem.version
}

call('usr', 'pwd')

