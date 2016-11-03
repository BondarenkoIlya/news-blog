<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:form action="/newsEdit.do?method=update&id=${newsForm.news.id}">
    <html:textarea property="news.title" name="newsForm" value="${newsForm.news.title}"/><br>
    <html:textarea property="news.brief" name="newsForm" value="${newsForm.news.brief}"/><br>
    <html:textarea property="news.content" name="newsForm" value="${newsForm.news.content}"/><br>
    <html:submit value="Edit"/>
</html:form>

