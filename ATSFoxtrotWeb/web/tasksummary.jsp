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
                <c:when test="${ deleted != null }">
                    <h4>Task deleted Id: <span class="font-weight-bold">${task.id}</span></h4>
                </c:when>
                <c:otherwise>
                <h4 class="text-center">Task Created</h4>
                    <table class="table table-striped">
                        <tbody>
                            <tr>
                                <td style="text-align: right;">Task Id</td>
                                <td>${ task.id }</td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">Name</td>
                                <td>${ task.name }</td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">Description</td>
                                <td>${ task.description}</td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">Duration</td>
                                <td>${ task.duration}</td>
                            </tr>
                    </table>
                </c:otherwise>
            </c:choose>
        </main>        
    </body>
    <%@include file="WEB-INF/jspf/footer.jspf" %>
</html>
