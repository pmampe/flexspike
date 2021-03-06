databaseChangeLog = {
  changeSet(author: "mange", id: "1448278733528-1") {
    createTable(tableName: "flex_date") {
      column(autoIncrement: "true", name: "id", type: "bigint") {
        constraints(nullable: "false", primaryKey: "true", primaryKeyName: "flexDatePK")
      }

      column(name: "version", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "date_created", type: "datetime") {
        constraints(nullable: "true")
      }

      column(name: "last_updated", type: "datetime") {
        constraints(nullable: "true")
      }

      column(name: "date", type: "date") {
        constraints(nullable: "false", unique: "true")
      }

      column(name: "description", type: "varchar(64)") {
        constraints(nullable: "true")
      }

      column(name: "end_hour", type: "integer") {
        constraints(nullable: "false")
      }

      column(name: "full_time", type: "integer") {
        constraints(nullable: "false")
      }

      column(name: "start_hour", type: "integer") {
        constraints(nullable: "false")
      }

      column(name: "updated_by_eppn", type: "varchar(64)") {
        constraints(nullable: "true")
      }
    }
  }

  changeSet(author: "mange", id: "1448278733528-2") {
    createTable(tableName: "user") {
      column(autoIncrement: "true", name: "id", type: "bigint") {
        constraints(nullable: "false", primaryKey: "true", primaryKeyName: "userPK")
      }

      column(name: "version", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "date_created", type: "datetime") {
        constraints(nullable: "true")
      }

      column(name: "uid", type: "varchar(64)") {
        constraints(nullable: "false", unique: "true")
      }
    }
  }

    changeSet(author: "mange", id: "1448278733528-3") {
    createTable(tableName: "work_rate") {
      column(autoIncrement: "true", name: "id", type: "bigint") {
        constraints(nullable: "false", primaryKey: "true", primaryKeyName: "workRatePK")
      }

      column(name: "version", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "date_created", type: "datetime") {
        constraints(nullable: "true")
      }

      column(name: "last_updated", type: "datetime") {
        constraints(nullable: "true")
      }

      column(name: "end_date", type: "date") {
        constraints(nullable: "false")
      }

      column(name: "start_date", type: "date") {
        constraints(nullable: "false")
      }

      column(name: "comment", type: "varchar(96)") {
        constraints(nullable: "true")
      }

      column(name: "rate", type: "integer") {
        constraints(nullable: "true")
      }

      column(name: "rate_friday", type: "integer") {
        constraints(nullable: "true")
      }

      column(name: "rate_monday", type: "integer") {
        constraints(nullable: "true")
      }

      column(name: "rate_thursday", type: "integer") {
        constraints(nullable: "true")
      }

      column(name: "rate_tuesday", type: "integer") {
        constraints(nullable: "true")
      }

      column(name: "rate_wednesday", type: "integer") {
        constraints(nullable: "true")
      }

      column(name: "user_id", type: "bigint") {
        constraints(nullable: "false")
      }
    }
  }


  changeSet(author: "mange", id: "1448278733528-4") {
    addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "work_rate", constraintName: "wr2uFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "true")
  }

  changeSet(author: "mange", id: "1448278733528-5") {
    createTable(tableName: "reported_time") {
      column(autoIncrement: "true", name: "id", type: "bigint") {
        constraints(nullable: "false", primaryKey: "true", primaryKeyName: "reportedTimePK")
      }

      column(name: "version", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "date_created", type: "datetime") {
        constraints(nullable: "true")
      }

      column(name: "last_updated", type: "datetime") {
        constraints(nullable: "true")
      }

      column(name: "absent_all_day", type: "bit(1)") {
        constraints(nullable: "true")
      }

      column(name: "comment", type: "varchar(96)") {
        constraints(nullable: "true")
      }

      column(name: "daily_delta", type: "integer") {
        constraints(nullable: "true")
      }

      column(name: "daily_total", type: "integer") {
        constraints(nullable: "true")
      }

      column(name: "end_time", type: "integer") {
        constraints(nullable: "true")
      }

      column(name: "flex_date_id", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "lunch_length", type: "integer") {
        constraints(nullable: "true")
      }

      column(name: "start_time", type: "integer") {
        constraints(nullable: "true")
      }

      column(name: "user_id", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "work_rate_id", type: "bigint") {
        constraints(nullable: "true")
      }
    }
  }

  changeSet(author: "mange", id: "1448278733528-6") {
    addForeignKeyConstraint(baseColumnNames: "flex_date_id", baseTableName: "reported_time", constraintName: "rt2fdFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "flex_date", referencesUniqueColumn: "true")
    addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "reported_time", constraintName: "rt2uFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "true")
    addForeignKeyConstraint(baseColumnNames: "work_rate_id", baseTableName: "reported_time", constraintName: "rt2wrFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "work_rate", referencesUniqueColumn: "true")
  }

  changeSet(author: "mange", id: "1448278733528-7") {
    createTable(tableName: "absent") {
      column(autoIncrement: "true", name: "id", type: "bigint") {
        constraints(nullable: "false", primaryKey: "true", primaryKeyName: "absentPK")
      }

      column(name: "version", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "date_created", type: "datetime") {
        constraints(nullable: "true")
      }

      column(name: "last_updated", type: "datetime") {
        constraints(nullable: "true")
      }

      column(name: "comment", type: "varchar(96)") {
        constraints(nullable: "true")
      }

      column(name: "flex_date_id", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "length", type: "integer") {
        constraints(nullable: "true")
      }

      column(name: "start_time", type: "integer") {
        constraints(nullable: "true")
      }

      column(name: "user_id", type: "bigint") {
        constraints(nullable: "false")
      }
    }
  }

  changeSet(author: "mange", id: "1448278733528-8") {
    addForeignKeyConstraint(baseColumnNames: "flex_date_id", baseTableName: "absent", constraintName: "a2fdFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "flex_date", referencesUniqueColumn: "true")
    addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "absent", constraintName: "a2uFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "true")
  }

  changeSet(author: "mange", id: "1448278733528-9") {
    createTable(tableName: "time_adjustment") {
      column(autoIncrement: "true", name: "id", type: "bigint") {
        constraints(nullable: "false", primaryKey: "true", primaryKeyName: "timeAdjustmentPK")
      }

      column(name: "version", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "date_created", type: "datetime") {
        constraints(nullable: "true")
      }

      column(name: "comment", type: "varchar(96)") {
        constraints(nullable: "true")
      }

      column(name: "adjustment", type: "integer") {
        constraints(nullable: "true")
      }

      column(name: "user_id", type: "bigint") {
        constraints(nullable: "false")
      }
    }
  }

  changeSet(author: "mange", id: "1448278733528-10") {
    addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "time_adjustment", constraintName: "ta2uFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "true")
  }

  changeSet(author: "mano3567", id: "1448278733528-11") {
    sql("insert into user (uid, version, date_created) select distinct(uid) as uid, 0, now() from reportedtime where datum>(adddate(curdate(), -1830));")
    sql("insert into flex_date(version, date_created, last_updated, date, description, start_hour, end_hour, full_time, updated_by_eppn) select 0, now(), now(), datum as date, description, mandstart as start_hour, mandend as end_hour, fulltime as full_time, 'flexuser@su.se' from calendar;")
    sql("insert into absent(version, date_created, last_updated, user_id, flex_date_id, start_time, length, comment) select 0, now(), now(), u.id as user_id, f.id as flex_date_id, a.absstart as start_time, a.abslength as length, a.comment from user u inner join absence a on a.uid=u.uid inner join flex_date f on f.date=a.datum;")
    sql("insert into work_rate(version, date_created, last_updated, user_id, start_date, end_date, rate, rate_monday, rate_tuesday, rate_wednesday, rate_thursday, rate_friday) select 0, now(), now(), u.id as user_id, w.startdate, w.enddate, w.rate, w.morate, w.turate, w.werate, w.thrate, w.frrate from workrate w inner join user u on u.uid=w.uid left join flex_date f1 on f1.date=w.startdate left join flex_date f2 on f2.date=enddate;")
    sql("delete from work_rate where rate = 0 and rate_monday = 0 and rate_tuesday = 0 and rate_wednesday = 0 and rate_thursday = 0 and rate_friday = 0;")
    sql("delete from work_rate where rate = 10000 and rate_monday = 0 and rate_tuesday = 0 and rate_wednesday = 0 and rate_thursday = 0 and rate_friday = 0;")
    sql("update work_rate set end_date='2049-12-31' where end_date > '2049-12-31';")
    sql("update work_rate set rate_monday=rate, rate_tuesday=rate, rate_wednesday=rate, rate_thursday=rate, rate_friday=rate where rate_monday = 0 and rate_tuesday = 0 and rate_wednesday = 0 and rate_thursday = 0 and rate_friday = 0;")
    sql("insert into time_adjustment(version, date_created, comment, adjustment, user_id) select 0, t.datum, t.comment, t.delta, u.id as user_id from timeadjustments t inner join user u on u.uid=t.uid;")
    sql("insert into reported_time(version, date_created, last_updated, absent_all_day, comment, daily_delta, daily_total, end_time, flex_date_id, lunch_length, start_time, user_id) select 0, now(), now(), absentallday as absent_all_day, comment, dailydelta as daily_delta, dailytotal as daily_total, (endhour*60+endminute) as end_time, f.id as flex_date_id, lunchlength as lunch_length, (starthour*60+startminute) as start_time, u.id as user_id from reportedtime r inner join user u on u.uid=r.uid inner join flex_date f on f.date=r.datum;")
  }

  changeSet(author: "mano3567", id: "1448278733528-12") {
    sql("update reported_time r inner join user u on u.id=r.user_id inner join flex_date f on f.id=r.flex_date_id inner join work_rate w on w.user_id=u.id and w.start_date<=f.date and w.end_date>=f.date set r.work_rate_id=w.id where w.start_date is not null and w.end_date is not null;")
  }

  changeSet(author: "mano3567", id: "1448278733528-13") {
    dropTable(tableName: "calendar")
    dropTable(tableName: "employee")
  }

/* drop the rest in a few months after prod ...
  changeSet(author: "mano3567", id: "1448278733528-14") {
    dropTable(tableName: "absence")
    dropTable(tableName: "agregatedtime")
    dropTable(tableName: "reportedtime")
    dropTable(tableName: "timeadjustments")
    dropTable(tableName: "workrate")
  }
  */
}
