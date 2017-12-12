<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Flextid</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
  </head>

  <body>
    <div class="panel panel-primary">
      <div class="panel-heading">
        <h2 class="panel-title">Flextidsregistrering för ${session.sessionUser.displayName} (${session.sessionUser.eppn})</h2>
      </div>
      <div class="panel-body">
        <strong>Summa: ${reportedTimeDelta+timeAdjustmentSum} (${reportedTimeDelta}, ${timeAdjustmentSum})</strong>
        <g:if test="${reportedTimes}">
            <strong>Reported Time</strong>
            <table class="table table-bordered table-hover table-striped table-responsive">
                <thead><tr><th>Date</th><th>Total</th><th>Delta</th></tr></thead>
                <tbody>
                    <g:each in="${reportedTimes}" var="reportedTime">
                        <tr><td><g:formatDate date="${reportedTime.flexDate.date}" format="yyyy-MM-dd"/></td><td>${reportedTime.dailyTotal}</td><td>${reportedTime.dailyDelta}</td></tr>
                    </g:each>
                </tbody>
                <tfoot><tr><th>Date</th><th>Total</th><th>Delta</th></tr></tfoot>
            </table>
        </g:if>
      </div>
    </div>
  </body>
</html>
