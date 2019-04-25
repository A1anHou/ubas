<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/4/24
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree"  lay-filter="test">
            <li class="layui-nav-item"><a href="#" onclick="">应用数据</a></li>
            <li class="layui-nav-item"><a href="#">位置数据</a></li>
        </ul>
    </div>
</div>
<script>
    function onShowData() {
        var date = new Date();
        var userId = ${userId};
        var formatDate = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
        window.location.href="${path}/parent/showData?userId="+userId+"&formatDate="+formatDate;
        window.event.returnValue = false
    }
    function onShowLocation() {
        var date = new Date();
        var userId = ${userId};
        var formatDate = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
        window.location.href="${path}/parent/showLocation?userId="+userId+"&formatDate="+formatDate;
        window.event.returnValue = false
    }

</script>
