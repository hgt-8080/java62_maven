<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/5 0005
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>管理员登录</h1>
  <p style="color:red;">
      ${msg}
  </p>
<form method="post" action="login.jhtml">
    <input type="text" name="username" placeholder="用户名" required/>
    <input type="password" name="pass" placeholder="密码" required pattern="\w{6,20}"/>
    <input type="submit" value="登录"/>
</form>
</body>
</html>
