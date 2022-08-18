import groovy.transform.Field
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
//import org.wslite.rest
//@Grab('com.github.groovy-wslite:groovy-wslite:1.1.3')
//import wslite.rest.RESTClient
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

//@NonCPS
def buildMessage() {
    def cr_next = new crNextStep('HR','MIGR','CR000014','TEST','DTTLNARESH','S' )
    def builder = new JsonBuilder(cr_next)

}

def sendMessage(JsonBuilder jsonMsg){

    //RESTClient client = new RESTClient("http://localhost:8080")
  /*  try{
        def rest = new RESTClient('curl https://typedwebhook.tools/webhook/6164fde1-da82-4970-a481-6edf3215b546')
       // String token = 'mytoken'
        def response = rest.post(
        path: 'services/user',
        headers: [Authorization:"Bearer ${token}"],
        body: [name:"josdem",email:"joseluis.delacruz@gmail.com"],
        requestContentType: 'application/json' )
        response.responseData
    } catch(Exception ex) {
        log.warn "Error: ${ex.message}"
    }*/
/*
    def client = new RESTClient("http://api.twitter.com/1/")
    def response = client.get(path:'/users/show.json', query:[screen_name:'jwagenleitner', include_entities:true])

    assert 200 == response.statusCode
    assert "John Wagenleitner" == response.json.name

    def client2 = new RESTClient("https://typedwebhook.tools/webhook/6164fde1-da82-4970-a481-6edf3215b546")
    def response2 = client2.get(path:'/users/show.json', query:[screen_name:'jwagenleitner', include_entities:true])

    assert 200 == response2.statusCode
    
    //assert "John Wagenleitner" == response.json.name
*/
    //def CR_TASK_NEXT_STEP_REQ =[ 'PHI_DOMAIN_ID': "", 'PHI_CR_TYPE': "", 'PHI_CR_NUM': "", 'DEL_JIRA_STATUS':"",  'PHI_ASSIGN_TO':"", 'PHI_MIGR_TYPE':""]
    //def client = new RESTClient("https://typedwebhook.tools/webhook/b802d188-fce9-438a-a51b-d426fff33216")
    def client = new RESTClient("http://140.238.207.38:8000//PSIGW/RESTListeningConnector/PSFT_HR/DEL_API_CR_NEXT_STEP.v1/")
    client.authorization = new HTTPBasicAuthorization("username", "secret")
    def response = client.post() {
        type "application/json"  // String or ContentType
        charset "US-ASCII"

        // one of the following
        //json id:"525", department:"Finance"
        //json jsonMsg
        //json CR_TASK_NEXT_STEP_REQ
        //text(CR_TASK_NEXT_STEP_REQ)
        //text(groovy.json.JsonOutput.toJson(jsonMsg))
        text(jsonMsg.toString())
        println "after text ()"
        println jsonMsg.toString()
    }
    println "HTTP post complete"
    println response.url
    println response.request
    println response.json
    println response.text
}

def call() {
    JsonBuilder builder=buildMessage()
   // println builder
    println ""
    println builder.toString()
    //println json(builder.toString())
    
   // JsonSlurper slurper= new JsonSlurper().parseText(builder.toString())
    //sendMessage(groovy.json.JsonOutput.toJson(builder))

/* def json = new groovy.json.JsonBuilder()
 json name: "Guillaume", age: 33
println 'print jsonoutput'
println JsonOutput.toJson(json)
*/
println 'calling send message'

    sendMessage(builder)
    getGroovyVersion() 
}

public String getGroovyVersion() {
        try {
                return org.codehaus.groovy.runtime.InvokerHelper.version
        }
        catch (Throwable ignore) { }
        return GroovySystem.version
}

//println getGroovyVersion()
call()

