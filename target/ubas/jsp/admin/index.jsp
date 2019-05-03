<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/4/12
  Time: 18:36
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
    <link rel="shortcut icon" href="${path}/static/custom/image/favicon.ico" />
    <link rel="bookmark"href="${path}/static/custom/image/favicon.ico" />
    <!--引入CSS-->
    <link rel="stylesheet" href="${path}/static/plugins/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <jsp:include page="../common/admin_header.jsp"></jsp:include>

    <div class="layui-body" style="left: 0">
        <!-- 内容主体区域 -->
        <!-- BEGIN PAGE CONTAINER -->
        <div class="layui-container">
            <!-- BEGIN PAGE HEAD -->
            <div class="layui-row">
                <h2>情况概览</h2>
            </div>
            <!-- END PAGE HEAD -->
            <!-- BEGIN PAGE CONTENT -->
            <div class="layui-row">
                <!-- 图表-->
                <div class="layui-row " style="margin-top: 10px">
                    <div class="layui-col-md6">
                        <div id="userChart" style="width: 550px; height: 400px; margin: 0 auto"></div>
                    </div>
                    <div class="layui-col-md6">
                        <div id="appChart" style="width: 550px; height: 400px; margin: 0 auto"></div>
                    </div>
                </div>

                <div class="layui-row layui-col-space10" style="margin-top: 10px">
                    <div class="layui-col-md6 layui-col-sm12">
                        <!-- BEGIN PORTLET-->
                        <div class="layui-table-header">
                            <div class="layui-col-xs11">
                                <i class="layui-icon">&#xe770;</i>
                                <span class="caption-subject theme-font bold uppercase">新增用户</span>
                            </div>
                            <div class="layui-col-xs1">
                                <a href="${path}/admin/user" class="caption-subject theme-font bold">More>></a>
                            </div>
                            <div class="table-scrollable table-scrollable-borderless">
                                <table class="layui-table" lay-skin="line">
                                    <tr>
                                        <th>
                                            ID
                                        </th>
                                        <th>
                                            用户名
                                        </th>
                                        <th>
                                            手机号
                                        </th>
                                        <th>
                                            年龄
                                        </th>
                                        <th>
                                            性别
                                        </th>
                                        <th>
                                            注册时间
                                        </th>
                                    </tr>
                                    <c:if test="${!empty userList}">
                                        <c:forEach var="user" items="${userList}">
                                            <tr>
                                                <td>${user.userId}</td>
                                                <td>${user.userName}</td>
                                                <td>${user.userTel}</td>
                                                <td>${currentYear-user.userBirthday.year}</td>
                                                <td>
                                                    <c:if test="${user.userGender == 0}">
                                                        <i class="layui-icon">&#xe661;</i>
                                                    </c:if>
                                                    <c:if test="${user.userGender == 1}">
                                                        <i class="layui-icon">&#xe662;</i>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <fmt:formatDate value="${user.userRegTime}"  type="both" />
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </table>
                            </div>
                        </div>
                        <!-- END PORTLET-->
                    </div>
                    <div class="layui-col-md6 layui-col-sm12">
                        <!-- BEGIN PORTLET-->
                        <div class="layui-table-header">
                            <div class="layui-col-xs11">
                                <i class="layui-icon">&#xe653;</i>
                                <span class="caption-subject theme-font bold uppercase">新增应用</span>
                            </div>
                            <div class="layui-col-xs1">
                                <a href="${path}/admin/app" class="caption-subject theme-font bold">More>></a>
                            </div>
                            <div class="table-scrollable table-scrollable-borderless">
                                <table class="layui-table" lay-skin="line">
                                    <tr>
                                        <th>
                                            ID
                                        </th>
                                        <th>
                                            应用名
                                        </th>
                                        <th>
                                            类别
                                        </th>
                                        <th>
                                            添加时间
                                        </th>
                                        <th>
                                            操作
                                        </th>
                                    </tr>
                                    <c:if test="${!empty appList}">
                                        <c:forEach var="app" items="${appList}">
                                            <tr>
                                                <td>${app.appId}</td>
                                                <td>${app.appName}</td>
                                                <td>${app.appType}</td>
                                                <td>
                                                    <fmt:formatDate value="${app.appAddTime}"  type="both" />
                                                </td>
                                                <td>
                                                    <a onclick="onEditBtn(${app.appId},'${app.appName}','${app.appPackage}','${app.appType}')">
                                                        <i class="layui-icon layui-icon-edit"></i>
                                                    </a>
                                                    <a onclick="onDelBtn(${app.appId})">
                                                        <i class="layui-icon layui-icon-delete"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </table>
                            </div>
                        </div>
                        <!-- END PORTLET-->
                    </div>
                </div>
                <!-- END PAGE CONTENT INNER -->
            </div>
            <!-- END PAGE CONTENT -->
        </div>
        <!-- END PAGE CONTAINER -->
    </div>

    <jsp:include page="../common/admin_footer.jsp"></jsp:include>
</div>
<div id="edit-main" style="display: none;">
    <form class="layui-form" id="add-form" action="">
        <div class="layui-form-item center" >
            <label class="layui-form-label" style="width: 100px" >应用ID</label>
            <div class="layui-input-block">
                <input type="text" name="appId" id="appId" style="width: 240px"  autocomplete="off" class="layui-input layui-disabled" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">应用名称</label>
            <div class="layui-input-block">
                <input type="text" name="appName" id="appName" required style="width: 240px" autocomplete="off" class="layui-input layui-disabled" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">应用包名</label>
            <div class="layui-input-block">
                <input type="text" name="appPackage" id="appPackage" required style="width: 240px" autocomplete="off" class="layui-input layui-disabled" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">应用类别</label>
            <div class="layui-input-block">
                <input type="text" name="appType" id="appType" required style="width: 240px" lay-verify="required" placeholder="请输入应用类别" autocomplete="off" class="layui-input">
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
<script src="${path}/static/plugins/highcharts/highcharts.js"></script>
<script>
    //JavaScript代码区域
    function onEditBtn(id,name,packageVal,type){
        $("#appId").val(id);
        $("#appName").val(name);
        $("#appPackage").val(packageVal);
        $("#appType").val(type);
        //页面层-自定义
        layer.open({
            type: 1,
            title:"编辑应用类别",
            closeBtn: false,
            shift: 2,
            area: ['400px', '300px'],
            shadeClose: true,
            content: $("#edit-main"),
            success: function(layero, index){
            },
            yes:function(){
            }
        });
    }
    function onDelBtn(id){
        var r=confirm("确认删除？");
        if (r==true) {
            window.location.href="${path}/admin/delApp?appId="+id;
            window.event.returnValue=false;
        }
    }
    //JavaScript代码区域
    layui.use(['element','layer','form'], function () {
        var element = layui.element;
        var layer = layui.layer,
        $ = layui.jquery,
        form = layui.form;


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
            $.post('${path}/admin/editApp', params, function (res) {
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
            window.location.href = "${path}/admin/index";
            window.event.returnValue = false
        }

    });

    $(document).ready(function () {
        var title = {
            text: '新增用户'
        };
        var subtitle = {
            text: '近一周用户新增情况'
        };
        var xAxis = {
            categories: ${lastWeek}
        };
        var yAxis = {
            title: {
                text: '人数 (人)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        };

        var tooltip = {
            valueSuffix: '人'
        }

        var legend = {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        };

        var series = [
            {
                name: '用户新增',
                data: ${newUser}
            }
        ];

        var json = {};

        json.title = title;
        json.subtitle = subtitle;
        json.xAxis = xAxis;
        json.yAxis = yAxis;
        json.tooltip = tooltip;
        json.legend = legend;
        json.series = series;

        $('#userChart').highcharts(json);
    });
    $(document).ready(function () {
        var title = {
            text: '新增应用'
        };
        var subtitle = {
            text: '近一周应用新增情况'
        };
        var xAxis = {
            categories: ${lastWeek}
        };
        var yAxis = {
            title: {
                text: '数量 (个)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        };

        var tooltip = {
            valueSuffix: '个'
        };

        var legend = {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        };
        var series = [
            {
                name: '应用新增',
                data: ${newApp}
            }
        ];

        var json = {};

        json.title = title;
        json.subtitle = subtitle;
        json.xAxis = xAxis;
        json.yAxis = yAxis;
        json.tooltip = tooltip;
        json.legend = legend;
        json.series = series;
        Highcharts.setOptions({
            colors: ['#64E572', '#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5',  '#FF9655',
                '#FFF263', '#6AF9C4']
        });
        $('#appChart').highcharts(json);
    });
</script>
</body>
</html>
