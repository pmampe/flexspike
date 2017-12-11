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
                <g:form name="findForm" action="userOverview" method="GET">
                    <div class="form-group">
                        <label for="uid">Uid:</label>
                        <g:textField name="uid" value="${uid}" class="form-control"/>
                    </div>
                    <g:submitButton name="find" value="Sök Användare"/>
                </g:form>

                <g:form name="findForm" action="sudo" method="GET">
                    <div class="form-group">
                        <label for="uid">Uid:</label>
                        <g:textField name="uid" value="${uid}" class="form-control"/>
                    </div>
                    <g:submitButton name="sudo" value="SUDO"/>
                </g:form>


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
            </div>
        </div>
    </body>
</html>
