<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<title>
</title>
<body>
<fmt:setBundle basename="i18n" scope="session"/>
<table border="1" cellpadding="2" cellspacing="2" align="center">
    <tr>
        <td height="20%" colspan="2">
            <tiles:insert attribute="header"/>
        </td>
    </tr>
    <tr>
        <td width="20%" height="250">
            <tiles:insert attribute="leftbar"/>
        </td>
        <td>
            <tiles:insert attribute="body"/>
        </td>
    </tr>
    <tr>
        <td height="20%" colspan="2">
            <tiles:insert attribute="footer"/>
        </td>
    </tr>
</table>
</body>
</html>