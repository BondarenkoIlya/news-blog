<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h3><bean:message key="news.edition"/></h3>

<html:form action="/newsEdit.do?method=update&id=${newsForm.news.id}">
    <bean:message key="news.title"/><br/>
    <html:textarea property="news.title" name="newsForm" value="${newsForm.news.title}"/><br>
    <bean:message key="news.date"/><br/>
    <html:textarea property="editDate" name="newsForm"/><br/>
    <bean:message key="news.edit.date.format"/><br/>
<bean:message key="news.brief"/><br/>
    <html:textarea property="news.brief" name="newsForm" value="${newsForm.news.brief}"/><br>
    <bean:message key="news.content"/><br/>
    <html:textarea property="news.content" name="newsForm" value="${newsForm.news.content}"/><br>
    <html:submit><bean:message key="news.edit.button"/></html:submit>
</html:form>

