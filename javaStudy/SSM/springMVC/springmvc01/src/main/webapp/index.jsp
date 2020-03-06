<%--
  Created by IntelliJ IDEA.
  User: qc
  Date: 2019/12/24
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="statics/jquery-3.2.1.js"></script>
</head>


<body>
<h1>HelloWord 黑蛋</h1>
<a href="/hello01">入门程序</a>
<button id="butn" style="background-color: red;color: white;">按钮</button>
</body>
<script>
    $(function () {
        $("#butn").click(function () {
            alert("弹出成功！")
            $.ajax({
                //请求方式
                type: "POST",
                //请求的媒体类型
                contentType: "application/json;charset=UTF-8",
                //请求地址
                url: "testjson",
                //数据，json字符串
                data: '{"name":"lishi","set":"女"}',
                //请求成功
                success: function (result) {
                    console.log(result);
                },
                //请求失败，包含具体的错误信息
                error: function (e) {
                    console.log(e)
                }
            });
        })
    })
</script>
</html>
