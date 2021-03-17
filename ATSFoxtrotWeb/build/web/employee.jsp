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
            <h1 class="text-center display-4 grey">Employee</h1>
            <section>
                <c:set var="errors" value="${ error }" />
                <c:if test="${ errors == null }">
                    <form method="POST" action="save">
                        <table class="table table-striped">  
                            <c:if test="${employee != null && employee.id!= 0 }">
                                <tr>
                                    <td><label>Employee Id:</label></td>
                                    <td>
                                        ${ employee.id }
                                        <input type="hidden" value="${ employee.id }" name="hdnEmployeeId" />                                
                                    </td>
                                </tr>
                            </c:if>
                            <tr>                    
                                <td>First Name</td>
                                <td><input class="form-control" type="text" name="empFirstName" value='${ employee.firstName }'/></td>
                            </tr>
                            <tr>                    
                                <td>Last Name</td>
                                <td><input class="form-control" type="text" name="empLastName" value='${ employee.lastName }'/></td>
                            </tr>
                            <tr>                    
                                <td>SIN</td>
                                <td><input class="form-control" type="text" name="empSin" value='${ employee.sin }'/></td>
                            </tr>
                            <tr>                    
                                <td>Hourly Rate</td>
                                <td><input class="form-control" type="text" name="empHourlyRate" value='${ employee.hourlyRate != 0 ? employee.hourlyRate : '' }'/></td>
                            </tr>
                            <c:if test="${employee != null && employee.id!= 0 }">
                                <tr>                    
                                    <td>Is Deleted</td>
                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="form-check-input" type="checkbox" name="empIsDeleted" value="" ${employee.isDeleted() ? 'checked' : ''}></td>
                                </tr>
                                <tr>
                                    <td>Created At:</td>
                                    <td><input class="form-control" type="date" name="empCreatedAt" value="${ employee.createdAt }" /></td>
                                </tr>
                                <tr>
                                    <td>Updated At:</td>
                                    <td><input class="form-control" type="date" name="empUpdatedAt" value="${ employee.updatedAt }" /></td>
                                </tr>
                                <tr>
                                    <td>Deleted At:</td>
                                    <td><input class="form-control" type="date" name="empDeletedAt" value="${ employee.deletedAt }" /></td>
                                </tr>
                            </c:if>
                        </table>

                        <c:choose>
                            <c:when test="${ employee != null && employee.id != 0 }">
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
