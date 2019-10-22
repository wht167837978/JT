<%--
  Created by IntelliJ IDEA.
  User: hailey
  Date: 2019/9/18
  Time: 1:29 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="http://localhost:8091/file" method="post" enctype="multipart/form-data">
    文件：<input name="pic" type="file"/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
