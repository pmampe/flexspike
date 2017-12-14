<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Flextid</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <asset:javascript src="dashboard/index.js"/>
  </head>

  <body>
    <div class="panel panel-primary">
      <div class="panel-heading">
        <h2 class="panel-title">Flextidsregistrering för ${session.sessionUser.displayName} (${session.sessionUser.eppn})</h2>
      </div>
      <div class="panel-body">
        <div class="row">
            <div class="col-sm-4">Datum</div>
            <div class="col-sm-8"><g:select name="date" from="${dates}" optionKey="id" optionValue="dateAsString"/></div>
        </div>
        <div id="reportTime">
            <g:render template="reportTime" model="${[flexDate: flexDate, reportedTime: reportedTime, reportedTimeDelta: reportedTimeDelta, timeAdjustmentSum: timeAdjustmentSum]}"/>
        </div>
        <div class="row">
            <div class="col-sm-2"><g:submitButton name="showMonthly" value="Månadsvis"/></div>
            <div class="col-sm-2"><g:submitButton name="show12Weeks" value="12 veckor"/></div>
            <div class="col-sm-2"><g:submitButton name="showLastMonth" value="Senaste Månad"/></div>
            <div class="col-sm-2"><g:submitButton name="showAbsence" value="Absence"/></div>
            <div class="col-sm-2"><g:submitButton name="showTimeAdjustments" value="TimeAdjustments"/></div>
            <div class="col-sm-2"><g:submitButton name="showWorkRates" value="WorkRates"/></div>
        </div>
        <div id="summary" class="row">
        </div>
      </div>
    </div>
  </body>
</html>
