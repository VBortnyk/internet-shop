<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SHOP</title>
</head>
<body>
<h1>Welcome!</h1>
<h3>Please <a href="${pageContext.request.contextPath}/injectData">Load DB</a>
    to get access to storage
</h3>
<h3>
    <a href="${pageContext.request.contextPath}/registration">Registration</a>
    <br>
    <a href="${pageContext.request.contextPath}/storage">Shop</a>
    <br>
    <a href="${pageContext.request.contextPath}/orders/all">All Orders</a>
    <br>
    <a href="${pageContext.request.contextPath}/users/all">All Users</a>
    <br>
    <a href="${pageContext.request.contextPath}/products/create">Add products to storage(admin only)</a>
    <br>
</h3>
</body>
</html>
