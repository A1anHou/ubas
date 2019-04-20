<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/4/19
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>用户行为分析系统 | 管理员</title>
    <!--引入CSS-->
    <link rel="stylesheet" href="${path}/static/plugins/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <jsp:include page="../common/admin_header.jsp"></jsp:include>

    <div class="layui-body" style="left: 0">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>修改电话号码</legend>
        </fieldset>
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">ID</label>
                <div class="layui-input-block">
                    <input type="text" name="id"  placeholder="请输入ID"  class="layui-input layui-disabled" value="${admin.adminId}" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="name"  placeholder="请输入姓名"  class="layui-input layui-disabled" value="${admin.adminName}" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">身份</label>
                <div class="layui-input-block">
                    <input type="text" name="identity"  placeholder="请输入身份"  class="layui-input layui-disabled" value="管理员" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">电话号码</label>
                <div class="layui-input-block">
                    <input type="text" name="tel" required  lay-verify="required" placeholder="请输入电话号码" autocomplete="off" class="layui-input" value="${admin.adminTel}">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>

        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>修改密码</legend>
        </fieldset>
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">原密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" required lay-verify="required" placeholder="请输入原密码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">新密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" required lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">再次输入新密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" required lay-verify="required" placeholder="请再次输入新密码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>

    </div>
    <jsp:include page="../common/admin_footer.jsp"></jsp:include>
</div>
<script src="${path}/static/plugins/layui/layui.js"></script>
<script src="${path}/static/plugins/jquery/jquery.min.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });
    //Demo
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
</script>
</body>
</html>
