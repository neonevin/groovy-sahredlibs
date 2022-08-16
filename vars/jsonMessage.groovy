import groovy.transform.Field
import groovy.json.JsonBuilder
import groovy.json.JsonOutput

class book {
        String title 
    String publisher 
    String author 
    book(title, publisher, author) {
        this.title=title
        this.publisher=publisher
        this.author=author
    }
}

class CR_TASK_NEXT_STEP_REQ {
        String PHI_DOMAIN_ID 
    String PHI_CR_NUM 
    String DEL_JIRA_STATUS 
    String PHI_ASSIGN_TO 
    CR_TASK_NEXT_STEP_REQ(PHI_DOMAIN_ID, PHI_CR_NUM, DEL_JIRA_STATUS,PHI_ASSIGN_TO) {
        this.PHI_DOMAIN_ID=PHI_DOMAIN_ID
        this.PHI_CR_NUM=PHI_CR_NUM
        this.DEL_JIRA_STATUS=DEL_JIRA_STATUS
        this.PHI_ASSIGN_TO=PHI_ASSIGN_TO
    }
}
def jsonParseData(jsonObj) {
    def slurObj=new groovy.json.JsonSlurper().parseText(jsonObj)
    return slurObj
}

@NonCPS
def buildMessage() {
  //  def book1 = new book('Learn Java','Orielly','Kathy Sierra')
    def cr_next = new CR_TASK_NEXT_STEP_REQ('HR','CR000012','TEST','DTTLNARESH' )
//def builder = new JsonBuilder(book1)
def builder = new JsonBuilder(cr_next)
/*builder.CR_TASK_NEXT_STEP_REQ {
    'PHI_DOMAIN_ID' 'HR'
    'PHI_CR_NUM' 'CR000012'
    'DEL_JIRA_STATUS' 'TEST'
    'PHI_ASSIGN_TO' 'DTTLNARESH'
    }*/
/*builder.book {
    title 'Head First Java'
    publisher 'Orielly'
    author 'Kathy Sierra', 'Bert Bates'
    year '2005'
    currency 'USD'
    price 44.95
    format 'pdf', 'print'
    }*/
    //builder.book.publisher='NEW CR'
   // builder.book.publisher='New guy'
}

def call() {
    JsonBuilder builder=buildMessage()
    println builder
    println ""
    println builder.toPrettyString()
}
/*
def buildMessage() {
    JsonBuilder builder = new JsonBuilder()
    builder.CR_TASK_NEXT_STEP_REQ {
            PHI_DOMAIN_ID 'HR'
            PHI_CR_NUM 'CR000012'
            DEL_JIRA_STATUS 'TEST'
            PHI_ASSIGN_TO 'DTTLNARESH'
    }

}
*/
