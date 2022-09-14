
/*phire pkg  */

package org.phire

import groovy.transform.Field


class PhireActReq {
    def CR_TASK_NEXT_STEP_REQ =  [PHI_DOMAIN_ID: "", PHI_CR_NUM: "", DTTL_TICKET_STATUS:"",  PHI_ASSIGN_TO:"", DTTL_ASSIGN_EMAIL:"", PHI_MIGR_TYPE:"", DTTL_TICKET_ID:"", DTTL_REQUESTER_ID:"", DTTL_REQUEST_EMAIL:""]
    //def CR_TASK_NEXT_STEP_REQ =  [PHI_DOMAIN_ID: "", PHI_CR_NUM: "", DEL_JIRA_STATUS:"",  PHI_ASSIGN_TO:"", PHI_MIGR_TYPE:"",DTTL_TICKET_ID:"",DTTL_REQUESTER_ID:""]
    PhireActReq(phiDomainId, phiCrNum, tktStatus, phiAssignee, assigneeEmail, phiMigrType, ticketId, rqstUsr, rqstEmail) {
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
            this.CR_TASK_NEXT_STEP_REQ.DTTL_TICKET_STATUS=tktStatus
        } else {
            this.CR_TASK_NEXT_STEP_REQ.DTTL_TICKET_STATUS=" "
        }
        if (phiAssignee) {
            this.CR_TASK_NEXT_STEP_REQ.PHI_ASSIGN_TO=phiAssignee
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

