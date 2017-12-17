<!doctype html>
<html lang="sv">
  <head>
    <title><g:layoutTitle default="Flex"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>

    <g:layoutHead/>
  </head>

  <body>
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="/">Flex</a>
        </div>
          <ul class="nav navbar-nav">
            <li><g:link controller="calender" action="index" title="Visa Kalender">SU Kalender</g:link></li>
            <li><g:link controller="dashboard" action="workRate" title="Hantera eventuell deltid">Deltid</g:link></li>
            <li><g:link controller="public" action="contacts" title="Kontakter">Kontakter</g:link></li>
            <g:if test="${session.sessionUser.getRole()==se.metricspace.flex.Role.SYSADMIN||session.sessionUser.getRole()==se.metricspace.flex.Role.CALADMIN}">
                <li><g:link controller="calender" action="caladmin">KalenderAdmin</g:link></li>
            </g:if>
            <g:if test="${session.sessionUser.getRole()==se.metricspace.flex.Role.SYSADMIN}">
                <li><g:link controller="admin" action="index">Admin</g:link></li>
            </g:if>
            <g:if test="${session.realUser}">
                <li><g:link controller="public" action="unsudo">Un-Sudo from ${session.sessionUser.eppn}</g:link></li>
            </g:if>
          </ul>
      </div>
    </nav>
    <g:layoutBody/>
    <div class="footer" role="contentinfo">Stockholms universitet, SE-106 91 Stockholm | Tfn: 08-16 20 00 | <a href="http://www.su.se/kontakt" title="Besöksadresser och fler kontaktuppgifter">Besöksadresser & fler kontaktuppgifter</a></div>
  </body>
</html>
