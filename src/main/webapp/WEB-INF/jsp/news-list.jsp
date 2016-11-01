<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<logic:iterate  name="newsListForm" property="newsList" id="news">
    <bean:write name="news" property="title"/>
    <br>
    <html:link action="/newsView.do?id=${news.id}">view</html:link>
    <br>
</logic:iterate>