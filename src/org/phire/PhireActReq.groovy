
/*phire pkg  */

package org.phire

import groovy.transform.Field


class PhireActReq {
    def CR_TASK_NEXT_STEP_REQ =  [
        PHI_DOMAIN_ID: "", 
        PHI_CR_NUM: "",
        DTTL_PROJECT_ID:"" ,
        DTTL_APPLICATIONID:"",
        DTTL_TICKET_TYPE:"",
        DTTL_TICKET_STATUS:"", 
        PHI_ASSIGN_TO:"", 
        DTTL_ASSIGN_EMAIL:"", 
        PHI_MIGR_TYPE:"", 
        DTTL_TICKET_ID:"",
        PHI_TITLE:"", 
        DTTL_REQUESTER_ID:"", 
        DTTL_REQUEST_EMAIL:""]
    //def CR_TASK_NEXT_STEP_REQ =  [PHI_DOMAIN_ID: "", PHI_CR_NUM: "", DEL_JIRA_STATUS:"",  PHI_ASSIGN_TO:"", PHI_MIGR_TYPE:"",DTTL_TICKET_ID:"",DTTL_REQUESTER_ID:""]
    PhireActReq(phiDomainId, phiCrNum, ticketPrjId, ticketAppId, ticketType, tktStatus, tktAssignee, assigneeEmail, phiMigrType, ticketId, phiTitle, rqstUsr, rqstEmail) {
        if (phiDomainId){
            this.CR_TASK_NEXT_STEP_REQ.PHI_DOMAIN_ID=phiDomainId
        } else{
            this.CR_TASK_NEXT_STEP_REQ.PHI_DOMAIN_ID=" "
        }
        //NP -  15dec- test code - update condition to none from null
        if (phiCrNum="NONE") {
            this.CR_TASK_NEXT_STEP_REQ.PHI_CR_NUM=phiCrNum
        } else {
            this.CR_TASK_NEXT_STEP_REQ.PHI_CR_NUM=" "
        }
        if (ticketPrjId) {
            this.CR_TASK_NEXT_STEP_REQ.DTTL_PROJECT_ID=ticketPrjId
        } else {
            this.CR_TASK_NEXT_STEP_REQ.DTTL_PROJECT_ID=" "
        }
        if (ticketAppId) {
            this.CR_TASK_NEXT_STEP_REQ.DTTL_APPLICATIONID=ticketAppId
        } else {
            this.CR_TASK_NEXT_STEP_REQ.DTTL_APPLICATIONID=" "
        }
        if (ticketType) {
            this.CR_TASK_NEXT_STEP_REQ.DTTL_TICKET_TYPE=ticketType
        } else {
            this.CR_TASK_NEXT_STEP_REQ.DTTL_TICKET_TYPE=" "
        }
        if (tktStatus){
            this.CR_TASK_NEXT_STEP_REQ.DTTL_TICKET_STATUS=tktStatus
        } else {
            this.CR_TASK_NEXT_STEP_REQ.DTTL_TICKET_STATUS=" "
        }
        if (tktAssignee) {
            this.CR_TASK_NEXT_STEP_REQ.PHI_ASSIGN_TO=tktAssignee
        } else {
            this.CR_TASK_NEXT_STEP_REQ.PHI_ASSIGN_TO=" "
        }
        if (assigneeEmail) {
            this.CR_TASK_NEXT_STEP_REQ.DTTL_ASSIGN_EMAIL=assigneeEmail
        } else {
            this.CR_TASK_NEXT_STEP_REQ.DTTL_ASSIGN_EMAIL=" "
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
        if (phiTitle) {
            this.CR_TASK_NEXT_STEP_REQ.PHI_TITLE=phiTitle
        } else {
            this.CR_TASK_NEXT_STEP_REQ.PHI_TITLE=" "
        }
        if (rqstUsr) {
            this.CR_TASK_NEXT_STEP_REQ.DTTL_REQUESTER_ID=rqstUsr
        } else {
            this.CR_TASK_NEXT_STEP_REQ.DTTL_REQUESTER_ID=" "
        }
        if (rqstEmail) {
            this.CR_TASK_NEXT_STEP_REQ.DTTL_REQUEST_EMAIL=rqstEmail
        } else {
            this.CR_TASK_NEXT_STEP_REQ.DTTL_REQUEST_EMAIL=" "
        }
    }

    def PhireActReq()
    {}
}

