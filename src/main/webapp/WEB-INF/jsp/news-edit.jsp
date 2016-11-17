<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3><bean:message key="news.edition"/></h3>
<div style="color: darkred">
    <html:errors/>
</div>
<script>
    $(document).ready(function () {
        $('#datePicker')
                .datepicker({
                    format: 'mm/dd/yyyy',
                    endDate: "0d",
                    clearBtn: true,
                    autoclose: true,
                    todayHighlight: true
                })
                .on('changeDate', function (e) {
                    // Revalidate the date field
                    $('#eventForm').formValidation('revalidateField', 'date');
                });
    });
</script>

<html:form action="/newsUpdate.do?id=${newsForm.news.id}">
    <bean:message key="news.title"/><br/>
    <html:textarea property="news.title" name="newsForm" value="${newsForm.news.title}"/><br>
    <bean:message key="news.date"/><br/>
    <div class="form-group">
        <div class="input-group input-append date" id="datePicker">
            <input type="text" value="<c:out value="${newsForm.editDate}"/>" title="date" class="form-control"
                   name="editDate" id="date"/>
            <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
        </div>
    </div>
    <%--<html:textarea property="editDate" name="newsForm"/><br/>
    <bean:message key="news.edit.date.format"/><br/>--%>
    <bean:message key="news.brief"/><br/>
    <html:textarea property="news.brief" name="newsForm" value="${newsForm.news.brief}"/><br>
    <bean:message key="news.content"/><br/>
    <html:textarea property="news.content" name="newsForm" value="${newsForm.news.content}"/><br>
    <html:submit><bean:message key="news.edit.button"/></html:submit>
</html:form>

