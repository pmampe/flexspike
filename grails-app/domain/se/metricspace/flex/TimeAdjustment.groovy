package se.metricspace.flex

class TimeAdjustment {
    String comment
    Date dateCreated
    int delta = 0
    User user

    static constraints = {
        comment(nullable: true)
        dateCreated(nullable: true)
        user(nullable: false)
    }
}
