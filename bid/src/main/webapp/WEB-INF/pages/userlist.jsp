<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/7 0007
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
 <c:forEach items="${p.data}" var="d">
     <p>
         <%--${d.id},${d.userName}--%>
         ${d}
     </p>
 </c:forEach>
<p>
    <span>当前是第${p.page}页,总数是${p.count}</span>
</p>
</body>
</html>
