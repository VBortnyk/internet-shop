<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login page</h1>
<h4 style="color: red">${message}</h4>

<form method="post" action="${pageContext.request.contextPath}/login">
    Please input your login<input type="test" name="login">
    Please input your password<input type="password" name="pwd">

    <button type="submit">Login</button>
</form>
<h3>Don't have your profile yet? Please pass registration</h3>
<button onclick="document.location='/registration'">Registration</button>
<br>
<br>
<button onclick="document.location='/injectData'">inject data</button>
</h3>

</body>
</html>
