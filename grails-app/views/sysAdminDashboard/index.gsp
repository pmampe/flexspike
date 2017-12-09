<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>SysAdmin</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
    </head>

    <body>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h2 class="panel-title">SysAdmin Dashboard</h2>
            </div>
            <div class="panel-body">
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
            </div>
        </div>
    </body>
</html>
