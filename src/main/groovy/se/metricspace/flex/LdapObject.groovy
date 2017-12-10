package se.metricspace.flex

import javax.naming.NamingEnumeration
class LdapObject implements Comparable<LdapObject> {
    protected Map<String, List<String>> itsAttributes = [:]
    protected String itsNameInSpace = null
    protected String itsReverseNameInSpace = null

    LdapObject() {
        this(null)
    }

    LdapObject(String nameInSpace) {
        this(nameInSpace, [:])
    }

    LdapObject(String nameInSpace, Map<String, List<String>> someAttributes) {
        itsNameInSpace = nameInSpace?.trim()
        itsNameInSpace?.split(",").each { String part ->
            if(itsReverseNameInSpace) {
                itsReverseNameInSpace = "${part},${itsReverseNameInSpace}"
            } else {
                itsReverseNameInSpace = part
            }
        }
        itsAttributes = someAttributes
    }

    @Override
    int compareTo(LdapObject object) {
        return itsReverseNameInSpace.compareTo(object.itsReverseNameInSpace)
    }

    @Override
    boolean equals(Object object) {
        boolean eq = false
        if(object && object instanceof LdapObject) {
            eq = itsReverseNameInSpace.hashCode() == object.hashCode()
        }

        return eq
    }

    List<String> getAttributeNames() {
        return (itsAttributes) ? itsAttributes.keySet().sort(): []
    }

    List<String> getAttributeValues(String attributeName) {
        return (itsAttributes) ? itsAttributes.get(attributeName, []).sort(): []
    }

    String getFirstAttributeValue(String attributeName) {
        return (itsAttributes) ? itsAttributes.get(attributeName, [""]).first() : ""
    }

    String getDN() {
        return itsNameInSpace
    }

    String getName() {
        return itsNameInSpace?.split(",")?.first()?.split("=")?.last()
    }

    String getNameInSpace() {
        return itsReverseNameInSpace
    }

    String getNameInSpaceAsURL() {
        return itsReverseNameInSpace.replaceAll('/', '_').replaceAll(',', '/')
    }

    int getNumberOfAttributes() {
        return (itsAttributes) ? itsAttributes.keySet().size() : 0
    }

    List<String> getObjectClasses() {
        return (itsAttributes) ? itsAttributes.get("objectClass", []): []
    }

    String getParentDN() {
        return itsNameInSpace?.substring(1+itsNameInSpace?.indexOf(","))
    }

    @Override
    int hashCode() {
        return (itsReverseNameInSpace) ? itsReverseNameInSpace.hashCode() : 0
    }

    static LdapObject parseLdapEntry(String nameInSpace, javax.naming.directory.BasicAttributes basicAttributes) {
        NamingEnumeration enumeration = basicAttributes.all
        Map<String, List<String>> someAttributes = [:]
        while(enumeration.hasMore()) {
            javax.naming.directory.BasicAttribute attribute = (javax.naming.directory.BasicAttribute)enumeration.next()
            NamingEnumeration all = attribute.all
            while(all.hasMore()) {
                List<String> values = someAttributes.get(attribute.ID.toString(),[])
                String someValue = all.next().toString()
                if(!values.contains(someValue)) {
                    values<<someValue
                    someAttributes.put(attribute.ID.toString(),values.sort())
                }
            }
        }
        enumeration.close()

        LdapObject ldapObject = new LdapObject(nameInSpace, someAttributes)
        return ldapObject
    }

    @Override
    String toString() {
        return "LdapObject: "+itsReverseNameInSpace+" and ${itsAttributes.keySet().size()} attributes"
    }
}