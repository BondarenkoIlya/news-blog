<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div>
    <bean:message key="news.title"/><br/>
    <bean:write name="newsForm" property="news.title"/><br>
    <bean:message key="news.date"/><br/>
    <fmt:formatDate type="date" value="${newsForm.news.date.toDate()}"/><br>
    <bean:message key="news.brief"/><br/>
    <bean:write name="newsForm" property="news.brief"/><br>
    <bean:message key="news.content"/><br/>
    <bean:write name="newsForm" property="news.content"/><br>
    <html:link action="/newsEdition.do?method=edition&id=${newsForm.news.id}"><bean:message key="news.edition"/>
    </html:link><br>
    <html:link action="/newsEdit.do?method=delete&id=${newsForm.news.id}"><bean:message key="news.delete"/> </html:link><br>
</div>
<div>
    <bean:message key="comments"/>:<br>
    <logic:iterate id="comment" name="newsForm" property="news.comments">
        <bean:message key="comment.author"/> - ${comment.author}<br>
        <bean:message key="comment.date"/> - <fmt:formatDate type="both" value="${comment.date.toDate()}"/><br>
        <bean:message key="comment.content"/>  ${comment.content}<br>
        <html:link action="/commentEdit.do?method=delete&id=${comment.id}&news_id=${newsForm.news.id}"><bean:message
                key="comment.delete"/></html:link><br>
    </logic:iterate>
</div>
<div>
    <bean:message key="comment.new"/><br>
    <html:form action="/commentEdit.do?method=create&news_id=${newsForm.news.id}">
        <bean:message key="comment.author"/> -
        <html:text property="newComment.author" name="newsForm"/><br>
        <bean:message key="comment.content"/> -
        <html:textarea property="newComment.content" name="newsForm"/><br>
        <html:submit><bean:message key="comment.button"/></html:submit>
    </html:form>
</div>

