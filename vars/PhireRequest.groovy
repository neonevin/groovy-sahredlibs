import groovy.transform.Field
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.wslite.rest.RESTClient
import org.wslite.http.auth.*
import org.phire.*

@NonCPS
def buildMessage() {
    //def cr_next = new crNextStep('HR','MIGR','CR000014','TEST','DTTLNARESH','N','S','PS02-1','nprasannan' )
    def builder = new JsonBuilder(cr_next)

}

@NonCPS
def sendMessage(JsonBuilder jsonMsg,String userid, String password, String url){

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
    return response.json
}

String call(String userid, String password, String phireTktId, String ticketPrjId, String ticketAppId, String ticketType, String tktStatus, String tktAssignee, String tktAssigneeEmail, String phiMigrType, String tktId, String lastCmmt, String phiTitle, String rqstUsr, String rqstEmail, String url) {
    if ((phireTktId) && (phireTktId.indexOf("-") != -1)) {
        phiDomainId = phireTktId.split('-')[0]
        phireId = phireTktId.split('-')[1]
    } else {
        phiDomainId=""
        phireId=""
    }
    
    //                          (phiDomainId, phiCrNum, tktStatus, phiAssignee, assigneeEmail, phiMigrType, ticketId, rqstUsr, rqstEmail) 
   //        (phiDomainId, phiCrNum, ticketPrjId, ticketAppId, ticketType, tktStatus, tktAssignee, assigneeEmail, phiMigrType, ticketId, phiTitle, rqstUsr, rqstEmail) {
    def cr_next = new PhireActReq(phiDomainId, phireId, ticketPrjId, ticketAppId, ticketType, tktStatus, tktAssignee, tktAssigneeEmail, phiMigrType, tktId, phiTitle, rqstUsr, rqstEmail)
    def builder = new JsonBuilder(cr_next)
    println builder.toString()
    println "Calling send Message"
    def respObj= new PhireActRsp()
    respObj=sendMessage(builder, userid, password, url) 
    // Load response.json  to PhireResponse 
    def builderRsp = new JsonBuilder(respObj)
    //println "Json builder - response obj"
    //println builderRsp.toString()
    
    String jiraComment=""

    if (respObj.CR_TASK_NEXT_STEP_RSP.PHI_HAS_ERROR) {
       jiraComment =  respObj.CR_TASK_NEXT_STEP_RSP.PHI_ERROR_TEXT
    }
    else {
        jiraComment =  respObj.CR_TASK_NEXT_STEP_RSP.DTTL_PHI_RESP_TEXT
    }
    //def response = args.jenkinsWorkflowScript.invokeMethod 'jiraAddComment', [comment: jiraComment, idOrKey: tktId, site: 'JiraSSL']
    return jiraComment
}

//call('env.USERNAME', 'env.USERPASS', 'PHIRE_NUM', 'TICKET_STATUS', 'ISSUE_ASSIGNEE', 'S', 'TICKET_ID', 'LAST_COMMENT_BODY', 'REQUEST_USER', 'http://140.238.207.38:8000//PSIGW/RESTListeningConnector/PSFT_HR/DEL_API_CR_NEXT_STEP.v1/')

