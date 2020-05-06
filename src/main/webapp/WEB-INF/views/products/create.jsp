<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Storage</title>
</head>
<body>
<h1>Here you can add a new product to storage</h1>
<h4 style="color: #ff0000">${message}</h4>

<form method="post" action="${pageContext.request.contextPath}/products/create">
    Product name<input type="text" name="name">
    Product price<input type="number" name="price">
    <button type="submit">add</button>
</form>

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
                <a href="${pageContext.request.contextPath}/products/delete?id=${product.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<button onclick="document.location='/'">Home</button>
</body>
</html>
