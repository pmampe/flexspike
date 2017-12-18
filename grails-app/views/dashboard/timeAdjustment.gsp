<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Justera - Flextid</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
  </head>

  <body>
    <div class="panel panel-primary">
      <div class="panel-heading">
        <h2 class="panel-title">Justera tid</h2>
      </div>
      <div class="panel-body">
          <p>Om saldot ska minskas anges ett minustecken före antal timmar och minuter. Om saldot ska ökas anges ett plustecken före antal timmar och minuter.</p>
          <g:form action="timeAdjustment" method="post">
              <div class="row">
                  <div class="col-sm-4">
                      <label for="adjustment">Justering (HH:MM):</label>
                  </div>
                  <div class="col-sm-8">
                      <g:textField name="adjustment" placeHolder="HH:MM" class="form-control"/>
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
                      <g:submitButton name="saveTimeAdjustment" value="Spara justering"/>
                  </div>
              </div>
          </g:form>
          <g:if test="${timeAdjustments}">
              <table class="table table-bordered table-hover table-responsive table-striped">
                  <thead><tr><th>Rapporterad</th><th>Justering</th><th>Kommentar</th></tr></thead>
                  <tbody>
                    <g:each in="${timeAdjustments}" var="timeAdjustment">
                        <tr>
                            <td><g:formatDate date="${timeAdjustment.dateCreated}" format="yyyy-MM-dd HH:mm"/></td>
                            <td><suflex:deltaTimeAsHHMM delta="${timeAdjustment.adjustment}"/></td>
                            <td>${timeAdjustment.comment}</td>
                        </tr>
                    </g:each>
                  </tbody>
                  <tfoot><tr><th>Rapporterad</th><th>Justering</th><th>Kommentar</th></tr></tfoot>
              </table>
          </g:if>
      </div>
    </div>
  </body>
</html>
