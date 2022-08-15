import groovy.transform.Field
import groovy.json.JsonBuilder
import groovy.json.JsonOutput

def jsonParseData(jsonObj) {
    def slurObj=new groovy.json.JsonSlurper().parseText(jsonObj)
    return slurObj
}

def buildMessage() {
    JsonBuilder builder = new JsonBuilder()
    builder.CR_TASK_NEXT_STEP_REQ {
        CR_TASK_NEXT_STEP_REQ{
            PHI_DOMAIN_ID "HR"
            PHI_CR_NUM "CR000012"
            DEL_JIRA_STATUS "TEST"
            PHI_ASSIGN_TO "DTTLNARESH"
        }

    }

}

dev call() {
    JsonBuilder builder=buildMessage()

println "JSONBuilder Object : " + builder
println ""
println "JSON Pretty Printed Config "
println "=========================="
println JsonOutput.prettyPrint(builder.toString())
println ""

}
