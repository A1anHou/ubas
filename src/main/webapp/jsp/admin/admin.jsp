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
            <button class="layui-btn"  id="addBtn" data-type="getInfo" style="float: left;" onclick="onAddBtn()">
                <i class="layui-icon">&#xe608;</i> 添加
            </button>
            <form class="layui-form" action="${path}/admin/searchAdmin">
                <button type="submit" class="layui-btn layui-btn-radius" id="searchBtn" lay-submit lay-filter="search" style="float: right;">搜索</button>
                <div class="layui-input-block" style="float: right; position: relative;">
                    <label class="layui-form-label">管理员搜索</label>
                    <input style="width: auto;" type="text" id="searchKeyword" name="keyword" lay-verify="required" placeholder="请输入管理员ID或姓名" autocomplete="off" class="layui-input">
                </div>
            </form>
        </div>
        <table class="layui-table" lay-skin="line">
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
                        <td>
                            <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="onDelBtn(${admin.adminId})">
                                <i class="layui-icon">&#xe640;</i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
    <jsp:include page="../common/admin_footer.jsp"></jsp:include>
</div>
<div id="add-main" style="display: none;">
    <form class="layui-form" id="add-form" action="">
        <div class="layui-form-item center" >
            <label class="layui-form-label" style="width: 100px" >管理员姓名</label>
            <div class="layui-input-block">
                <input type="text" name="adminName" required value="" style="width: 240px" lay-verify="required" placeholder="请输入管理员姓名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">电话号码</label>
            <div class="layui-input-block">
                <input type="text" name="adminTel" required style="width: 240px" lay-verify="phone" placeholder="请输入电话号码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">密码</label>
            <div class="layui-input-block">
                <input type="password" name="adminPwd" id="firstpwd" required style="width: 240px" lay-verify="firstpwd" placeholder="请输入密码" autocomplete="off" class="layui-input">
                <!-- <input type="hidden" name="id" style="width: 240px" autocomplete="off" class="layui-input"> -->
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">再次输入密码</label>
            <div class="layui-input-block">
                <input type="password"  id="secondpwd" required style="width: 240px" lay-verify="secondpwd" placeholder="请再次输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="save" >提交</button>
                <button class="layui-btn layui-btn-primary" id="closeBtn" >关闭</button>
            </div>
        </div>
    </form>
</div>
<script src="${path}/static/plugins/layui/layui.js"></script>
<script src="${path}/static/plugins/jquery/jquery.min.js"></script>
<script>
    function onAddBtn(){
        //页面层-自定义
        layer.open({
            type: 1,
            title:"新增管理员",
            closeBtn: false,
            shift: 2,
            area: ['400px', '300px'],
            shadeClose: true,
            content: $("#add-main"),
            success: function(layero, index){},
            yes:function(){
            }
        });
    }
    function onDelBtn(id){
        var r=confirm("确认删除？");
        if (r==true) {
            window.location.href="${path}/admin/delAdmin?adminId="+id;
            window.event.returnValue=false;
        }
    }
    //JavaScript代码区域
    layui.use(['element','layer','form'], function () {
        var element = layui.element;
        var layer = layui.layer;
        $ = layui.jquery;
        form = layui.form;

        //表单验证
        form.verify({
            firstpwd: [/(.+){6,12}$/, '密码必须6到12位'],
            secondpwd: function(value) {
                if(value != $("#firstpwd").val()){
                    $("#secondpwd").val("");
                    return '确认密码与密码不一致';
                }
            }
        });
        //提交监听事件
        form.on('submit(save)', function (data) {
            params = data.field;
            /*alert(JSON.stringify(params))*/
            submit($,params);
            return false;
        });
        var obj = document.getElementById('closeBtn');
        obj.addEventListener('click', function cancel(){
            CloseWin();
        });
    //提交
    function submit($,params){
        $.post('${path}/admin/addAdmin ', params, function (res) {
            if (res.status==1) {
                layer.msg(res.message,{icon:1},function(index){
                    CloseWin();
                })
            }else if(res.status==2){
                layer.msg(res.message,{icon:0},function(){
                    parent.location.href='${path}/admin/index';
                    CloseWin();
                })
            }else{
                layer.msg(res.message,{icon:0},function(){
                    location.reload(); // 页面刷新
                    return false
                })
            }
        }, 'json');
    }
    debugger;
    //关闭页面
    function CloseWin(){
        parent.location.reload(); // 父页面刷新
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    }

    });
</script>
</body>
</html>
