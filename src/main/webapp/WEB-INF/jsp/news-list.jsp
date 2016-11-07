<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<h3><bean:message key="news.list"/></h3>
<logic:iterate name="newsListForm" property="newsList" id="news">
    <bean:message key="news.title"/><br/>
    <bean:write name="news" property="title"/><br>
    <bean:message key="news.date"/><br/>
    <fmt:formatDate type="date" value="${news.date.toDate()}"/><br>
    <bean:message key="news.brief"/><br/>
    <bean:write name="news" property="brief"/><br>
    <html:link action="/newsEdition.do?method=view&id=${news.id}"><bean:message key="news.list.detail"/>
    </html:link><br>
    <br>
</logic:iterate>