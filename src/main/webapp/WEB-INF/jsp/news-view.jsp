<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:write name="newsForm" property="news.title" /><br>
<bean:write name="newsForm" property="news.date" /><br>
<bean:write name="newsForm" property="news.brief" /><br>
<bean:write name="newsForm" property="news.content" /><br>
Comments:<br>
<logic:iterate id="comment" name="newsForm" property="news.comments" >
${comment.author} --- ${comment.date}<br>
    ${comment.content}<br>
    <html:link action="/commentEdit.do?method=delete&id=${comment.id}">Delete</html:link><br>
</logic:iterate>
New comment:<br>
<html:form action="/commentEdit.do?method=create">
    <html:text property="newComment.author" name="newsForm" value="author"/><br>
    <html:text property="newComment.content" name="newsForm" value="${news.title}"/><br>
    <html:submit value="Comment"/>
</html:form>

