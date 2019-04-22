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
                    <input type="text" id="adminId"  placeholder="请输入ID"  class="layui-input layui-disabled" value="${admin.adminId}" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input type="text" placeholder="请输入姓名"  class="layui-input layui-disabled" value="${admin.adminName}" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">身份</label>
                <div class="layui-input-block">
                    <input type="text" placeholder="请输入身份"  class="layui-input layui-disabled" value="管理员" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">电话号码</label>
                <div class="layui-input-block">
                    <input type="text" name="adminTel" id="adminTel" required  lay-verify="phone" placeholder="请输入电话号码" autocomplete="off" class="layui-input" value="${admin.adminTel}">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="telSubmit">提交</button>
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
                    <input type="password" name="oldPwd" id="oldPwd" required lay-verify="required" placeholder="请输入原密码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">新密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="newPwd" id="firstpwd" required lay-verify="firstpwd" placeholder="请输入新密码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">再次输入新密码</label>
                <div class="layui-input-inline">
                    <input type="password" id="secondpwd" required lay-verify="secondpwd" placeholder="请再次输入新密码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="pwdSubmit">立即提交</button>
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
    layui.use(['element','layer','form'], function () {
        var element = layui.element;
        var layer = layui.layer;
        $ = layui.jquery;
        form = layui.form;
        //表单验证
        form.verify({
            firstpwd: [/(.+){6,12}$/, '密码必须6到12位'],
            secondpwd: function(value) {
                if(value != $("#firstpwd").val()){
                    $("#secondpwd").val("");
                    return '确认密码与密码不一致';
                }
            }
        });
        //提交监听事件
        form.on('submit(telSubmit)', function (data) {
            params = {
                'adminId': $('#adminId').val(),
                'adminTel': $('#adminTel').val(),
                'editType':0
            };
            /*alert(JSON.stringify(params))*/
            submit($,params);
            return false;
        });
        form.on('submit(pwdSubmit)', function (data) {
            params = {
              'adminId': $('#adminId').val(),
              'oldPwd':$('#oldPwd').val(),
              'adminPwd': $('#firstpwd').val(),
              'editType':1
            };
            /*alert(JSON.stringify(params))*/
            submit($,params);
            return false;
        });
        //提交
        function submit($,params){
            $.post('${path}/admin/editAdmin ', params, function (res) {
                if (res.status==1) {
                    layer.msg(res.message,{icon:1})
                }else if(res.status==2){
                    layer.msg(res.message,{icon:0},function(){
                        parent.location.href='${path}/admin/index';
                    })
                }else{
                    layer.msg(res.message,{icon:0},function(){
                        location.reload(); // 页面刷新
                        return false
                    })
                }
            }, 'json');
        }
    });
</script>
</body>
</html>
