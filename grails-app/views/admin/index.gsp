<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Admin</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
    </head>

    <body>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h2 class="panel-title">Admin Grace in Moice</h2>
            </div>
            <div class="panel-body">
                <g:form name="findForm" action="sudo" method="GET">
                    <div class="form-group">
                        <label for="eppn">Eppn:</label>
                        <g:textField name="eppn" value="${eppn}" class="form-control"/>
                    </div>
                    <g:submitButton name="sudo" value="SUDO"/>
                </g:form>

                <table class="table table-bordered table-hover table-responsive table-striped">
                    <thead><tr><th>Tid</th><th>Antal</th></tr></thead>
                    <tbody>
                        <tr><td>Week</td><td>${usersLastWeek}</td></tr>
                        <tr><td>Month</td><td>${usersLastMonth}</td></tr>
                        <tr><td>Quarter</td><td>${usersLastQuarter}</td></tr>
                        <tr><td>Year</td><td>${usersLastYear}</td></tr>
                    </tbody>
                    <tfoot><tr><th>Tid</th><th>Antal</th></tr></tfoot>
                </table>
                <hr/>

                <table class="table table-bordered table-hover table-responsive table-striped">
                    <thead><tr><th>Namn</th><th>Antal</th><th>LastUpdated</th></tr></thead>
                    <tbody>
                       <tr><td>FlexDate</td><td>${se.metricspace.flex.FlexDate.count()}</td><td><g:formatDate date="${se.metricspace.flex.FlexDate.findByLastUpdatedIsNotNull([sort: 'lastUpdated', order: 'desc', max: 1])?.lastUpdated}" format="yyyy-MM-dd HH:mm"/></td></tr>
                       <tr><td>User</td><td>${se.metricspace.flex.User.count()}</td><td><g:formatDate date="${se.metricspace.flex.User.findByDateCreatedIsNotNull([sort: 'dateCreated', order: 'desc', max: 1])?.dateCreated}" format="yyyy-MM-dd HH:mm"/></td></tr>
                       <tr><td>WorkRate</td><td>${se.metricspace.flex.WorkRate.count()}</td><td><g:formatDate date="${se.metricspace.flex.WorkRate.findByLastUpdatedIsNotNull([sort: 'lastUpdated', order: 'desc', max: 1])?.lastUpdated}" format="yyyy-MM-dd HH:mm"/></td></tr>
                       <tr><td>ReportedTime</td><td>${se.metricspace.flex.ReportedTime.count()}</td><td><g:formatDate date="${se.metricspace.flex.ReportedTime.findByLastUpdatedIsNotNull([sort: 'lastUpdated', order: 'desc', max: 1])?.lastUpdated}" format="yyyy-MM-dd HH:mm"/></td></tr>
                       <tr><td>Absent</td><td>${se.metricspace.flex.Absent.count()}</td><td><g:formatDate date="${se.metricspace.flex.Absent.findByLastUpdatedIsNotNull([sort: 'lastUpdated', order: 'desc', max: 1])?.lastUpdated}" format="yyyy-MM-dd HH:mm"/></td></tr>
                       <tr><td>TimeAdjustment</td><td>${se.metricspace.flex.TimeAdjustment.count()}</td><td><g:formatDate date="${se.metricspace.flex.TimeAdjustment.findByDateCreatedIsNotNull([sort: 'dateCreated', order: 'desc', max: 1])?.dateCreated}" format="yyyy-MM-dd HH:mm"/></td></tr>
                    </tbody>
                    <tfoot><tr><th>Namn</th><th>Antal</th><th>LastUpdated</th></tr></tfoot>
                </table>
                <hr/>

                <strong>SessionUser:</strong>
                <table class="table table-bordered table-hover table-responsive table-striped">
                    <thead><tr><th>Key</th><th>Value</th></tr></thead>
                    <tbody>
                        <tr><td>Eppn</td><td>${session.sessionUser.eppn}</td></tr>
                        <tr><td>Uid</td><td>${session.sessionUser.uid}</td></tr>
                        <tr><td>Role</td><td>${session.sessionUser.role}</td></tr>
                        <tr><td>IsEmployee</td><td>${session.sessionUser.isEmployee()}</td></tr>
                        <tr><td>IsSuPerson</td><td>${session.sessionUser.isSuPerson()}</td></tr>
                    </tbody>
                    <tfoot><tr><th>Key</th><th>Value</th></tr></tfoot>
                </table>
                <hr/>

                <strong>Host Information:</strong>
                <table class="table table-bordered table-hover table-responsive table-striped">
                    <thead><tr><th>Key</th><th>Value</th></tr></thead>
                    <tbody>
                        <tr><td><strong>Version:</strong></td><td>${grailsApplication.metadata.getApplicationVersion()}</td></tr>
                        <tr><td><strong>Grails:</strong></td><td>v${grailsApplication.metadata.getGrailsVersion()}</td></tr>
                        <tr><td><strong>Groovy:</strong></td><td>v${groovy.lang.GroovySystem.getVersion()}</td></tr>
                        <tr><td><strong>Java-Name:</strong></td><td>${java.lang.System.getProperty("java.runtime.name")}</td></tr>
                        <tr><td><strong>Java-Version:</strong></td><td>${java.lang.System.getProperty("java.runtime.version")}</td></tr>
                        <tr><td><strong>Host:</strong></td><td>${InetAddress?.getLocalHost()?.getHostName()}</td></tr>
                        <tr><td><strong>Environment:</strong></td><td>${grailsApplication.metadata.getEnvironment()}</td></tr>
                    </tbody>
                    <tfoot><tr><th>Key</th><th>Value</th></tr></tfoot>
                </table>

                <strong>Shibboleth Attributes:</strong>
                <table class="table table-bordered table-hover table-responsive table-striped">
                    <thead><tr><th>Key</th><th>Value</th></tr></thead>
                    <tbody>
                        <tr><td><strong>eppn:</strong></td><td>${request.eppn}</td></tr>
                        <tr><td><strong>socialSecurityNumber:</strong></td><td>${request.socialSecurityNumber}</td></tr>
                        <tr><td><strong>mail:</strong></td><td>${request.mail}</td></tr>
                        <tr><td><strong>givenName:</strong></td><td>${request['givenName']}</td></tr>
                        <tr><td><strong>sn:</strong></td><td>${request['sn']}</td></tr>
                        <tr><td><strong>affiliation:</strong></td><td><g:each in="${request['affiliation']?.split(';')}" var="aff">${aff}<br/></g:each></td></tr>
                        <tr><td><strong>entitlement:</strong></td><td><g:each in="${request['entitlement']?.split(';')}" var="enti"><g:if test="${enti.startsWith('urn:mace:swami.se:gmai:')}">${enti.substring(23)}</g:if><g:else>${enti}</g:else><br/></g:each></td></tr>
                    </tbody>
                    <tfoot><tr><th>Key</th><th>Value</th></tr></tfoot>
                </table>
            </div>
        </div>
    </body>
</html>
