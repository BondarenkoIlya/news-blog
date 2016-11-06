<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<h3><bean:message key="annual.news"/></h3>

<html:link action="/changeLocale.do?method=english">En</html:link>
<html:link action="/changeLocale.do?method=france">Fr</html:link>
<html:link action="/changeLocale.do?method=russian">Рус</html:link>


