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
                    <h4>Team deleted Id: <span class="font-weight-bold">${team.id}</span></h4>
                </c:when>
                <c:otherwise>
                <h4 class="text-center">Team Created</h4>
                    <table class="table table-striped">
                        <tbody>
                            <tr>
                                <td style="text-align: right;">Team Id</td>
                                <td>${ team.id }</td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">Name</td>
                                <td>${ team.name }</td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">Is On Call</td>
                                <td>${ team.isOnCall()}</td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">Members</td>
                                <td>
                                    
                                    <c:forEach items="${team.members}" var="member">
                                        ${member.firstName} ${member.lastName}<br/>
                                    </c:forEach>
                                </td>
                            </tr>
                    </table>
                </c:otherwise>
            </c:choose>
        </main>        
    </body>
    <%@include file="WEB-INF/jspf/footer.jspf" %>
</html>
