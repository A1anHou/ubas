<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/4/12
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="${path}/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" href="${path}/static/custom/css/admin_login.css">
</head>

<body>
<div id="container">

    <div class="admin-login-background">
        <div class="admin-header">
            <h1>用户行为分析系统</h1>
        </div>
        <form class="layui-form" action="${path}/parent/login" method="post">
            <div>
                <i class="layui-icon layui-icon-username admin-icon admin-icon-username"></i>
                <input type="text" name="username" placeholder="请输入用户名"
                       autocomplete="off"
                       class="layui-input admin-input admin-input-username">
            </div>
            <div>
                <i class="layui-icon layui-icon-password admin-icon admin-icon-password"></i>
                <input type="password" name="password"
                       placeholder="请输入密码"
                       autocomplete="off"
                       class="layui-input admin-input">
            </div>
            <div>
                <input type="text" name="verify"
                       placeholder="请输入验证码"
                       autocomplete="off"
                       class="layui-input admin-input admin-input-verify">
                <img class="admin-captcha" src="${path}/Kaptcha"
                     onclick="changeVerifyCode(this)">
            </div>
            <div class="admin-alert">
                <span>${login_result}</span>
                <a href="#" style="float: right;color: #cccccc" onclick="onAddBtn()">没有账号？点击注册</a>
            </div>
            <button class="layui-btn admin-button" lay-submit id="admin_login">登录</button>

        </form>
    </div>
</div>
<div id="add-main" style="display: none;">
    <form class="layui-form" id="add-form" action="">
        <div class="layui-form-item center" >
            <label class="layui-form-label" style="width: 100px" >姓名</label>
            <div class="layui-input-block">
                <input type="text" name="parentName" required value="" style="width: 240px" lay-verify="required" placeholder="请输入管理员姓名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">电话号码</label>
            <div class="layui-input-block">
                <input type="text" name="parentTel" required style="width: 240px" lay-verify="phone" placeholder="请输入电话号码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">密码</label>
            <div class="layui-input-block">
                <input type="password" name="parentPwd" id="firstpwd" required style="width: 240px" lay-verify="firstpwd" placeholder="请输入密码" autocomplete="off" class="layui-input">
                <!-- <input type="hidden" name="id" style="width: 240px" autocomplete="off" class="layui-input"> -->
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">再次输入密码</label>
            <div class="layui-input-block">
                <input type="password"  id="secondpwd" required style="width: 240px" lay-verify="secondpwd" placeholder="请再次输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="save" >提交</button>
                <button class="layui-btn layui-btn-primary" id="closeBtn" >关闭</button>
            </div>
        </div>
    </form>
</div>
<script src="${path}/static/plugins/layui/layui.js"></script>
<script type="text/javascript" src="${path}/static/plugins/jquery/jquery.min.js"></script>
<script>
    function onAddBtn(){
        //页面层-自定义
        layer.open({
            type: 1,
            title:"注册家长账号",
            closeBtn: false,
            shift: 2,
            area: ['400px', '300px'],
            shadeClose: true,
            content: $("#add-main"),
            success: function(layero, index){},
            yes:function(){
            }
        });
    }

    //JavaScript代码区域
    layui.use(['element','layer','form'], function () {
        var element = layui.element;
        var layer = layui.layer;
        var $ = layui.jquery;
        var form = layui.form;

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
        form.on('submit(save)', function (data) {
            params = data.field;
            alert(JSON.stringify(params));
            submit($,params);
            return false;
        });
        var obj = document.getElementById('closeBtn');
        obj.addEventListener('click', function cancel(){
            CloseWin();
        });
        //提交
        function submit($,params){
            $.post('${path}/parent/register', params, function (res) {
                if (res.status==1) {
                    layer.msg(res.message,{icon:1},function(index){
                        CloseWin();
                    })
                }else if(res.status==2){
                    layer.msg(res.message,{icon:0},function(){
                        CloseWin();
                    })
                }else{
                    layer.msg(res.message,{icon:0},function(){
                        location.reload(); // 页面刷新
                        return false
                    })
                }
            }, 'json');
        }
        debugger;
        //关闭页面
        function CloseWin(){
            parent.location.reload(); // 父页面刷新
            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.layer.close(index); //再执行关闭
        }

    });

    function changeVerifyCode(img){
        img.src="${path}/Kaptcha?" + Math.floor(Math.random()*100);
    }
</script>
</body>

</html>