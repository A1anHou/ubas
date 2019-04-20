<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/4/12
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <!-- 内容主体区域 -->
        <!-- BEGIN PAGE CONTAINER -->
        <div class="layui-container">
            <!-- BEGIN PAGE HEAD -->
            <div class="layui-row">
                <div class="page-title">
                    <h1>情况概览
                        <small>新增用户 & 新增应用</small>
                    </h1>
                </div>
            </div>
            <!-- END PAGE HEAD -->
            <!-- BEGIN PAGE CONTENT -->
            <div class="layui-row">
                <!-- BEGIN PAGE CONTENT INNER -->
                <div class="layui-row " style="margin-top: 10px">
                    <div class="layui-col-md12">
                        <div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
                    </div>
                </div>

                <div class="layui-row layui-col-space10" style="margin-top: 10px">
                    <div class="layui-col-md6 layui-col-sm12">
                        <!-- BEGIN PORTLET-->
                        <div class="portlet light ">
                            <div class="layui-table-header layui-col-xs11">
                                <div class="caption caption-md">
                                    <i class="icon-bar-chart theme-font hide"></i>
                                    <span class="caption-subject theme-font bold uppercase">新增用户</span>
                                </div>
                            </div>
                            <div class="layui-table-header layui-col-xs1">
                                <div class="caption caption-md">
                                    <a href="#" class="caption-subject theme-font bold">More>></a>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div class="table-scrollable table-scrollable-borderless">
                                    <table class="layui-table" lay-skin="line">
                                        <thead>
                                        <tr class="uppercase">
                                            <th colspan="2">
                                                用户名
                                            </th>
                                            <th>
                                                邮箱
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
                                            <th>
                                                操作
                                            </th>
                                        </tr>
                                        </thead>
                                        <tr>
                                            <td class="fit">
                                                <img class="user-pic" src="../../assets/admin/layout3/img/avatar4.jpg">
                                            </td>
                                            <td>
                                                <a href="javascript:;" class="primary-link">Brain</a>
                                            </td>
                                            <td>
                                                brain@163.com
                                            </td>
                                            <td>
                                                25
                                            </td>
                                            <td>
                                                男
                                            </td>
                                            <td>
                                                <span class="bold theme-font">2019-4-1 8:21</span>
                                            </td>
                                            <td>
                                                <a href="#"><i class="fa fa-eye"></i></a>
                                                &nbsp
                                                <a href="#"><i class="fa fa-cog"></i></a>
                                                &nbsp
                                                <a href="#"><i class="fa fa-ban"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="fit">
                                                <img class="user-pic" src="../../assets/admin/layout3/img/avatar5.jpg">
                                            </td>
                                            <td>
                                                <a href="javascript:;" class="primary-link">Nick</a>
                                            </td>
                                            <td>
                                                nick@163.com
                                            </td>
                                            <td>
                                                32
                                            </td>
                                            <td>
                                                男
                                            </td>
                                            <td>
                                                <span class="bold theme-font">2019-3-31 10:5</span>
                                            </td>
                                            <td>
                                                <a href="#"><i class="fa fa-eye"></i></a>
                                                &nbsp
                                                <a href="#"><i class="fa fa-cog"></i></a>
                                                &nbsp
                                                <a href="#"><i class="fa fa-ban"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="fit">
                                                <img class="user-pic" src="../../assets/admin/layout3/img/avatar6.jpg">
                                            </td>
                                            <td>
                                                <a href="javascript:;" class="primary-link">Janny</a>
                                            </td>
                                            <td>
                                                janny@163.com
                                            </td>
                                            <td>
                                                22
                                            </td>
                                            <td>
                                                女
                                            </td>
                                            <td>
                                                <span class="bold theme-font">2019-3-30 22:15</span>
                                            </td>
                                            <td>
                                                <a href="#"><i class="fa fa-eye"></i></a>
                                                &nbsp
                                                <a href="#"><i class="fa fa-cog"></i></a>
                                                &nbsp
                                                <a href="#"><i class="fa fa-ban"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="fit">
                                                <img class="user-pic" src="../../assets/admin/layout3/img/avatar7.jpg">
                                            </td>
                                            <td>
                                                <a href="javascript:;" class="primary-link">Tom</a>
                                            </td>
                                            <td>
                                                tom@163.com
                                            </td>
                                            <td>
                                                31
                                            </td>
                                            <td>
                                                男
                                            </td>
                                            <td>
                                                <span class="bold theme-font">2019-3-30 18:12</span>
                                            </td>
                                            <td>
                                                <a href="#"><i class="fa fa-eye"></i></a>
                                                &nbsp
                                                <a href="#"><i class="fa fa-cog"></i></a>
                                                &nbsp
                                                <a href="#"><i class="fa fa-ban"></i></a>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <!-- END PORTLET-->
                    </div>
                    <div class="layui-col-md6 layui-col-sm12">
                        <!-- BEGIN PORTLET-->
                        <div class="portlet light ">
                            <div class="layui-table-header layui-col-xs11">
                                <div class="caption caption-md">
                                    <i class="icon-bar-chart theme-font hide"></i>
                                    <span class="caption-subject theme-font bold uppercase">新收录的应用</span>
                                </div>
                            </div>
                            <div class="layui-table-header layui-col-xs1">
                                <div class="caption caption-md">
                                    <a href="#" class="caption-subject theme-font bold">More>></a>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div class="table-scrollable table-scrollable-borderless">
                                    <table class="layui-table" lay-skin="line">
                                        <thead>
                                        <tr class="uppercase">
                                            <th colspan="2">
                                                应用名
                                            </th>
                                            <th>
                                                类别
                                            </th>
                                            <th>
                                                来源
                                            </th>
                                            <th>
                                                收录时间
                                            </th>
                                            <th>
                                                操作
                                            </th>
                                        </tr>
                                        </thead>
                                        <tr>
                                            <td class="fit">
                                                <img class="user-pic" src="../../assets/admin/layout3/img/app1.png">
                                            </td>
                                            <td>
                                                <a href="javascript:;" class="primary-link">QQ</a>
                                            </td>
                                            <td>
                                                社交
                                            </td>
                                            <td>
                                                <a href="javascript:;" class="primary-link">Nick</a>
                                            </td>
                                            <td>
                                                <span class="bold theme-font">2019/4/2 23:56</span>
                                            </td>
                                            <td>
                                                <a href="#"><i class="fa fa-eye"></i></a>
                                                &nbsp
                                                <a href="#"><i class="fa fa-cog"></i></a>
                                                &nbsp
                                                <a href="#"><i class="fa fa-ban"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="fit">
                                                <img class="user-pic" src="../../assets/admin/layout3/img/app2.png">
                                            </td>
                                            <td>
                                                <a href="javascript:;" class="primary-link">微信</a>
                                            </td>
                                            <td>
                                                未分类
                                            </td>
                                            <td>
                                                <a href="javascript:;" class="primary-link">Janny</a>
                                            </td>
                                            <td>
                                                <span class="bold theme-font">2019/4/1 23:55</span>
                                            </td>
                                            <td>
                                                <a href="#"><i class="fa fa-eye"></i></a>
                                                &nbsp
                                                <a href="#"><i class="fa fa-cog"></i></a>
                                                &nbsp
                                                <a href="#"><i class="fa fa-ban"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="fit">
                                                <img class="user-pic" src="../../assets/admin/layout3/img/app3.png">
                                            </td>
                                            <td>
                                                <a href="javascript:;" class="primary-link">淘宝</a>
                                            </td>
                                            <td>
                                                购物
                                            </td>
                                            <td>
                                                <a href="javascript:;" class="primary-link">Nick</a>
                                            </td>
                                            <td>
                                                <span class="bold theme-font">2019/4/1 23:11</span>
                                            </td>
                                            <td>
                                                <a href="#"><i class="fa fa-eye"></i></a>
                                                &nbsp
                                                <a href="#"><i class="fa fa-cog"></i></a>
                                                &nbsp
                                                <a href="#"><i class="fa fa-ban"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="fit">
                                                <img class="user-pic" src="../../assets/admin/layout3/img/app4.png">
                                            </td>
                                            <td>
                                                <a href="javascript:;" class="primary-link">滴滴出行</a>
                                            </td>
                                            <td>
                                                生活
                                            </td>
                                            <td>
                                                <a href="javascript:;" class="primary-link">Tom</a>
                                            </td>
                                            <td>
                                                <span class="bold theme-font">2019/3/31 22:17</span>
                                            </td>
                                            <td>
                                                <a href="#"><i class="fa fa-eye"></i></a>
                                                &nbsp
                                                <a href="#"><i class="fa fa-cog"></i></a>
                                                &nbsp
                                                <a href="#"><i class="fa fa-ban"></i></a>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
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
<script src="${path}/static/plugins/layui/layui.js"></script>
<script src="${path}/static/plugins/jquery/jquery.min.js"></script>
<script src="${path}/static/plugins/highcharts/highcharts.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });

    $(document).ready(function() {
        var title = {
            text: '月平均气温'
        };
        var subtitle = {
            text: 'Source: runoob.com'
        };
        var xAxis = {
            categories: ['一月', '二月', '三月', '四月', '五月', '六月'
                ,'七月', '八月', '九月', '十月', '十一月', '十二月']
        };
        var yAxis = {
            title: {
                text: 'Temperature (\xB0C)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        };

        var tooltip = {
            valueSuffix: '\xB0C'
        }

        var legend = {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        };

        var series =  [
            {
                name: 'Tokyo',
                data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2,
                    26.5, 23.3, 18.3, 13.9, 9.6]
            },
            {
                name: 'New York',
                data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8,
                    24.1, 20.1, 14.1, 8.6, 2.5]
            },
            {
                name: 'Berlin',
                data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6,
                    17.9, 14.3, 9.0, 3.9, 1.0]
            },
            {
                name: 'London',
                data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0,
                    16.6, 14.2, 10.3, 6.6, 4.8]
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

        $('#container').highcharts(json);
    });
</script>
</body>
</html>
