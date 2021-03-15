<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/jspf/taglibraries.jspf" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <title>Invoicing Application</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/navigation.jspf" %>
        <main>
            <h1 class="text-center display-4 grey">Invoices List</h1>
            <section>
                <c:set var="invoiceCount" value="${ invoices.size()}" />
                <c:choose>
                    <c:when test="${ invoiceCount > 0}">
                        <table class="table table-striped">
                            <tr class="bg-dark text-light">
                                <th>
                                    Id
                                </th>
                                <th>
                                    Description
                                </th>
                                <th>
                                    Invoice Subtotal
                                </th>
                                <th>
                                    Invoice Discount
                                </th>
                                <th>
                                    Invoice Total
                                </th>
                                <th>
                                    Invoice Date
                                </th>
                            </tr>
                            <c:forEach items="${invoices}"  var="inv">
                                <tr>
                                    <td><a href="invoice/${ inv.id}">${ inv.id}</a></td>
                                    <td>${ inv.invoiceDescription }</td>
                                    <td><fmt:formatNumber value="${inv.subtotal}" type="currency" currencySymbol="$"/></td>
                                    <td><fmt:formatNumber value="${inv.discountAmount}" type="currency" currencySymbol="$"/></td>
                                    <td><fmt:formatNumber value="${inv.total}" type="currency" currencySymbol="$"/></td>
                                    <td>${ inv.invoiceDate}</td>            
                                </tr>
                            </c:forEach>

                        </table>
                    </c:when>
                    <c:when test="${ invoiceCount == 0}">
                        <h4 style="text-align:center">No Invoices</h4>
                    </c:when>
                </c:choose>
            </section>
        </main>
    </body>
    <%@include file="WEB-INF/jspf/footer.jspf" %>
</html>
