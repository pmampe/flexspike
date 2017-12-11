package se.metricspace.flex

class TimeAdjustment {
    int adjustment = 0
    String comment
    Date dateCreated
    User user

    static constraints = {
        comment(nullable: true)
        dateCreated(nullable: true)
        user(nullable: false)
    }
}
