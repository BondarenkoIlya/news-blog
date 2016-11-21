<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div>
    <dl>
        <dt><bean:message key="news.title"/></dt>
        <dd><bean:write name="newsForm" property="news.title"/></dd>

        <dt><bean:message key="news.date"/></dt>
        <dd><fmt:formatDate type="date" value="${newsForm.news.date.toDate()}"/></dd>

        <dt><bean:message key="news.brief"/></dt>
        <dd><bean:write name="newsForm" property="news.brief"/></dd>

        <dt><bean:message key="news.content"/></dt>
        <dd><bean:write name="newsForm" property="news.content"/></dd>
    </dl>
    <a href="<c:url value="/newsEdition.do?method=edition&id=${newsForm.news.id}"/>" role="button"
       class="btn btn-default"><bean:message key="news.edition"/></a>
    <a href="<c:url value="/newsDelete.do?id=${newsForm.news.id}"/>" role="button"
       class="btn btn-mini btn-warning"><bean:message key="news.delete"/></a>
</div>
<div>
    <bean:message key="comments"/>:<br>
    <logic:iterate id="comment" name="newsForm" property="news.comments">
        <blockquote>
            <small><bean:message key="comment.author"/> - ${comment.author}</small>
            <small><bean:message key="comment.date"/> - <fmt:formatDate type="both"
                                                                        value="${comment.date.toDate()}"/></small>
            <p>${comment.content}</p>
            <a class="btn btn-mini btn-warning"
               href="<c:url value="/commentDelete.do?id=${comment.id}&news_id=${newsForm.news.id}"/>"
               role="button">
                <bean:message key="comment.delete"/></a>
        </blockquote>
        <br/>
    </logic:iterate>
</div>
<div>
    <bean:message key="comment.new"/><br>
    <html:form action="/commentCreate.do?news_id=${newsForm.news.id}">
        <div style="color: darkred">
            <html:errors/>
        </div>
        <bean:message key="comment.author"/> -
        <html:text property="newComment.author" name="commentForm"/><br>
        <bean:message key="comment.content"/> -
        <html:textarea property="newComment.content" name="commentForm"/><br>
        <html:submit><bean:message key="comment.button"/></html:submit>
    </html:form>
</div>

