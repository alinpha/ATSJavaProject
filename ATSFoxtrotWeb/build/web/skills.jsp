<%-- 
    Document   : skills
    Created on : 27-Mar-2021, 10:55:03 PM
    Author     : Aline Vetrov
--%>

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
            <h1 class="text-center display-4 grey">${employee.firstName} ${employee.lastName} Skills</h1>
            <section>
                <c:set var="errors" value="${ error }" />
                <c:if test="${ errors == null }">
                    <form method="POST" action="skills/${employee.id}">
                        
                        <input type="hidden" value="${ employee.id }" name="hdnEmployeeId" /> 
                        
                        <h2>Current Skills</h2>
                        <c:if test="${skills.size() == 0}">
                            <p>No skills assigned</p>
                        </c:if>
                        <ul>
                        <c:forEach items="${skills}" var="skill">
                            <li>
                                
                                <button type="submit" class="btn btn-outline-danger btn-sm mb-1" value="${skill.id}" name="actionRemove">
                                    -
                                </button> ${skill.name}
                            </li>
                        </c:forEach>
                        </ul>
                            
                        <h2>Available Skills (tasks)</h2>
                        <c:if test="${tasks.size() == 0}">
                            <p>No skills in the system</p>
                        </c:if>
                        <ul>
                        <c:forEach items="${tasks}" var="task">
                            <li>
                                
                                <button type="submit" class="btn btn-outline-success btn-sm mb-1" value="${task.id}" name="actionAdd">
                                    +
                                </button> ${task.name}
                            </li>
                        </c:forEach>
                        </ul>
                           
                    </form>
                </c:if>
            </section>
        </main>
        <c:if test="${error != null || employee.errors.size() > 0}">
            <script type="text/javascript">
                $(window).on('load', function () {
                    $('#myModal').modal('show');
                });
            </script>
            <div class="modal fade" tabindex="-1" role="dialog" id="myModal">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Errors</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <c:choose>
                                <c:when test="${ employee.errors.size() > 0 }">
                                    <ul>
                                        <c:forEach items="${ employee.errors }" var="err">
                                            <li>Error Code: ${ err.code } Error Desc: ${err.description }</li>
                                            </c:forEach>
                                    </ul>
                                </c:when>
                                <c:when test="${ error != null }">
                                    <ul>
                                        <c:forEach items="${ error.errors }" var="err">
                                            <li>${ err }</li>
                                            </c:forEach>
                                    </ul>
                                </c:when>
                            </c:choose>
                        </div>
                        <div class="modal-footer">                        
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </body>
    <%@include file="WEB-INF/jspf/footer.jspf" %>
</html>
