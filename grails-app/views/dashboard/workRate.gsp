<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Deltid - Flextid</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <asset:javascript src="dashboard/workRate.js"/>
  </head>

  <body>
    <div class="panel panel-primary">
      <div class="panel-heading">
        <h2 class="panel-title">Deltid</h2>
      </div>
      <div class="panel-body">
          <h3>Deltid</h3>
          <p>De som arbetar heltid behöver inte lägga in 100 procent som arbetsgrad, och ifall man är ledig (semester/tjänstledig/föräldraledig eller liknande) behöver man inte lägga in noll procent eftersom man inte förväntas rapportera flex under den perioden.</p>
          <g:form action="workRate" method="post">
              <div class="row">
                  <div class="col-sm-2">
                      <label for="startdate">Gäller från (yy-mm-dd):</label>
                  </div>
                  <div class="col-sm-2">
                      <strong id="message0" style="color: red;"></strong>
                  </div>
                  <div class="col-sm-8">
                      <g:textField name="startdate" placeholder="yy-mm-dd" class="form-control" maxlength="8"/>
                  </div>
              </div>
              <div class="row">
                  <div class="col-sm-2">
                      <label for="enddate">Gäller till (yy-mm-dd):</label>
                  </div>
                  <div class="col-sm-2">
                      <strong id="message1" style="color: red;"></strong>
                  </div>
                  <div class="col-sm-8">
                      <g:textField name="enddate" placeholder="yy-mm-dd" class="form-control" maxlength="8"/>
                  </div>
              </div>
              <div class="row">
                  <div class="col-sm-2">
                      <label for="workrate">Arbetsgrad (procent):</label>
                  </div>
                  <div class="col-sm-2">
                      <strong id="message2" style="color: red;"></strong>
                  </div>
                  <div class="col-sm-8">
                      <g:textField name="workrate" class="form-control" maxlength="3"/>
                  </div>
              </div>
              <div class="row">
                  <div class="col-sm-4">
                      <label for="comment">Kommentar:</label>
                  </div>
                  <div class="col-sm-8">
                      <g:textField name="comment" class="form-control"/>
                  </div>
              </div>
              <div class="row">
                  <div class="col-sm-4">
                      &nbsp;
                  </div>
                  <div class="col-sm-8">
                      <g:submitButton name="saveWorkRate" value="Spara arbetsgrad" disabled="${true}"/>
                  </div>
              </div>
          </g:form>
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
