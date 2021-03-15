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
            <h1 class="text-center display-4 grey">Task</h1>
            <section>
                <c:set var="errors" value="${ error }" />
                <c:if test="${ errors == null }">
                    <form method="POST" action="save">
                        <table class="table table-striped">  
                            <c:if test="${task != null && task.id!= 0 }">
                                <tr>
                                    <td><label>Task Id:</label></td>
                                    <td>
                                        ${ task.id }
                                        <input type="hidden" value="${ task.id }" name="hdnTaskId" />                                
                                    </td>
                                </tr>
                            </c:if>
                            <tr>                    
                                <td>Name</td>
                                <td><input class="form-control" type="text" name="taskName" value='${ task.name }'/></td>
                            </tr>
                            <tr>                    
                                <td>Description</td>
                                <td><input class="form-control" type="text" name="taskDescription" value='${ task.description }'/></td>

                            </tr>
                            <tr>                    
                                <td>Duration</td>
                                <td><input class="form-control" type="text" name="taskDuration" value='${ task.duration }'/></td>
                            </tr>
                            <tr>
                                <td>Created At:</td>
                                <td><input class="form-control" type="date" name="taskCreatedAt" value="${ task.createdAt }" /></td>
                            </tr>
                            <tr>
                                <td>Updated At:</td>
                                <td><input class="form-control" type="date" name="taskUpdatedAt" value="${ task.updateddAt }" /></td>
                            </tr>
                        </table>

                        <c:choose>
                            <c:when test="${ task != null && task.id != 0 }">
                                <input class="btn btn-primary" type="submit" value="Delete" name="action" />
                                <input class="btn btn-primary" type="submit" value="Save" name="action" />                       
                            </c:when>
                            <c:otherwise>
                                <input class="btn btn-primary" type="submit" value="Create" name="action" />
                            </c:otherwise>
                        </c:choose>
                    </form>
                </c:if>
            </section>
        </main>
        <c:if test="${error != null || task.errors.size() > 0}">
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
                                <c:when test="${ task.errors.size() > 0 }">
                                    <ul>
                                        <c:forEach items="${ task.errors }" var="err">
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
