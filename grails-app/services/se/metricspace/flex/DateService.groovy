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
    List<String> getDatesForMonth(String month) {
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
}
