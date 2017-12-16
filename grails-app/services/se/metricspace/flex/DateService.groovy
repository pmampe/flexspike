package se.metricspace.flex

import grails.gorm.transactions.Transactional
import grails.transaction.NotTransactional
import javax.sql.DataSource
import groovy.sql.Sql

@Transactional
class DateService {
    DataSource dataSource

    @Transactional(readOnly=true)
    List<String> getCalenderMonths() {
        List<String> months = []
        Sql sql
        try {
            sql = Sql.newInstance(dataSource)
            sql.rows("select distinct(date_format(date, '%Y-%m')) as month from flex_date order by month desc;").each { row ->
               months << row.month 
            }
        } catch(Throwable exception) {
            log.warn "Problems doing sql: ${exception.getMessage()}"
        } finally {
            if(sql) {
                try {
                    sql.close()
                } catch(Throwable exception) {
                }
                sql = null
            }
        }
        return months 
    }

    @Transactional(readOnly=true)
    List<FlexDate> getDatesForMonth(String month) {
        List<FlexDate> dates = []
        Sql sql
        try {
            sql = Sql.newInstance(dataSource)
            
            sql.rows("select id, date from flex_date where date like ? order by date asc;", [month+"%"]).each { row ->
                FlexDate date = FlexDate.get(row.id as Long)
                if(date) {
                    dates << date
                }
            }
        } catch(Throwable exception) {
            log.warn "Problems doing sql: ${exception.getMessage()}"
        } finally {
            if(sql) {
                try {
                    sql.close()
                } catch(Throwable exception) {
                }
                sql = null
            }
        }
        return dates
    }

    @Transactional(readOnly=true)
    Map<String, Map<String, Integer>> getUserReportedTimeByMonth(long userId) {
        Map<String, Map<String, Integer>> timeByMonth = [:]
        Sql sql
        try {
            sql = Sql.newInstance(dataSource)

            sql.rows("select sum(r.daily_delta) as daily_delta, sum(r.daily_total) as daily_total, date_format(d.date, '%Y-%m') as month from reported_time r inner join flex_date d on d.id=r.flex_date_id where r.user_id=? group by month order by month desc;", [userId]).each { row ->
                int daily_delta = row.daily_delta as Integer
                int daily_total = row.daily_total as Integer
                String month = row.month as String
                timeByMonth.put(month, [totalTime: daily_total, deltaTime: daily_delta, adjustments: 0])
            }
            
            sql.rows("select sum(t.adjustment) as adjustment, date_format(t.date_created, '%Y-%m') as month from time_adjustment t  where t.user_id=? group by month order by month desc;", [userId]).each { row ->
                int adjustment = row.adjustment as int
                String month = row.month as String
                Map<String, Integer> monthE = timeByMonth.get(month, [totalTime: 0, deltaTime: 0, adjustments: 0])
                monthE.put('adjustments', adjustment)
                timeByMonth.put(month, monthE)
            }
        } catch(Throwable exception) {
            log.warn "Problems doing sql: ${exception.getMessage()}"
        } finally {
            if(sql) {
                try {
                    sql.close()
                } catch(Throwable exception) {
                }
                sql = null
            }
        }
        return timeByMonth
    }

    @Transactional(readOnly=true)
    Map<String, Map<String, Integer>> getUserReportedTimeByWeek(long userId, int numberOfWeeks) {
        Map<String, Map<String, Integer>> timeByWeek = [:]
        Sql sql
        try {
            sql = Sql.newInstance(dataSource)
            Date monday = getMostRecentMonday()
            while(numberOfWeeks>0) {
                sql.rows("select sum(r.daily_delta) as daily_delta, sum(r.daily_total) as daily_total from reported_time r inner join flex_date f on f.id=r.flex_date_id where user_id=? and f.date>=? and f.date<?;", [userId, monday, monday.plus(7)]).each { row ->
                    Integer daily_delta = row.daily_delta as Integer
                    Integer daily_total = row.daily_total as Integer
                    String week = monday.format('yyyy-MM-dd')
                    timeByWeek.put(week, [totalTime: (daily_total)?: 0, deltaTime: (daily_delta)?: 0, adjustments: 0])
                }
                sql.rows("select sum(t.adjustment)  as adjustment from time_adjustment t where t.user_id=? and t.date_created>=? and t.date_created<?;", [userId, monday, monday.plus(7)]).each { row ->
                    Integer adjustment = row.adjustment as Integer
                    String week = monday.format('yyyy-MM-dd')
                    Map<String, Integer> weekE = timeByWeek.get(week, [totalTime: 0, deltaTime: 0, adjustments: 0])
                    weekE.put('adjustments', (adjustment)?: 0)
                    timeByWeek.put(week, weekE)
                }
                numberOfWeeks--
                monday = monday.minus(7)
            }
        } catch(Throwable exception) {
            log.warn "Problems doing sql: ${exception.getMessage()}"
        } finally {
            if(sql) {
                try {
                    sql.close()
                } catch(Throwable exception) {
                }
                sql = null
            }
        }
        return timeByWeek
    }

    @NotTransactional
    private Date getMostRecentMonday() {
        Date monday = Date.newInstance()
        while(monday[Calendar.DAY_OF_WEEK] != Calendar.MONDAY) {
            monday = monday.minus(1)
        }

        return Date.parse('yyyy-MM-dd HH:mm', monday.format('yyyy-MM-dd')+" 00:00")
    }
}
