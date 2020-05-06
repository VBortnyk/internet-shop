<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Please input your personal data</h1>
<h4 style="color: red">${message}</h4>

<form method="post" action="${pageContext.request.contextPath}/registration">
    Please input your login<input type="text" name="login">
    Please input your password<input type="password" name="pwd">
    Please confirm your login<input type="password" name="pwd-confirmation">

    <button type="submit">Register</button>
</form>

</body>
</html>
