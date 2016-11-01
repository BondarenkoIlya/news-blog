<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:write name="newsForm" property="news.title" />
<logic:iterate id="comment" name="newsForm" property="news.comments" >
${comment.author}

</logic:iterate>

