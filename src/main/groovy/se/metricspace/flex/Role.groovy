package se.metricspace.flex

enum Role {
  PUBLIC('public',100),
  STUDENT('student',200),
  EMPLOYEE('student',300),
  CALADMIN('student',400),
  SYSADMIN('sysadmin',1000)

  private final String name
  private final String displayName // mimic portfolio-behavior
  private final int value

  Role(String name, Integer value) {
    this.name = name
    this.value = value
  }

  static Role findByName(String name) {
    for (r in Role.enumConstants) {
      if (name == r.name) {
        return r
      }
    }
    return null
  }

  String getName() {
    return name
  }

  Integer getValue() {
    return this.value
  }
}

