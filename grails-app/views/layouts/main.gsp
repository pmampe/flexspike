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
        <g:if test="${session.sessionUser.getRole()==se.metricspace.flex.Role.CALADMIN ||session.sessionUser.getRole()==se.metricspace.flex.Role.SYSADMIN}">
          <ul class="nav navbar-nav">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Funtioner <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><g:link controller="calender" action="index">KalenderAdmin</g:link></li>
                <g:if test="${session.sessionUser.getRole()==se.metricspace.flex.Role.SYSADMIN}">
                  <li><g:link controller="admin" action="index">Admin</g:link></li>
                </g:if>
              </ul>
            </li>
          </ul>
        </g:if>
        <g:if test="${session.realUser}">
          <ul class="nav navbar-nav">
              <li><g:link controller="public" action="unsudo">Un-Sudo</g:link></li>
          </ul>
        </g:if>
      </div>
    </nav>
    <div class="container">
      <g:layoutBody/>
    </div>
  </body>
</html>
