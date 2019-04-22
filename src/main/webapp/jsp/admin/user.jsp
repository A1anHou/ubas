<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/4/15
  Time: 10:57
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
        <div class="layui-table-header">
            <form class="layui-form" action="${path}/admin/searchUser">
                <button type="submit" class="layui-btn layui-btn-radius" id="searchBtn" lay-submit lay-filter="search" style="float: right;">搜索</button>
                <div class="layui-input-block" style="float: right; position: relative;">
                    <label class="layui-form-label">用户搜索</label>
                    <input style="width: auto;" type="text" id="searchKeyword" name="keyword" lay-verify="required" placeholder="请输入用户ID或姓名" autocomplete="off" class="layui-input">
                </div>
            </form>
        </div>
        <table class="layui-table" lay-skin="line">
            <tr>
                <th>ID</th>
                <th>用户名</th>
                <th>用户电话</th>
                <th>用户职业</th>
                <th>性别</th>
                <th>注册时间</th>
                <th>关联家长数</th>
            </tr>

            <c:if test="${!empty userList}">
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td>${user.userId}</td>
                        <td>${user.userName}</td>
                        <td>${user.userTel}</td>
                        <td>${user.userJob}</td>
                        <td>
                            <c:if test="${user.userGender == 0}">
                                女
                            </c:if>
                            <c:if test="${user.userGender == 1}">
                                男
                            </c:if>
                        </td>
                        <td>${user.userRegTime}</td>
                        <td>${parentNum.get(user.userId)}</td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
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
</script>
</body>
</html>
