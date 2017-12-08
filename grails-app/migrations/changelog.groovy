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

      column(name: "eppn", type: "varchar(64)") {
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

      column(name: "end_date_id", type: "bigint") {
        constraints(nullable: "true")
      }

      column(name: "start_date_id", type: "bigint") {
        constraints(nullable: "true")
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
    addForeignKeyConstraint(baseColumnNames: "end_date_id", baseTableName: "work_rate", constraintName: "wr2fd1FK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "flex_date", referencesUniqueColumn: "true")
    addForeignKeyConstraint(baseColumnNames: "start_date_id", baseTableName: "work_rate", constraintName: "wr2fd2FK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "flex_date", referencesUniqueColumn: "true")
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
    addForeignKeyConstraint(baseColumnNames: "work_rate_id", baseTableName: "reported_time", constraintName: "rt2wrFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "true")
  }

  changeSet(author: "mange", id: "1448278733528-7") {
    createTable(tableName: "absence") {
      column(autoIncrement: "true", name: "id", type: "bigint") {
        constraints(nullable: "false", primaryKey: "true", primaryKeyName: "absencePK")
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
    addForeignKeyConstraint(baseColumnNames: "flex_date_id", baseTableName: "absence", constraintName: "a2fdFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "flex_date", referencesUniqueColumn: "true")
    addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "absence", constraintName: "a2uFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "true")
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

      column(name: "delta", type: "integer") {
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
}
