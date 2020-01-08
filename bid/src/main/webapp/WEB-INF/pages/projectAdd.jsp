<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/8 0008
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增项目</title>
</head>
<body>
<form enctype="multipart/form-data" action="addProject.jhtml" method="post">

    <div class="layui-col-md9 layui-col-xs12">
        <div class="layui-row layui-col-space10">
            <div class="layui-col-md9 layui-col-xs7">
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">工程名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="projectName" class="layui-input projectName" lay-verify="projectName" placeholder="请输入工程名">
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">项目类型</label>
                    <div class="layui-input-block">
                        <select name="projectCategory" id="projectCategory" class="projectCategory" >
                            <option value="水文水资源管理">水文水资源管理</option>
                            <option value="水文工程">水文工程</option>
                            <option value="水文信息化">水文信息化</option>
                            <option value="综合保障服务">综合保障服务</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">专家数量</label>
                    <div class="layui-input-block">
                        <input type="text" name="professorNum" class="layui-input professorNum" lay-verify="professorNum" placeholder="请输入数量">
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">项目状态</label>
                    <div class="layui-input-block">
                        <select name="isEvaluated" id="isEvaluated" class="isEvaluated" lay-filter="isEvaluated">
                            <option value="待抽签">待抽签</option>
                            <option value="随机抽签中">随机抽签中</option>
                            <option value="补抽中">补抽中</option>
                            <option value="待登记">待登记</option>
                            <option value="登记成功">登记成功</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">采购方式</label>
                    <div class="layui-input-block">
                        <select name="prochaseMode" id="prochaseMode" class="prochaseMode" >
                            <option value="领导决定">领导决定</option>
                            <option value="投票决定">投票决定</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">部门名称</label>
                    <div class="layui-input-block">
                        <select name="departmentId" id="departmentId" class="departmentId" >
                            <option value="1">省局</option>
                            <option value="2">市局</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">预算</label>
                    <div class="layui-input-block">
                        <input type="text" name="budget" class="layui-input budget" lay-verify="budget" placeholder="请输入预算">
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">公示日期</label>
                    <div class="layui-input-block">
                        <input type="text" name="announceDate" class="layui-input announceDate" lay-verify="announceDate" placeholder="公示日期" id="announceDate">
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">开标日期</label>
                    <div class="layui-input-block">
                        <input type="text" name="openDate" class="layui-input openDate" lay-verify="openDate" placeholder="开标日期" id="openDate">
                    </div>
                </div>

                <div class="layui-form-item magt3">
                    <label class="layui-form-label">开标地点</label>
                    <div class="layui-input-block">
                        <input type="text" name="address" class="layui-input address" lay-verify="address" placeholder="开标地点" id="address">
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">成交公告日期</label>
                    <div class="layui-input-block">
                        <input type="text" name="dealDate" class="layui-input dealDate" lay-verify="dealDate" placeholder="成交公告日期" id="dealDate">
                    </div>
                </div>

                <div class="layui-form-item magt3">
                    <label class="layui-form-label">成交发布网址</label>
                    <div class="layui-input-block">
                        <input type="text" name="url" class="layui-input url"  placeholder="成交发布网址" id="url">
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">成交单位</label>
                    <div class="layui-input-block">
                        <input type="text" name="dealCompany" class="layui-input dealCompany" lay-verify="dealCompany" placeholder="成交单位" id="dealCompany">
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">成交价格</label>
                    <div class="layui-input-block">
                        <input type="text" name="settlePrice" class="layui-input settlePrice" lay-verify="settlePrice" placeholder="成交价格" id="settlePrice">
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">项目联系人姓名</label>
                    <div class="layui-input-block">
                        <input type="text" name="linkMan" class="layui-input linkMan" lay-verify="linkMan" placeholder="项目联系人姓名" id="linkMan">
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">项目联系人电话</label>
                    <div class="layui-input-block">
                        <input type="text" name="mobliePhone" class="layui-input mobliePhone" lay-verify="mobliePhone" placeholder="项目联系人电话" id="mobliePhone">
                    </div>
                </div>

                <div class="layui-form-item magt3">
                    <label class="layui-form-label">项目文件路径</label>
                    <div class="layui-input-block">
                        <input type="file" accept="rar|zip|doc|docx|xls|xlsx" name="fujian" class="layui-input projectFilePath" lay-verify="projectFilePath" placeholder="项目文件路径" id="projectFilePath">
                    </div>
                </div>

                <div class="layui-form-item magt3">
                    <label class="layui-form-label">创建者</label>
                    <div class="layui-input-block">
                        <input type="text" name="createBy" class="layui-input createBy" lay-verify="createBy" placeholder="创建者" id="createBy">
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">创建日期</label>
                    <div class="layui-input-block">
                        <input type="text" name="createDate" class="layui-input createDate" lay-verify="createDate" placeholder="创建日期" id="createDate">
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">采购部门</label>
                    <div class="layui-input-block">
                        <input type="text" name="department" class="layui-input department" lay-verify="department" placeholder="采购部门" id="department">
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">修改者</label>
                    <div class="layui-input-block">
                        <input type="text" name="updateBy" class="layui-input updateBy" lay-verify="updateBy" placeholder="修改者" id="updateBy">
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">修改日期</label>
                    <div class="layui-input-block">
                        <input type="text" name="updateDate" class="layui-input updateDate" lay-verify="updateDate" placeholder="修改日期" id="updateDate">
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">报道时间</label>
                    <div class="layui-input-block">
                        <input type="text" name="registerTime" class="layui-input registerTime" lay-verify="registerTime" placeholder="报道日期" id="registerTime">
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">报道地点</label>
                    <div class="layui-input-block">
                        <input type="text" name="registerAddress" class="layui-input registerAddress" lay-verify="registerAddress" placeholder="报道地点" id="registerAddress">
                    </div>
                </div>

                <div class="layui-form-item magt3">
                    <label class="layui-form-label">评标委员会组成人数</label>
                    <div class="layui-input-block">
                        <input type="text" name="committee" class="layui-input committee" lay-verify="committee" placeholder="评标委员会组成人数" id="committee">
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">采购代表</label>
                    <div class="layui-input-block">
                        <input type="text" name="professional" class="layui-input professional" lay-verify="professional" placeholder="采购代表" id="professional">
                    </div>
                </div>
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">完成状态</label>
                    <div class="layui-input-block">
                        <select name="status" id="status" class="status" lay-filter="status">
                            <option value="0">未完成</option>
                            <option value="1">已完成</option>
                        </select>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <div class="layui-col-md3 layui-col-xs12">
        <div class="border">
            <hr class="layui-bg-gray" />
            <div class="layui-right">
                <button>提交</button>
            </div>
        </div>
    </div>
</form>
</body>
</html>
