<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="panel panel-primary">
    <div class="panel-body">
        <bean:message key="menu"/>
    </div>
    <div class="panel panel-info">
        <div class="list-group">
            <div class="list-group-item"><html:link action="/newsList.do"><bean:message
                    key="leftbar.link.news.list"/></html:link></div>
            <div class="list-group-item"><html:link action="/newsNew.do"><bean:message
                    key="leftbar.link.news.new"/></html:link></div>
        </div>
    </div>
</div>