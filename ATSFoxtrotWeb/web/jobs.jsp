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
            <h1 class="text-center display-4 grey">Today Job List</h1>
            <section>
                <c:choose>
                    <c:when test="${ jobs.size() > 0}">
                        <table class="table table-striped">
                            <tr class="bg-dark text-light">
                                
                                <th>
                                    Team
                                </th>
                                <th>Job Description</th>
                                <th>Start Time</th>
                                <th></th>
                            </tr>
                            <c:forEach items="${jobs}"  var="job">
                                <tr>
                                    
                                    <td>${ job.team.name }</td>
                                    <td>${ job.description }</td>
                                    <td>${ job.start }</td>
                                    <td><a href="job/${ job.id}">Show</a></td>  
                                </tr>
                            </c:forEach>

                        </table>
                    </c:when>
                    <c:when test="${ jobs.size() == 0}">
                        <h4 style="text-align:center">No jobs today</h4>
                    </c:when>
                </c:choose>
            </section>
        </main>
    </body>
    <%@include file="WEB-INF/jspf/footer.jspf" %>
</html>
