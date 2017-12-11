<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Overview ${uid}</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
    </head>

    <body>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h2 class="panel-title">Overview for ${ldapUser?.getFirstAttributeValue('displayName')}</h2>
            </div>
            <div class="panel-body">
                Absent: ${absSum}<br/>
                ReportedTimeDelta: ${reportedTimeDelta}<br/>
                TimeAdjustments: ${timeAdjustmentSum}<br/>

                <g:if test="${absentList}">
                    <hr/><strong>Absent</strong>
                    <table>
                        <thead><tr><th>Date</th><th>Start</th><th>Length</th><th>Comment</th></tr></thead>
                        <tbody>
                        <g:each in="${absentList}" var="absent">
                            <tr><td><g:formatDate date="${absent.flexDate.date}" format="yyyy-MM-dd"/></td><td>${absent.startTime}</td><td>${absent.length}</td><td>${absent.comment}</td></tr>
                        </g:each>
                        </tbody>
                    </table>
                </g:if>
                <g:if test="${latestReportedTimes}">
                    <hr/><strong>ReportedTimes</strong>
                    <table>
                        <thead><tr><th>Date</th><th>DailyDelta</th><th>Comment</th></tr></thead>
                        <tbody>
                            <g:each in="${latestReportedTimes}" var="reportedTime">
                                <tr><td><g:formatDate date="${reportedTime.flexDate.date}" format="yyyy-MM-dd"/></td><td>${reportedTime.dailyDelta}</td><td>${reportedTime.comment}</td></tr>
                            </g:each>
                        </tbody>
                    </table>
                </g:if>
                <g:if test="${timeAdjustments}">
                    <hr/><strong>TimeAdjustments</strong>
                    <table>
                        <thead><tr><th>Date</th><th>Adjustment</th><th>Comment</th></tr></thead>
                        <tbody>
                            <g:each in="${timeAdjustments}" var="timeAdjustment">
                                <tr><td><g:formatDate date="${timeAdjustment.dateCreated}" format="yyyy-MM-dd HH:dd"/></td><td>${timeAdjustment.adjustment}</td><td>${timeAdjustment.comment}</td></tr>
                            </g:each>
                        </tbody>
                    </table>
                </g:if>
                <g:if test="${workRates}">
                    <hr/><strong>WorkRate</strong>
                    <table>
                        <thead><tr><th>StartDate</th><th>Adjustment</th><th>Comment</th></tr></thead>
                        <tbody>
                            <g:each in="${workRates}" var="workRate">
                                <tr><td><g:formatDate date="${workRate.startDate.date}" format="yyyy-MM-dd"/></td><td>${workRate.rate}</td><td>${workRate.comment}</td></tr>
                            </g:each>
                        </tbody>
                    </table>
                </g:if>
            </div>
        </div>
    </body>
</html>
