package se.metricspace.flex

import grails.gorm.transactions.Transactional
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
    List<Expando> getUserReportedTimeByMonth(long userId) {
        List<Expando> timeByMonth = []
        Sql sql
        try {
            sql = Sql.newInstance(dataSource)
            
            sql.rows("select sum(r.daily_delta) as daily_delta, sum(r.daily_total) as daily_total, date_format(d.date, '%Y-%m') as month from reported_time r inner join flex_date d on d.id=r.flex_date_id where r.user_id=? group by month order by month desc;", [userId]).each { row ->
                int daily_delta = row.daily_delta as int
                int daily_total = row.daily_total as int
                String month = row.month as String
                timeByMonth << Expando.newInstance(month: month, totalTime: daily_total, deltaTime: daily_delta)
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
}
