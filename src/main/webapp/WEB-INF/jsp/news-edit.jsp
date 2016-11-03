<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:form action="">
    <html:text property="newComment.author" name="newsForm" value=""/><br>
    <html:text property="newComment.content" name="newsForm" value=""/><br>
    <html:submit value="Comment"/>
</html:form>
