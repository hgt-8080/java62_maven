<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div>
    <form action="userSearch.jhtml" method="get">
        <div style="text-align: center;margin-top: 20px">
            姓名:<input type="text" name="name" value="${name}"/>
            开始时间<input type="date" name="startDate" value="${start}"/>
            结束时间:<input type="date" name="end" value="${end}"/>
            <input type="submit" value="查询"/>
        </div>
    </form>
</div>
<a href="toAddProject.jhtml">增加项目</a>
<div class="layui-card-body ">
    <table class="layui-table layui-form">
        <thead>
        <tr>
            <th>ID</th>
            <th>用户名</th>
            <th>真实姓名</th>
            <th>电话</th>
            <th>坐标</th>
            <th>所属单位</th>
            <th>角色</th>
            <th>入职时间</th>
            <th>操作</th>
        </thead>
        <tbody>
        <c:forEach items="${p.data}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>${user.name}</td>
                <td>${user.telephone}</td>
                <td>${user.landMark}</td>
                <td>${user.unit}</td>
                <td>${user.role}</td>
                <td><fmt:formatDate value="${user.enterdate}" pattern="yyyy年MM月dd日"/></td>
                <td class="td-status">
                    <a onclick="return confirm('您真的要修改吗？')" href="user-update-${user.id}.jhtml">修改</a>
                <%--<td class="td-manage">
                    <a onclick="member_stop(this,'10001')" href="javascript:;" title="启用">
                        <i class="layui-icon">&#xe601;</i>
                    </a>

                </td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div>

    <div style="text-align: center;font-size: 23px">
        <a href="userSearch.jhtml?page=1&name=${name}&start=${start}&end=${end}">首页</a>
        <c:if test="${p.page>1}">
            <a href="userSearch.jhtml?page=${p.page-1}&name=${name}&start=${start}&end=${end}">上一页</a>
        </c:if>
        <c:if test="${p.totalpage>p.page}">
            <a href="userSearch.jhtml?page=${p.page+1}&name=${name}&start=${start}&end=${end}">下一页</a>
        </c:if>
        <a href="userSearch.jhtml?page=${p.totalpage}&name=${name}&start=${start}&end=${end}">尾页</a>
        <input type="number" min="1" value="${p.page}" id="go" style="width: 40px;height: 40px"/>
    </div>

</div>

</body>
</html>
<style>
    a{
        color: #0000FF;
    }
    a:hover{
        color: red;
    }
</style>