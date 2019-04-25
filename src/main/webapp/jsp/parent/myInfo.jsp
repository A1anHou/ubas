<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/4/19
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>用户行为分析系统 | 家长</title>
    <!--引入CSS-->
    <link rel="stylesheet" href="${path}/static/plugins/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <jsp:include page="../common/parent_header.jsp"></jsp:include>

    <div class="layui-body" style="left:0">
        <div class="layui-card">
            <div class="layui-card-header"><h5>个人信息</h5></div>
            <div class="layui-card-body">
                <h2>ID：${parent.parentId}</h2>
                <br/>
                <h2>姓名：${parent.parentName}</h2>
                <br/>
                <h2>身份：家长</h2>
                <br/>
                <h2>电话：${parent.parentTel}</h2>
                <br/>
                <h2>注册时间：
                    <fmt:formatDate value="${parent.parentRegTime}" type="date" pattern="yyyy年MM月dd日 HH:ss:mm"/>
                </h2>
            </div>
        </div>
        <div style="text-align: center">
            <a href="${path}/parent/index" class="layui-btn">确定</a>
            <a href="${path}/parent/editMyInfo" class="layui-btn layui-btn-warm">修改</a>
        </div>
    </div>
    <jsp:include page="../common/parent_footer.jsp"></jsp:include>
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
