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
        <table class="layui-table" lay-skin="line">
            <tr>
                <th>图标</th>
                <th>应用名</th>
                <th>包名</th>
                <th>类别</th>
                <th>添加时间</th>
            </tr>

            <c:if test="${!empty appList}">
                <c:forEach var="app" items="${appList}">
                    <tr>
                        <td><img src="${app.appIcon}" style="max-width: 30px"></td>
                        <td>${app.appName}</td>
                        <td>${app.appPackage}</td>
                        <td>${app.appType}</td>
                        <td>${app.appAddTime}</td>
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
