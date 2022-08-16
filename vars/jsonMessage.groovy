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
def jsonParseData(jsonObj) {
    def slurObj=new groovy.json.JsonSlurper().parseText(jsonObj)
    return slurObj
}

@NonCPS
def buildMessage() {
    def book1 = new book('Learn Java','Orielly','Kathy Sierra')
def builder = new JsonBuilder(book1)
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
            builder.book.publisher='New guy'
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
