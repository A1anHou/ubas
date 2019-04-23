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
                <th>年龄</th>
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
                        <td>${currentYear-user.userBirthday.year}</td>
                        <td>
                            <fmt:formatDate value="${ user.userRegTime}"  type="both" />
                        </td>
                        <td>${parentNum.get(user.userId)}</td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
        <div id="laypage" style="text-align: right;padding-right: 5%"></div>
    </div>
    <jsp:include page="../common/admin_footer.jsp"></jsp:include>
</div>
<script src="${path}/static/plugins/layui/layui.js"></script>
<script src="${path}/static/plugins/jquery/jquery.min.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['element','laypage'], function () {
        var element = layui.element;
        var laypage = layui.laypage;

        //分页
        laypage.render({
            elem: 'laypage',
            count: ${pager.totalRecord},//这个是你的总页面
            curr: ${pager.pageNum},
            limit : ${pager.pageSize},//这个是每页面显示多少条，页面跳转后他会自动让下拉框里对应的值设为选中状态
            limits: [5, 10, 20, 50, 100], //这个是下拉框里显示的option
            layout: ['prev', 'page','limit','next'],
            jump: function(obj, first){//这个方法是在你选择页数后触发执行，在这里完成当你点击页码后需要向服务请求数据的操作
                if(first){ return ; }//如果是第一次不执行
                var url = "${path}/admin/user";
                //拼接分页参数和表单下所有带name属性参数、向后台提交数据、可以实现下一页与搜索的内容同时进行
                url += '?pageNum='+obj.curr+'&pageSize='+obj.limit;
                window.location.href = url;
            }
        });

    });
</script>
</body>
</html>
