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
    <title>用户行为分析系统 | 管理员</title>
    <!--引入CSS-->
    <link rel="stylesheet" href="${path}/static/plugins/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <jsp:include page="../common/admin_header.jsp"></jsp:include>

    <div class="layui-body" style="left:0">
        <div class="layui-card">
            <div class="layui-card-header"><h5>个人信息</h5></div>
            <div class="layui-card-body">
                <h2>ID：${admin.adminId}</h2>
                <br/>
                <h2>姓名：${admin.adminName}</h2>
                <br/>
                <h2>身份：管理员</h2>
                <br/>
                <h2>电话：${admin.adminTel}</h2>
                <br/>
                <h2>注册时间：${admin.adminRegTime}</h2>
            </div>
        </div>
        <div style="text-align: center">
            <a href="${path}/admin/index" class="layui-btn">确定</a>
            <a href="${path}/admin/editMyInfo" class="layui-btn layui-btn-warm">修改</a>
            <a href="" id="delete" class="layui-btn layui-btn-danger">删除</a>
        </div>
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

    $(function () {
        $('#delete').click(function () {
            var r=confirm("确认删除？");
            if (r==true) {
                var obj = {
                    'adminName':${admin.adminId},
                };

                $.ajax({
                    url:'${path}/admin/delAdmin',
                    type:'post',
                    data:JSON.stringify(obj),
                    contentType:"application/json",
                    success:function (data) {
                        alert("删除成功！");
                        window.location.href="${path}/jsp/login.jsp";
                    }
                })
            }
        })
    })
</script>
</body>
</html>
