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

        </div>
        <table class="layui-table">
            <tr>
                <th>ID</th>
                <th>姓名</th>
                <th>电话</th>
                <th>注册时间</th>
                <th>操作</th>
            </tr>

            <c:if test="${!empty adminList}">
                <c:forEach var="admin" items="${adminList}">
                    <tr>
                        <td>${admin.adminId}</td>
                        <td>${admin.adminName}</td>
                        <td>${admin.adminTel}</td>
                        <td>${admin.adminRegTime}</td>
                        <td><i class="layui-icon-del">&#xe60c;</i> </td>
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
