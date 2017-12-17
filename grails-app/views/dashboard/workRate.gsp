<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Deltid - Flextid</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
  </head>

  <body>
    <div class="panel panel-primary">
      <div class="panel-heading">
        <h2 class="panel-title">Deltid</h2>
      </div>
      <div class="panel-body">
          <h3>Deltid</h3>
          <p>De som arbetar heltid behöver inte lägga in 100 procent som arbetsgrad, och ifall man är ledig (semester/tjänstledig/föräldraledig eller liknande) behöver man inte lägga in noll procent eftersom man inte förväntas rapportera flex under den perioden.</p>
          <g:if test="${workRates}">
              <table class="table table-bordered table-hover table-responsive table-striped">
                  <thead><tr><th>Start</th><th>Slut</th><th>Arbetsgrad</th><th>Kommentar</th></tr></thead>
                  <tbody>
                    <g:each in="${workRates}" var="workRate">
                        <tr>
                            <td><g:formatDate date="${workRate.startDate.date}" format="yyyy-MM-dd"/></td>
                            <td><g:formatDate date="${workRate.endDate?.date}" format="yyyy-MM-dd"/></td>
                            <td>${workRate.getAverageRate()} %</td>
                            <td>${workRate.comment}</td>
                        </tr>
                    </g:each>
                  </tbody>
                  <tfoot><tr><th>Start</th><th>Slut</th><th>Arbetsgrad</th><th>Kommentar</th></tr></tfoot>
              </table>
          </g:if>
      </div>
    </div>
  </body>
</html>
