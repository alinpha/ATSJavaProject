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
            <h1 class="text-center display-4 grey">Teams List</h1>
            <section>
                <c:choose>
                    <c:when test="${ teams.size() > 0}">
                        <table class="table table-striped">
                            <tr class="bg-dark text-light">
                                <th>Name</th>
                                <th></th>
                            </tr>
                            <c:forEach items="${teams}"  var="team">
                                <tr> 
                                    <td>${ team.name }</td>
                                    <td><a href="team/${ team.id}">Show</a></td>  
                                </tr>
                            </c:forEach>

                        </table>
                    </c:when>
                    <c:when test="${ teams.size() == 0}">
                        <h4 style="text-align:center">No Teams</h4>
                    </c:when>
                </c:choose>
            </section>
        </main>
    </body>
    <%@include file="WEB-INF/jspf/footer.jspf" %>
</html>
