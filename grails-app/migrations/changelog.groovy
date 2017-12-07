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

      column(name: "start_time", type: "integer") {
        constraints(nullable: "true")
      }

      column(name: "user_id", type: "bigint") {
        constraints(nullable: "false")
      }
    }
  }

  changeSet(author: "mange", id: "1448278733528-4") {
    addForeignKeyConstraint(baseColumnNames: "flex_date_id", baseTableName: "reported_time", constraintName: "rt2fdFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "flex_date", referencesUniqueColumn: "true")
    addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "reported_time", constraintName: "rt2uFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "true")
  }
}
