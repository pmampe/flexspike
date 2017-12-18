package se.metricspace.flex

import grails.gorm.transactions.Transactional

@Transactional
class FlexService {

    @Transactional
    void addWorkRate(String uid, Date fromDate, Date toDate, int rate, String comment) {
        User user = findOrCreateUser(uid)
        FlexDate start = findByDate(fromDate)
        FlexDate end = findByDate(toDate)
        if(rate<1) {
            rate = 0
        }
        if(rate>9999) {
            rate = 10000
        }
        WorkRate.newInstance(user: user, startDate: start, endDate: end, rate: rate, rateMonday: rate, rateTuesday: rate, rateWednesday: rate, rateThursday: rate, rateFriday: rate, comment: comment?.trim()).save(flush: true, failOnError: true)
    }

    @Transactional(readOnly = true)
    FlexDate findByDate(Date date) {
        return (date) ? FlexDate.findByDate(Date.parse("yyyy-MM-dd", date.format("yyyy-MM-dd"))) : null
    }

    @Transactional
    User findOrCreateUser(String uid) {
        if(uid?.trim() && !User.findByUid(uid.trim())) {
            User.newInstance(uid: uid.trim()).save(flush: true, failOnError: true)
        }
        return (uid?.trim()) ? User.findByUid(uid.trim()) : null
    }
}
