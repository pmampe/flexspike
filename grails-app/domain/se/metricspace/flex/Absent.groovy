package se.metricspace.flex

class Absent {
    String comment
    Date dateCreated
    FlexDate flexDate
    Date lastUpdated
    int length = 0 // minutes
    User user
    int startTime = 0 // minutes since midnight

    static constraints = {
        comment(nullable: true, blank: true)
        dateCreated(nullable: true)
        flexDate(nullable: false)
        lastUpdated(nullable: true)
        user(nullable: false)
    }
}
