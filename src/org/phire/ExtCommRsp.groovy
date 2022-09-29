
package org.phire

import groovy.transform.Field


class ExtCommRsp {
    def DTTL_PHI_API_POST_RES =  [
        DTTL_TICKET_ID: "", 
        PHI_DOMAIN_ID: "", 
        PHI_CR_NUM:"",  
        DTTL_TASK_STATUS: "", 
        DTTL_RES_COMMENTS: "",
        PHI_HAS_ERROR: ""
    ]



    def ExtCommRsp ()
    {}

    def buildRspMsg(Map msgMap)    
    {
        //println "in class fnction" + msgMap.toString()
        this.DTTL_PHI_API_POST_RES.DTTL_TICKET_ID = msgMap.ticketid ?: 'JIRADEMO-01'
        this.DTTL_PHI_API_POST_RES.PHI_DOMAIN_ID = msgMap.phire_domain ?: ' '
        this.DTTL_PHI_API_POST_RES.PHI_CR_NUM = msgMap.phire_cr ?: ' '
        this.DTTL_PHI_API_POST_RES.DTTL_TASK_STATUS = msgMap.upd_status ?: 'N'
        this.DTTL_PHI_API_POST_RES.DTTL_RES_COMMENTS = msgMap.comment ?: 'None'        
        this.DTTL_PHI_API_POST_RES.PHI_HAS_ERROR = msgMap.error ?: 'N/A'
    }
}