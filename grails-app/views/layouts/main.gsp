<!DOCTYPE html>
<html>
<head>
    <title>TekVideo | <g:layoutTitle default="Title"/></title>
    <g:layoutHead/>
    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>
    <fa:require />
    <link rel="shortcut icon" href="${asset.assetPath(src: "favicon.ico")}" />

    <script>
        $(function() {
            var start = Date.now();
            events.configure("${createLink(controller: "event", action: "register")}");
            events.start();

            events.emit({
                "kind": "VISIT_SITE",
                "url": document.location.href,
                "ua": navigator.userAgent
            }, true);

            window.onbeforeunload = function() {
                events.emit({
                    "kind": "EXIT_SITE",
                    "url": document.location.href,
                    "ua": navigator.userAgent,
                    "time": Date.now() - start
                });
                events.flush(false);
            };
        });
    </script>
</head>
<body>

<twbs:navbar title="TekVideo">
    <twbs:navcontainer>
        <twbs:navitem><g:link action="list" controller="course">Kurser</g:link></twbs:navitem>
    </twbs:navcontainer>

    <twbs:navcontainer location="right">
        <sec:ifNotLoggedIn>
            <twbs:navitem>
                <g:link method="POST" controller='login' action=''>Log ind</g:link>
            </twbs:navitem>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <sec:ifAllGranted roles="ROLE_TEACHER">
                <twbs:navitem>
                    <g:link controller="admin">Admin Panel</g:link>
                </twbs:navitem>
            </sec:ifAllGranted>
            <twbs:navitem>
                <g:link method="POST" controller='logout' action=''>Log ud</g:link>
            </twbs:navitem>
        </sec:ifLoggedIn>
    </twbs:navcontainer>
</twbs:navbar>

<twbs:container>
    <g:if test="${flash.error}">
        <twbs:alert type="danger">${flash.error}</twbs:alert>
    </g:if>
    <g:if test="${flash.warning}">
        <twbs:alert type="warning">${flash.warning}</twbs:alert>
    </g:if>
    <g:if test="${flash.success}">
        <twbs:alert type="success">${flash.success}</twbs:alert>
    </g:if>
    <g:if test="${flash.message}">
        <twbs:alert type="info">${flash.message}</twbs:alert>
    </g:if>
    <g:layoutBody/>
</twbs:container>

<twbs:container id="footer">
    <twbs:row>
        <twbs:column cols="3">
            <h5>Dette er en footer</h5>
            <a href="#">Proin sit </a><br />
            <a href="#">In rutrum ex vitae dictum</a><br />
            <a href="#">Nunc suscipit orci</a><br />
            <a href="#">Aenean in turpis feugiat</a><br />
        </twbs:column>
        <twbs:column cols="3">
            <h5>Den kan der være ting i</h5>
            <a href="#">Sed lobortis nunc venenatis augue</a><br />
            <a href="#">Vivamus sit amet risus a</a><br />
            <a href="#">Fusce eget ligula a turpis</a><br />
        </twbs:column>
        <twbs:column cols="6">
            <div class="pull-right">
                <asset:image src="sdu_logo.png" />
            </div>
        </twbs:column>
    </twbs:row>
</twbs:container>
</body>
</html>