import groovy.transform.Field
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
/*import org.wslite.rest.RESTClient
import org.wslite.http.auth.*
*/
import org.phire.*

/*
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
*/
//String call(String userid, String password, String phireTktId, String tktStatus, String tktAssignee, String tktAssigneeEmail, String phiMigrType, String tktId, String lastCmmt, String rqstUsr, String rqstEmail, String url) {
Map call(Map args) {

    def commReqObj = new ExtCommReq()
    // Load args  to request object
    commReqObj.args=args
    
    def commRspObj = new ExtCommRsp()
    //def map = buildRspMsg(args)
    //commRspObj.buildRspMsg(map)
    commRspObj.buildRspMsg(buildRspMsg(args))
    //commReqObj.fixNull();
/*
    // for req
    // Load args  to Json 
    def builderReq = new JsonBuilder(commReqObj)
    println "Json builder - comm req obj"
    println builderReq.toString()
    println "comm req obj"
    println commReqObj.DTTL_PHI_API_POST_REQ.toString()
    println commReqObj.DTTL_PHI_API_POST_REQ.DTTL_ASSIGN_EMAIL.toString()
    
    // for response
    def builderRsp = new JsonBuilder(commRspObj)
    //println "print args" + commReqObj.args.toString()
    println "Json builder - comm rsp obj"
    println builderRsp.toString()
*/
    println "comm rsp obj"
    println commRspObj.DTTL_PHI_API_POST_RES.toString()
    //println commRspObj.DTTL_PHI_API_POST_RES.DTTL_ASSIGN_EMAIL.toString()

    /*
    if ((phireTktId) && (phireTktId.indexOf("-") != -1)) {
        phiDomainId = phireTktId.split('-')[0]
        phireId = phireTktId.split('-')[1]
    } else {
        phiDomainId=""
        phireId=""
    }
    
    //                          (phiDomainId, phiCrNum, tktStatus, phiAssignee, assigneeEmail, phiMigrType, ticketId, rqstUsr, rqstEmail) 
    def cr_next = new PhireActReq(phiDomainId, phireId, tktStatus, tktAssignee, tktAssigneeEmail, phiMigrType, tktId, rqstUsr, rqstEmail)
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
    */
    return commReqObj.args
}

Map buildRspMsg(Map args)
{
    Map msg = args
    //println "in fnction" + args.toString()
    msg.put("upd_status","Y")
    msg.put("comment", "Success")
    msg.put("error", "N")
    //println "in fnction Map" + msg.toString()
    return msg
}
//call('env.USERNAME', 'env.USERPASS', 'PHIRE_NUM', 'TICKET_STATUS', 'ISSUE_ASSIGNEE', 'S', 'TICKET_ID', 'LAST_COMMENT_BODY', 'REQUEST_USER', 'http://140.238.207.38:8000//PSIGW/RESTListeningConnector/PSFT_HR/DEL_API_CR_NEXT_STEP.v1/')

//def Map m1=call (DTTL_TICKET_ID:"ps01-1",DTTL_SOURCE_SYSTEM:"Phi",PHI_DOMAIN_ID:"HR2",PHI_CR_NUM:"cr00002",DTTL_EXT_UPDATE:"Y", PHI_ASSIGN_TO:"PHO_DEV",DTTL_ASSIGN_EMAIL:"123@a.com", DTTL_RES_COMMENTS:"comment")
//def Map m1=call (DTTL_TICKET_ID:"ps01-1",DTTL_SOURCE_SYSTEM:"Phi",PHI_DOMAIN_ID:"HR2",PHI_CR_NUM:"cr00002",DTTL_EXT_UPDATE:"Y", PHI_ASSIGN_TO:"PHO_DEV",EMAIL:"123@a.com")
def Map m1=call(ticketid:"PS01-16", phire_domain:"HR2", phire_cr:"cr00002", upd_ext:"Y", assignee:"tester",assignee_email:"tester@123.com", comment:"test done" )
