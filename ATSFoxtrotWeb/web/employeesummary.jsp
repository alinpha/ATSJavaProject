<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/jspf/taglibraries.jspf" %>
<c:set var="vm" value="${vm}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <title>ATS Application</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/navigation.jspf" %>
        <main>
            <c:choose>
                <c:when test="${ vm.rowsDeleted != null }">
                    <h4>Employee deleted Id: <span class="font-weight-bold">${employee.id}</span></h4>
                    </c:when>
                    <c:otherwise>
                    <table class="table table-striped">
                        <tbody>
                            <tr>
                                <td style="text-align: right;">Invoice Id</td>
                                <td>${ employee.id }</td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">First Name:</td>
                                <td>${ invoice.firstName }</td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">Last Name:</td>
                                <td>${ employee.lastName}</td>
                            </tr>
                            
                    </table>
                </c:otherwise>
            </c:choose>
        </main>        
    </body>
    <%@include file="WEB-INF/jspf/footer.jspf" %>
</html>
