<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-fixed-top">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-9">
                <div class="navbar-header">
                    <a class="navbar-brand"><h3><bean:message key="annual.news"/></h3></a>
                </div>
            </div>
            <div class="col-md-3">
                <div class="collapse navbar-collapse" align="righ">
                    <ul class="nav navbar-nav">
                        <li><html:link action="/changeLocale.do?method=english">English</html:link></li>
                        <li><html:link action="/changeLocale.do?method=france">Français</html:link></li>
                        <li><html:link action="/changeLocale.do?method=russian">Русский</html:link></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>