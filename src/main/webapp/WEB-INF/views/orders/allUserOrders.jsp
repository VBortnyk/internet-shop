<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order details</title>
</head>
<body>
<h1>Here you can see all your orders</h1>

<table border="1">
    <tr>
        <th>Order ID</th>
        <th>Total price</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.id}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/orders/details?id=${order.id}">Info</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/orders/delete?id=${order.id}">Delete</a>
        </tr>
    </c:forEach>
</table>
</body>
</html>
