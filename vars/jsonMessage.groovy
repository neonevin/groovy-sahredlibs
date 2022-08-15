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
test '13'
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
