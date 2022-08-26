import groovy.transform.Field
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
//import org.wslite.rest.*
//@Grab('com.github.groovy-wslite:groovy-wslite:1.1.3')
import org.wslite.rest.RESTClient
import org.wslite.http.auth.*

class crNextStep {
    //def CR_TASK_NEXT_STEP_REQ =  [PHI_DOMAIN_ID: "", PHI_CR_NUM: "", DTTL_TICKET_STATUS:"",  PHI_ASSIGN_TO:"", DTTL_CHANGE_ASSIGN:"",PHI_MIGR_TYPE:"", DTTL_TICKET_ID:"",DTTL_REQUESTER_ID:"", PHI_CR_TYPE: "",]
    def CR_TASK_NEXT_STEP_REQ =  [PHI_DOMAIN_ID: "", PHI_CR_NUM: "", DEL_JIRA_STATUS:"",  PHI_ASSIGN_TO:"", PHI_MIGR_TYPE:"",DTTL_TICKET_ID:"",DTTL_REQUESTER_ID:""]
    crNextStep(phiDomainId, phiCrNum, tktStatus, phiAssignee, phiMigrType, ticketId, rqstUsr) {
        if (phiDomainId){
            this.CR_TASK_NEXT_STEP_REQ.PHI_DOMAIN_ID=phiDomainId
        } else{
            this.CR_TASK_NEXT_STEP_REQ.PHI_DOMAIN_ID=" "
        }
        if (phiCrNum) {
            this.CR_TASK_NEXT_STEP_REQ.PHI_CR_NUM=phiCrNum
        } else {
            this.CR_TASK_NEXT_STEP_REQ.PHI_CR_NUM=" "
        }
        if (tktStatus){
            this.CR_TASK_NEXT_STEP_REQ.DEL_JIRA_STATUS=tktStatus
        } else {
            this.CR_TASK_NEXT_STEP_REQ.DEL_JIRA_STATUS=" "
        }
        if (phiAssignee) {
            this.CR_TASK_NEXT_STEP_REQ.PHI_ASSIGN_TO=phiAssignee
        } else {
            this.CR_TASK_NEXT_STEP_REQ.PHI_ASSIGN_TO=" "
        }
        if (phiMigrType) {
            this.CR_TASK_NEXT_STEP_REQ.PHI_MIGR_TYPE=phiMigrType
        } else {
            this.CR_TASK_NEXT_STEP_REQ.PHI_MIGR_TYPE='S'
        }
        if (ticketId) {
            this.CR_TASK_NEXT_STEP_REQ.DTTL_TICKET_ID=ticketId
        } else {
            this.CR_TASK_NEXT_STEP_REQ.DTTL_TICKET_ID=" "
        }
        if (rqstUsr) {
            this.CR_TASK_NEXT_STEP_REQ.DTTL_REQUESTER_ID=rqstUsr
        } else {
            this.CR_TASK_NEXT_STEP_REQ.DTTL_REQUESTER_ID=" "
        }
    }
}

@NonCPS
def buildMessage() {
    //def cr_next = new crNextStep('HR','MIGR','CR000014','TEST','DTTLNARESH','N','S','PS02-1','nprasannan' )
    def builder = new JsonBuilder(cr_next)

}

@NonCPS
def sendMessage(JsonBuilder jsonMsg,String userid, String password, String url){
     println "in send message 1"

    //def client = new RESTClient("http://140.238.207.38:8000/PSIGW/RESTListeningConnector/PSFT_HR/DEL_API_CR_NEXT_STEP.v1/")
    def client = new RESTClient(url)
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

def call(String userid, String password, String phireTktId, String tktStatus, String tktAssignee, String phiMigrType, String tktId, String lastCmmt, String rqstUsr,  String url) {
//def call(String phireid) {
    //JsonBuilder builder=buildMessage()
    if ((phireTktId) && (phireTktId.indexOf("-") != -1)) {
        
        phiDomainId = phireTktId.split('-')[0]
        phireId = phireTktId.split('-')[1]
    } else {
        phiDomainId=""
        phireId=""
    }
    
                    //crNextStep(phiDomainId, phiCrNum, tktStatus, phiAssignee, phiMigrType, ticketId, reqstrId)
    def cr_next = new crNextStep(phiDomainId, phireId, tktStatus, tktAssignee, phiMigrType, tktId, rqstUsr)
    def builder = new JsonBuilder(cr_next)
    println builder.toString()

    //JsonBuilder builder=readEnv(phireid)
    println "Calling send Message"
    sendMessage(builder, userid, password, url)
    //println builder.toString()

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
def readEnv(String phireid){
    //println ${script.env.jstatus} 
    //println ${script.env.juser}
    //println ${script.env.JIRA_ISSUE_KEY}
    println "readenv " phireid
    def cr_next = new crNextStep('HR','MIGR','CR000014','TEST','DTTLNARESH','N','S',phireid,'nprasannan' )
    def builder = new JsonBuilder(cr_next)
    println builder.toString()
}


public String getGroovyVersion() {
        try {
                return org.codehaus.groovy.runtime.InvokerHelper.version
        }
        catch (Throwable ignore) { }
        return GroovySystem.version
}

call('env.USERNAME', 'env.USERPASS', 'PHIRE_NUM', 'TICKET_STATUS', 'ISSUE_ASSIGNEE', 'S', 'TICKET_ID', 'LAST_COMMENT_BODY', 'REQUEST_USER', 'http://140.238.207.38:8000//PSIGW/RESTListeningConnector/PSFT_HR/DEL_API_CR_NEXT_STEP.v1/')

