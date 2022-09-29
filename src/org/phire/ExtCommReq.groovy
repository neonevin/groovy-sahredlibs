
package org.phire

import groovy.transform.Field


class ExtCommReq {
    def DTTL_PHI_API_POST_REQ=  [
        DTTL_TICKET_ID: "", 
        DTTL_SOURCE_SYSTEM: "", 
        PHI_DOMAIN_ID: "",
        PHI_CR_NUM:"",  
        DTTL_EXT_UPDATE: "", 
        PHI_ASSIGN_TO: "",
        DTTL_ASSIGN_EMAIL: "test@123.com.au", 
        DTTL_RES_COMMENTS: ""
    ]
    def args =[
        ticketid:"",
        source:"",
        phire_domain:"",
        phire_cr:"",
        upd_ext:"",
        assignee:"",
        assignee_email:"",
        comment:""
    ]


    def ExtCommReq ()
    {}
    /*
    def fixNull()    
    {
        // Update null values with defaults
        this.DTTL_PHI_API_POST_REQ.DTTL_TICKET_ID = this.DTTL_PHI_API_POST_REQ.DTTL_TICKET_ID ?: ' '
        this.DTTL_PHI_API_POST_REQ.DTTL_SOURCE_SYSTEM = this.DTTL_PHI_API_POST_REQ.DTTL_SOURCE_SYSTEM ?: 'PHIRE'
        this.DTTL_PHI_API_POST_REQ.PHI_DOMAIN_ID = this.DTTL_PHI_API_POST_REQ.PHI_DOMAIN_ID ?: ' '
        this.DTTL_PHI_API_POST_REQ.PHI_CR_NUM = this.DTTL_PHI_API_POST_REQ.PHI_CR_NUM ?: ' '
        this.DTTL_PHI_API_POST_REQ.DTTL_EXT_UPDATE = this.DTTL_PHI_API_POST_REQ.DTTL_EXT_UPDATE ?: ' '
        this.DTTL_PHI_API_POST_REQ.PHI_ASSIGN_TO = this.DTTL_PHI_API_POST_REQ.PHI_ASSIGN_TO ?: ' '
        this.DTTL_PHI_API_POST_REQ.DTTL_ASSIGN_EMAIL = this.DTTL_PHI_API_POST_REQ.DTTL_ASSIGN_EMAIL ?: ' '
        this.DTTL_PHI_API_POST_REQ.DTTL_RES_COMMENTS = this.DTTL_PHI_API_POST_REQ.DTTL_RES_COMMENTS ?: ' '
    }
    */

    // load Msg Map from args, assign defaults when null
    @NonCPS
    def buildReqMsg()
    {
        this.DTTL_PHI_API_POST_REQ.DTTL_TICKET_ID = this.args.ticketid ?: 'JIRADEMO-01'
        this.DTTL_PHI_API_POST_REQ.DTTL_SOURCE_SYSTEM = this.args.source ?: 'PHIRE'
        this.DTTL_PHI_API_POST_REQ.PHI_DOMAIN_ID = this.args.phire_domain ?: ' '
        this.DTTL_PHI_API_POST_REQ.PHI_CR_NUM = this.args.phire_cr ?: ' '
        this.DTTL_PHI_API_POST_REQ.DTTL_EXT_UPDATE = this.args.upd_ext ?: 'N'
        this.DTTL_PHI_API_POST_REQ.PHI_ASSIGN_TO = this.args.PHI_ASSIGN_TO ?: 'N/A'
        this.DTTL_PHI_API_POST_REQ.DTTL_ASSIGN_EMAIL = this.args.assignee ?: 'N/A'
        this.DTTL_PHI_API_POST_REQ.DTTL_RES_COMMENTS = this.args.comment ?: 'None'
    }


}