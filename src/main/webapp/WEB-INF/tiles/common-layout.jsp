<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<title>
</title>
<head>
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/3.3.7/css/bootstrap.css"/>">
    <script src="<c:url value="/webjars/jquery/1.11.1/jquery.js"/>"></script>
    <link rel="stylesheet"
          href="<c:url value="/webjars/bootstrap-datepicker/1.6.1/css/bootstrap-datepicker3.min.css"/>">
    <script src="<c:url value="/webjars/bootstrap-datepicker/1.6.1/js/bootstrap-datepicker.min.js"/>"></script>
    <style>
        body {
            padding-top: 70px;
        }
    </style>
</head>
<body>
<fmt:setBundle basename="i18n" scope="session"/>
<table class="table" border="1" cellpadding="2" cellspacing="2" align="center">
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