<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personal Account</title>
</head>
<body>
<h1>User cabinet</h1>
<%--<h2>My orders</h2>--%>
<%--<table border ="1">--%>
<%--    <tr>--%>
<%--        <th>Order ID</th>--%>
<%--        <th>Total price</th>--%>
<%--    </tr>--%>
<%--    <c:forEach var="order"  items="${allOrders}">--%>
<%--        <tr>--%>
<%--            <td>--%>
<%--                <c:out value="${order.id}"/>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <c:out value=""/>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <a href="${pageContext.request.contextPath}/orders/delete?id=${order.id}">Delete</a>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>
<h2>My shopping cart</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value="${product.id}"/>
            </td>
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/cart/delete?id=${product.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<h3>
    <button onclick="document.location='/create'">Complete order</button>
    <br>
     <button onclick="document.location='/storage'">Shop</button>
</h3>
</body>
</html>
