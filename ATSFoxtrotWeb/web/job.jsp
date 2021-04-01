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
            
            <h1 class="text-center display-4 grey">Job</h1>
            <section>
                <c:set var="errors" value="${ error }" />
                <c:if test="${ errors == null }">
                    <form method="POST" action="save">
                        <table class="table table-striped">  
                            <c:if test="${job != null && job.id != 0 }">
                                <tr>
                                    <td><label>Job Id:</label></td>
                                    <td>
                                        ${ job.id }
                                        <input type="hidden" value="${ job.id }" name="hdnJobId" />                                
                                    </td>
                                </tr>
                            </c:if>
                            <tr>                    
                                <td>Description</td>
                                <td><input class="form-control" type="text" name="jobDesc" value='${ job.description }'/></td>
                            </tr>
                            <tr>                    
                                <td>Client Name</td>
                                 <td><input class="form-control" type="text" name="clientName" value='${ job.clientName }'/></td>
                            </tr>
                            
                            <tr>
                                <td>
                                    Team
                                </td>
                                  <td>
                                    <select name="selectTeam">
                                        <option>Select Member</option>
                                        <c:forEach items="${teams}" var="team">
                                            <option value="${team.id}">${team.name}</option>
                                        </c:forEach>
                                    </select>
                                  </td>
                              </tr>
                            
                           
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
                                <c:when test="${ job.errors.size() > 0 }">
                                    <ul>
                                        <c:forEach items="${ job.errors }" var="err">
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
