
/*phire pkg  */

package org.phire

import groovy.transform.Field
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper


class phireResponse {

    def CR_TASK_NEXT_STEP_RSP=  [
        PHI_DOMAIN_ID: "", 
        PHI_CR_TYPE: "", 
        PHI_CR_NUM: "",
        DTTL_TICKET_STATUS:"",  
        PHI_ASSIGN_TO: "", 
        PHI_MIGR_TYPE: "",
        DTTL_PHI_RESP_TEXT: "", 
        PHI_HAS_ERROR: true, 
        PHI_ERROR_TEXT:""
    ]


    //@NonCPS
    def phireResponse (phiDomainId, phiCrType, phiCrNum, tktStatus, phiAssignee, phiMigrType, phiRespTxt, phiHasError, phiErrorTxt)
    {
        if (phiDomainId){
            this.CR_TASK_NEXT_STEP_RSP.PHI_DOMAIN_ID=phiDomainId
        } else{
            this.CR_TASK_NEXT_STEP_RSP.PHI_DOMAIN_ID=" "
        }
        if (phiCrType){
            this.CR_TASK_NEXT_STEP_RSP.PHI_CR_TYPE=phiCrType
        } else{
            this.CR_TASK_NEXT_STEP_RSP.PHI_CR_TYPE=" "
        }
        if (phiCrNum){
            this.CR_TASK_NEXT_STEP_RSP.PHI_CR_NUM=phiCrNum
        } else{
            this.CR_TASK_NEXT_STEP_RSP.PHI_CR_NUM=" "
        }
        if (tktStatus){
            this.CR_TASK_NEXT_STEP_RSP.DTTL_TICKET_STATUS=tktStatus
        } else{
            this.CR_TASK_NEXT_STEP_RSP.DTTL_TICKET_STATUS=" "
        }
        if (phiAssignee){
            this.CR_TASK_NEXT_STEP_RSP.PHI_ASSIGN_TO=phiAssignee
        } else{
            this.CR_TASK_NEXT_STEP_RSP.PHI_ASSIGN_TO=" "
        }
        if (phiMigrType){
            this.CR_TASK_NEXT_STEP_RSP.PHI_MIGR_TYPE=phiMigrType
        } else{
            this.CR_TASK_NEXT_STEP_RSP.PHI_MIGR_TYPE=" "
        }
        if (phiRespTxt){
            this.CR_TASK_NEXT_STEP_RSP.DTTL_PHI_RESP_TEXT=phiRespTxt
        } else{
            this.CR_TASK_NEXT_STEP_RSP.DTTL_PHI_RESP_TEXT=" "
        }
        if (phiHasError){
            this.CR_TASK_NEXT_STEP_RSP.PHI_HAS_ERROR=phiHasError
        } else{
            this.CR_TASK_NEXT_STEP_RSP.PHI_HAS_ERROR=true
        }
        if (phiErrorTxt){
            this.CR_TASK_NEXT_STEP_RSP.PHI_ERROR_TEXT=phiErrorTxt
        } else{
            this.CR_TASK_NEXT_STEP_RSP.PHI_ERROR_TEXT=true
        }    
    }

    def phireResponse ()
    {}    
    String getText() 
    {
        
    }

}

def testMessage()
{
   /*def json_rsp='''{
        "CR_TASK_NEXT_STEP_RSP":{
            "PHI_DOMAIN_ID":"HR2",
            "PHI_CR_TYPE":"MIGR",
            "PHI_CR_NUM":"CR000025",
            "DTTL_TICKET_STATUS":"SIT",
            "PHI_ASSIGN_TO":"62f058135111209f4fdfdc71",
            "PHI_MIGR_TYPE":"S",
            "DTTL_PHI_RESP_TEXT":"'' for the domain 'HR2' and CR# 'CR000025' is completed ",
            "PHI_HAS_ERROR":true,
            "PHI_ERROR_TEXT":[
                "The current task for  CR # CR000025 is not a migration task with a target of . Please check the status in Phire. (31000,3)"
            ]
        }
    }'''*/
   def json_rsp='''{
            "PHI_DOMAIN_ID":"HR2",
            "PHI_CR_TYPE":"MIGR",
            "PHI_CR_NUM":"CR000025",
            "DTTL_TICKET_STATUS":"SIT",
            "PHI_ASSIGN_TO":"62f058135111209f4fdfdc71",
            "PHI_MIGR_TYPE":"S",
            "DTTL_PHI_RESP_TEXT":"'' for the domain 'HR2' and CR# 'CR000025' is completed ",
            "PHI_HAS_ERROR":true,
            "PHI_ERROR_TEXT":[
                "The current task for  CR # CR000025 is not a migration task with a target of . Please check the status in Phire. (31000,3)"
            ]
    }'''    
    def jsonSlurper = new JsonSlurper()
    cfg = jsonSlurper.parseText(json_rsp)
   return cfg

}

def sendtestMessage()
{
    //println testMessage()
    
    def json = new groovy.json.JsonBuilder()
    json CR_TASK_NEXT_STEP_RSP:  testMessage()

    println json.toPrettyString()
        // Json String to Map
        def personMap = new JsonSlurper().parseText(json.toPrettyString())
        // using Map to convert to Person object type
       // def newPhiResp = new phireResponse(personMap)    
    def newPhiResp = new JsonSlurper().parseText(json.toPrettyString()) as phireResponse
    println "print object"
    println (newPhiResp.toString())
    println newPhiResp.CR_TASK_NEXT_STEP_RSP.DTTL_PHI_RESP_TEXT
    println newPhiResp.CR_TASK_NEXT_STEP_RSP.PHI_ERROR_TEXT
    /*Map json_rsp= testMessage()
    def jsonPhire = new JsonSlurper().parseText(json_rsp) as phireResponse
    println jsonPhire.CR_TASK_NEXT_STEP_RSP
    */
}

sendtestMessage()

def readResponse(rspJson)
{
    def json = new groovy.json.JsonBuilder()
}