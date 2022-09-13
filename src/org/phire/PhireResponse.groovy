
/*phire pkg  */

package org.phire

import groovy.transform.Field
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper


class PhireResponse {
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



    def PhireResponse (phiDomainId, phiCrType, phiCrNum, tktStatus, phiAssignee, phiMigrType, phiRespTxt, phiHasError, phiErrorTxt)
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


    def PhireResponse ()
    {}    
}