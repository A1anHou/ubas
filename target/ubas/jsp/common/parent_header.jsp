<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/4/12
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-header">
    <a href="${path}/parent/index"><div class="layui-logo">用户行为分析系统</div></a>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
            <a href="javascript:;">
                <img src="${path}/static/custom/image/parent.png" class="layui-nav-img">
                ${sessionScope.SESSION_USER.parentName}
            </a>
            <dl class="layui-nav-child">
                <dd><a href="${path}/parent/myInfo">我的信息</a></dd>
                <dd><a href="${path}/parent/editMyInfo">账号设置</a></dd>
                <dd><a href="${path}/parent/logout">退出登录</a></dd>
            </dl>
        </li>
    </ul>
</div>
