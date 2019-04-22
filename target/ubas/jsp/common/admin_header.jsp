<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/4/12
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-header">
    <a href="${path}/admin/index"><div class="layui-logo">用户行为分析系统</div></a>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
        <li class="layui-nav-item"><a href="${path}/admin/index">数据概览</a></li>
        <li class="layui-nav-item"><a href="${path}/admin/app">应用管理</a></li>
        <li class="layui-nav-item">
            <a href="javascript:;">用户管理</a>
            <dl class="layui-nav-child">
                <dd><a href="${path}/admin/user">普通用户</a></dd>
                <dd><a href="${path}/admin/parent">家长用户</a></dd>
                <dd><a href="${path}/admin/admin">管理员用户</a></dd>
            </dl>
        </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
            <a href="javascript:;">
                <img src="${path}/static/custom/image/admin.png" class="layui-nav-img">
                ${sessionScope.SESSION_USER.adminName}
            </a>
            <dl class="layui-nav-child">
                <dd><a href="${path}/admin/myInfo">我的信息</a></dd>
                <dd><a href="${path}/admin/editMyInfo">账号设置</a></dd>
                <dd><a href="${path}/admin/logout">退出登录</a></dd>
            </dl>
        </li>
    </ul>
</div>
