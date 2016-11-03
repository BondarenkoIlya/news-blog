<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:write name="newsForm" property="news.title" /><br>
<bean:write name="newsForm" property="news.date" /><br>
<bean:write name="newsForm" property="news.brief" /><br>
<bean:write name="newsForm" property="news.content" /><br>
<html:link action="/newsEdition.do?method=edition&id=${newsForm.news.id}">Edit news</html:link><br>
<html:link action="/newsEdit.do?method=delete&id=${newsForm.news.id}">Delete news</html:link><br>
Comments:<br>
<logic:iterate id="comment" name="newsForm" property="news.comments" >
${comment.author} --- ${comment.date}<br>
    ${comment.content}<br>
    <html:link action="/commentEdit.do?method=delete&id=${comment.id}&news_id=${newsForm.news.id}">Delete</html:link><br>
</logic:iterate>
New comment:<br>
<html:form action="/commentEdit.do?method=create&news_id=${newsForm.news.id}">
    <html:text property="newComment.author" name="newsForm"/><br>
    <html:text property="newComment.content" name="newsForm"/><br>
    <html:submit value="Comment"/>
</html:form>

