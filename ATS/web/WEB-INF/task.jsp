<%-- 
    Document   : task
    Created on : 10-Mar-2021, 11:36:17 AM
    Author     : Aline Vetrov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <title>Task</title>
    </head>
    <body>
        <c:if test="${vm.task != null}">
        ${vm.task.id} ${vm.task.name} ${vm.task.description} ${vm.book.duration}
        
        <form method="POST" action="save">
            <table class="table">  
                <!-- Check for a task and only render this is I have a task -->
                <c:if test="${vm.task != null && vm.task.id != 0}" />
                    <tr>
                        <td><label>Task Id:</label></td>
                        <td>      
                            ${vm.task.id}
                            <input type="hidden" value='${vm.task.id}' name="hdnTaskId" />                                
                        </td>
                    </tr>
                
                <tr>                    
                    <td>Task Name:</td>
                    <td><input type="text" name="taskName" value='${vm.task.name}'/></td>
                </tr>
                <tr>                    
                    <td>Task Description</td>
                    <td><input type="text" name="taskDescription" value='${ vm.task.description }'/></td>
                </tr>
                <tr>
                    <td>Task Duration</td>
                    <td><input type="text" name="taskDuration" value='${ vm.task.duration !=0 ? vm.task.duration : ''}'/></td>
                </tr>
            </table>

            <!-- Decide on what buttons to render. When updating, show Save and Delete, create show Create -->
            <c:choose>
                <c:when test="${vm.task != null && vm.task.id != 0}">
                    <input class="btn btn-primary" type="submit" value="Delete" name="action" />
                    <input class="btn btn-primary" type="submit" value="Save" name="action" />
                </c:when>
                <c:otherwise>
                    <input class="btn btn-primary" type="submit" value="Create" name="action" />                   
                </c:otherwise> 
            </c:choose>  
        </form>
        </c:if>
        <!--Set up errors here -->
        <c:if test="${error.errors != null}">
            <ul class="alert alert-danger">
                <c:forEach items="${error.errors}" var ="e">
                    <li>${e}</li>
                </c:forEach>
            </ul>
        </c:if>
    </body>
</html>
