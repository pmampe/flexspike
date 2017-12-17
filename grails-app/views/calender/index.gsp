<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Kalender</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
    </head>

    <body>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h2 class="panel-title">Kalender</h2>
            </div>
            <div class="panel-body">
                <g:form name="findForm" action="index" method="POST">
                    <div class="form-group">
                        <label for="month">Month:</label>
                        <g:select name="month" from="${months}" value="${selectedMonth}" class="form-control"/>
                    </div>
                    <g:submitButton name="find" value="Visa Månad"/>
                </g:form>
                <g:if test="${params.find}">
                    <g:if test="${flexDates}">
                        <table class="table table-responsive table-bordered table-hover table-striped">
                            <thead><tr><th>Date</th><th>Start</th><th>End</th><th>Length</th><th>Comment</th></tr></thead>
                            <tbody>
                                <g:each in="${flexDates}" var="flexDate">
                                    <tr>
                                        <td><g:formatDate date="${flexDate.date}" format="yyyy-MM-dd"/></td>
                                        <td><g:if test="${flexDate.fullTime<1}">Arbetsfri dag</g:if><g:else>0${flexDate.startHour}:00</g:else></td>
                                        <td><g:if test="${flexDate.fullTime<1}">Arbetsfri dag</g:if><g:else>${flexDate.endHour}:00</g:else></td>
                                        <td><g:if test="${flexDate.fullTime<1}">Arbetsfri dag</g:if><g:else><suflex:deltaTimeAsHHMM delta="${flexDate.fullTime}"/></g:else></td>
                                        <td>${flexDate.description}</td>
                                    </tr>
                                </g:each>
                            </tbody>
                            <tfoot><tr><th>Date</th><th>Start</th><th>End</th><th>Length</th><th>Comment</th></tr></tfoot>
                        </table>
                    </g:if>
                </g:if>
            </div>
        </div>
    </body>
</html>
