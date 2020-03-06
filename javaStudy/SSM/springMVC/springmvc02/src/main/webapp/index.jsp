<%--
  Created by IntelliJ IDEA.
  User: qc
  Date: 2019/12/25
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<h3>文件上传</h3>
<form action="file" method="post" enctype="multipart/form-data">
    <input type="file" name="upload">
    <input type="submit" value="上传">
</form>
</body>
</html>
