<%--
  Created by IntelliJ IDEA.
  User: qc
  Date: 2019/11/21
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆页面</title>
</head>
<body>
    <form action="loginServlet" method="post">
        用户名：<input type="text" name="name">
        用户名：<input type="password" name="password">
        <input type="submit" value="登陆">

    </form>
</body>
</html>
