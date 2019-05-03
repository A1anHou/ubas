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
    <link rel="shortcut icon" href="${path}/static/custom/image/favicon.ico" />
    <link rel="bookmark"href="${path}/static/custom/image/favicon.ico" />
    <link rel="stylesheet" href="${path}/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" href="${path}/static/custom/css/admin_login.css">
</head>

<body>
<div id="container">

    <div class="admin-login-background">
        <div class="admin-header">
            <h1>用户行为分析系统</h1>
        </div>
        <form class="layui-form" action="${path}/admin/login" method="post">
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
            </div>
            <button class="layui-btn admin-button" lay-submit lay-filter="formDemo" id="admin_login">登录</button>


        </form>
    </div>
</div>
<script src="${path}/static/plugins/layui/layui.js"></script>
<script type="text/javascript" src="${path}/static/plugins/jquery/jquery.min.js"></script>
<script>

    function changeVerifyCode(img){
        img.src="${path}/Kaptcha?" + Math.floor(Math.random()*100);
    }
</script>
</body>

</html>