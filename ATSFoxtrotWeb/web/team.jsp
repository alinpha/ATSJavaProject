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
            
            <h1 class="text-center display-4 grey">Team</h1>
            <section>
                <c:set var="errors" value="${ error }" />
                <c:if test="${ errors == null }">
                    <form method="POST" action="save">
                        <table class="table table-striped">  
                            <c:if test="${team != null && team.id != 0 }">
                                <tr>
                                    <td><label>Team Id:</label></td>
                                    <td>
                                        ${ team.id }
                                        <input type="hidden" value="${ team.id }" name="hdnTeamId" />                                
                                    </td>
                                </tr>
                            </c:if>
                            <tr>                    
                                <td>Team Name</td>
                                <td><input class="form-control" type="text" name="teamName" value='${ team.name }'/></td>
                            </tr>
                            <tr>                    
                                <td>Is On Call</td>
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="form-check-input" type="checkbox" name="isOnCall" value="" ${team.isOnCall() ? 'checked' : ''}></td>
                            </tr>
                            
                            <c:set var="i" value="${ 1 }" />
                            
                            <c:forEach items="${team.getMembers()}" var="member">
                            
                                <tr>                    
                                    <td>Member ${i}</td>
                                    <td>
                                        <select name="selectedMember${i}">
                                            <option>Select Member</option>
                                            <c:forEach items="${employees}" var="emp">
                                                <option value="${emp.id}">${emp.firstName} ${emp.lastName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                            
                            </c:forEach>
                            
                            <c:if test="${team != null && team.id != 0 }">
                                <tr>                    
                                    <td>Is Deleted</td>
                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="form-check-input" type="checkbox" name="isDeleted" value="" ${team.isDeleted() ? 'checked' : ''}></td>
                                </tr>
                                <tr>
                                    <td>Created At:</td>
                                    <td><input class="form-control" type="date" name="createdAt" value="${ team.createdAt }" /></td>
                                </tr>
                                <tr>
                                    <td>Updated At:</td>
                                    <td><input class="form-control" type="date" name="updatedAt" value="${ team.updatedAt }" /></td>
                                </tr>
                                <tr>
                                    <td>Deleted At:</td>
                                    <td><input class="form-control" type="date" name="deletedAt" value="${ team.deletedAt }" /></td>
                                </tr>
                            </c:if>
                        </table>
                        
                        <c:choose>
                            <c:when test="${ team != null && team.id != 0 }">
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
        <c:if test="${error != null || team.errors.size() > 0}">
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
                                <c:when test="${ team.errors.size() > 0 }">
                                    <ul>
                                        <c:forEach items="${ team.errors }" var="err">
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
