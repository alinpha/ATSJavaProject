<%-- 
    Document   : employee
    Created on : 10-Mar-2021, 11:35:58 AM
    Author     : Aline Vetrov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <title>Employee</title>
    </head>
    <body>
        <c:if test="${vm.employee != null}">
        ${vm.employee.id} ${vm.employee.firstName} ${vm.employee.lastName} ${vm.employee.hourlyRate}
        
        <form method="POST" action="save">
            <table class="table">  
                <!-- Check for a task and only render this is I have a task -->
                <c:if test="${vm.employee != null && vm.employee.id != 0}" />
                    <tr>
                        <td><label>Employee Id:</label></td>
                        <td>      
                            ${vm.employee.id}
                            <input type="hidden" value='${vm.employee.id}' name="hdnEmployeeId" />                                
                        </td>
                    </tr>
                
                <tr>                    
                    <td>First Name:</td>
                    <td><input type="text" name="firstName" value='${vm.employee.firstName}'/></td>
                </tr>
                <tr>                    
                    <td>Last Name</td>
                    <td><input type="text" name="lastName" value='${ vm.employee.lastName }'/></td>
                </tr>
                <tr>
                    <td>Hourly Rate</td>
                    <td><input type="text" name="hourlyRate" value='${ vm.employee.hourlyRate !=0 ? vm.employee.hourlyRate : ''}'/></td>
                </tr>
            </table>

            <!-- Decide on what buttons to render. When updating, show Save and Delete, create show Create -->
            <c:choose>
                <c:when test="${vm.employee != null && vm.employee.id != 0}">
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