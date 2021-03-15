<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/jspf/taglibraries.jspf" %>
<c:set var="vm" value="${vm}" />
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
            <c:choose>
                <c:when test="${ vm.rowsDeleted != null }">
                    <h4>Invoice deleted Id: <span class="font-weight-bold">${invoice.id}</span></h4>
                    </c:when>
                    <c:otherwise>
                    <table class="table table-striped">
                        <tbody>
                            <tr>
                                <td style="text-align: right;">Invoice Id</td>
                                <td>${ invoice.id }</td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">Invoice Description:</td>
                                <td>${ invoice.invoiceDescription }</td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">Invoice Date:</td>
                                <td>${ invoice.invoiceDate}</td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">Invoice Subtotal:</td>
                                <td>
                                    <fmt:formatNumber value="${invoice.subtotal}" type="currency" currencySymbol="$"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">Invoice Discount:</td>
                                <td>
                                    <fmt:formatNumber value="${invoice.discountAmount}" type="currency" currencySymbol="$"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right;">Invoice Total:</td>
                                <td>
                                    <fmt:formatNumber value="${invoice.total}" type="currency" currencySymbol="$"/>
                                </td>
                            </tr>
                    </table>
                </c:otherwise>
            </c:choose>
        </main>        
    </body>
    <%@include file="WEB-INF/jspf/footer.jspf" %>
</html>
