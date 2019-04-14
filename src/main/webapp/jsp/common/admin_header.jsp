<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/4/12
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-header">
    <div class="layui-logo">用户行为分析系统</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
        <li class="layui-nav-item"><a href="">数据概览</a></li>
        <li class="layui-nav-item"><a href="">应用管理</a></li>
        <li class="layui-nav-item">
            <a href="javascript:;">用户管理</a>
            <dl class="layui-nav-child">
                <dd><a href="">普通用户</a></dd>
                <dd><a href="">家长用户</a></dd>
                <dd><a href="">管理员用户</a></dd>
            </dl>
        </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
            <a href="javascript:;">
                <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                Admin
            </a>
            <dl class="layui-nav-child">
                <dd><a href="">我的信息</a></dd>
                <dd><a href="">安全设置</a></dd>
                <dd><a href="">退出登录</a></dd>
            </dl>
        </li>
    </ul>
</div>
