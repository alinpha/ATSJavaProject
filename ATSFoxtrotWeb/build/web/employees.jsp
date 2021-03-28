<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/jspf/taglibraries.jspf" %>
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
            <h1 class="text-center display-4 grey">Employees List</h1>
            <section>
                <c:set var="employeesCount" value="${ employees.size()}" />
                <c:choose>
                    <c:when test="${ employeesCount > 0}">
                        <table class="table table-striped">
                            <tr class="bg-dark text-light">
                                
                                <th>
                                    First Name
                                </th>
                                <th>
                                    Last Name
                                </th>
                                <th></th>
                            </tr>
                            <c:forEach items="${employees}"  var="emp">
                                <tr>
                                    
                                    <td>${ emp.firstName }</td>
                                    <td>${ emp.lastName }</td>
                                    <td class="text-right">
                                        <a href="employee/${ emp.id}">Details</a> | 
                                        <a href="skills/${ emp.id}">Skills</a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </table>
                    </c:when>
                    <c:when test="${ employeesCount == 0}">
                        <h4 style="text-align:center">No Employees</h4>
                    </c:when>
                </c:choose>
            </section>
        </main>
    </body>
    <%@include file="WEB-INF/jspf/footer.jspf" %>
</html>
