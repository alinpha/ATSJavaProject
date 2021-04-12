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
                    <h4>Job deleted Id: <span class="font-weight-bold">${job.id}</span></h4>
                </c:when>
                <c:otherwise>
                <h4 class="text-center">Job Created</h4>
                    <table class="table table-striped">
                        <tbody>
                            <tr>
                                <td style="text-align: right;">Job Id</td>
                                <td>${ job.id }</td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">Description</td>
                                <td>${ job.description }</td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">Client Name</td>
                                <td>${ job.clientName }</td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">Tasks</td>
                                <td>
                                    
                                    <c:forEach items="${job.jobTasks}" var="jtask">
                                        ${jtask.task.name}, Cost: ${jtask.operationCost}, Revenue: ${jtask.operationRevenue}<br/>
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">Team</td>
                                <td>${ job.team.name }</td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">Is On Site</td>
                                <td>${ job.isOnSite() }</td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">Start Date</td>
                                <td>${ job.start }</td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">End Date</td>
                                <td>${ job.end }</td>
                            </tr>
                            
                    </table>
                </c:otherwise>
            </c:choose>
        </main>        
    </body>
    <%@include file="WEB-INF/jspf/footer.jspf" %>
</html>
