<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/7 0007
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form class="layui-form" method="post" action="useradd.jhtml">
    <div class="layui-form-item">
        <label for="userName" class="layui-form-label"> <span
                class="x-red">*</span>登录名
        </label>
        <div class="layui-input-inline">
            <input type="text" id="userName" name="userName" lay-verify="userName"
                   required="required" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">
            <span class="x-red">*</span>将会成为您唯一的登入名
        </div>
    </div>
    <div class="layui-form-item">
        <label for="name" class="layui-form-label">
            真实姓名
        </label>
        <div class="layui-input-inline">
            <input type="text" id="name" name="name"
                   lay-verify="name" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label for="password" class="layui-form-label"> <span
                class="x-red">*</span>密码
        </label>
        <div class="layui-input-inline">
            <input type="password" id="password" name="password" required=""
                   lay-verify="password" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">2到16个字符</div>
    </div>
    <div class="layui-form-item">
        <label for="repass" class="layui-form-label"> <span
                class="x-red">*</span>确认密码
        </label>
        <div class="layui-input-inline">
            <input type="password" id="repass" name="repass" required=""
                   lay-verify="repass" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label for="telephone" class="layui-form-label"> <span
                class="x-red"></span>手机号
        </label>
        <div class="layui-input-inline">
            <input type="telephone" id="telephone" name="telephone"
                   lay-verify="telephone" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label for="unit" class="layui-form-label"> <span
                class="x-red"></span>所属单位
        </label>
        <div class="layui-input-inline">
            <input type="text" id="unit" name="unit"
                   lay-verify="unit" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label for="landmark" class="layui-form-label"> <span
                class="x-red"></span>坐标
        </label>
        <div class="layui-input-inline">
            <input type="text" id="landmark" name="landMark"
                   lay-verify="landmark" autocomplete="off" class="layui-input">
        </div>
    </div>
   <div class="layui-form-item">
        <label for="enterdate" class="layui-form-label"> <span
                class="x-red"></span>入职日期
        </label>
        <div class="layui-input-inline">
            <input type="text" id="enterdate" name="enterdate"
                   lay-verify="enterdate" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label for="role" class="layui-form-label"> <span
                class="x-red">*</span>角色
        </label>
        <div class="layui-input-inline">
            <select  id="role" name="role"
                     lay-verify="role"  class="layui-input">
                <option value="1">省局</option>
                <option value="2">市局</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label  class="layui-form-label"></label>
        <label  class="layui-form-label"></label>
        <button class="layui-btn" lay-filter="add" lay-submit="">增加</button>
    </div>
</form>
</body>
</html>
