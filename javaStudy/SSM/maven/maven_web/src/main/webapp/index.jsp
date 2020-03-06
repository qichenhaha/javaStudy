<%--
  Created by IntelliJ IDEA.
  User: qc
  Date: 2019/12/26
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="account/findAll">查询啊</a>
<a href="account/update">修改</a>
<form action="account/save" method="get">
    <input type="text" name="name">
    <input type="text" name="money">
    <input type="submit" value="提交">
</form>
</body>
</html>
