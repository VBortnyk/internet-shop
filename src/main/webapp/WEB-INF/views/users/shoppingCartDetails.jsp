<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personal Account</title>
</head>
<body>
<h1>User cabinet</h1>

<h1>Welcome </h1> <h4 style="color: red">${message}</h4>
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
