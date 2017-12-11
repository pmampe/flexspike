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
                Abs: ${absSum}
            </div>
        </div>
    </body>
</html>
