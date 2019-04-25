<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/4/24
  Time: 19:00
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
<style>
    #container {width:1080px; height: 720px;}
</style>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <jsp:include page="../common/parent_header.jsp"></jsp:include>
    <div class="layui-body" style="left:0">
        <!-- 内容主体区域 -->
        <div class="layui-container" style="padding: 15px;">
            <div class="layui-row">
                <h3>
                    ${user.userName}&nbsp<fmt:formatDate value="${date}" type="date" pattern="yyyy年MM月dd日"/>&nbsp的数据
                    <small class="layui-inline" style="float: right;">
                        <form class="layui-form" action="${path}/parent/showData">
                            <input type="hidden" name="userId" value="${user.userId}"/>
                            <div class="layui-inline">
                                <input type="text" class="layui-input" id="date-picker" name="formatDate"
                                       style="max-width: 100px" placeholder="点此更改日期"/>
                            </div>
                            <button class="layui-btn" style="float: right">确定</button>
                        </form>
                    </small>
                </h3>
            </div>
            <div class="layui-row" style="margin-top: 20px">
                <div class="layui-col-md11">
                    <div class="layui-row">
                        <div id="barChart" style="min-width:400px;height:400px"></div>
                    </div>
                    <hr/>
                    <div class="layui-row">
                        <div class="layui-tab">
                            <ul class="layui-tab-title">
                                <li class="layui-this">按应用查看</li>
                                <li>按类别查看</li>
                            </ul>
                            <div class="layui-tab-content">
                                <div class="layui-tab-item layui-show">
                                    <div class="layui-col-md6">
                                        <h3>最常使用</h3>
                                        <table class="layui-table" lay-even lay-skin="nob">
                                            <tr>
                                                <th>图标</th>
                                                <th>应用名</th>
                                                <th>类别</th>
                                                <th>使用时长</th>
                                            </tr>
                                            <c:if test="${!empty appList}">
                                                <c:forEach var="app" items="${appList}">
                                                    <tr>
                                                        <td><img src="${app.appIcon}" style="max-width: 30px"></td>
                                                        <td>${app.appName}</td>
                                                        <td>${app.appType}</td>
                                                        <td>
                                                                ${app.useTime}分钟
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                        </table>
                                    </div>
                                    <div class="layui-col-md6">
                                        <div id="pieChart" style="min-width:400px;height:400px"></div>
                                    </div>
                                </div>
                                <div class="layui-tab-item">
                                    <div class="layui-col-md6">
                                        <h3>最常使用</h3>
                                        <table class="layui-table" lay-even lay-skin="nob">
                                            <tr>
                                                <th>应用类别</th>
                                                <th>使用时长</th>
                                            </tr>
                                            <c:if test="${!empty typeList}">
                                                <c:forEach var="type" items="${typeList}">
                                                    <tr>
                                                        <td>${type.name}</td>
                                                        <td>
                                                                ${type.typeUseTime}分钟
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                        </table>
                                    </div>
                                    <div class="layui-col-md6">
                                        <div id="pieChart2" style="min-width:400px;height:400px"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr/>
                    <div class="layui-row">
                        <fieldset class="layui-elem-field">
                            <legend>位置信息 - 位置点${locationList.size()-1}处</legend>
                            <div class="layui-field-box">
                                <div id="container"></div>
                            </div>
                        </fieldset>

                    </div>
                </div>
                <div class="layui-col-md1">
                    <h3>解锁情况</h3>
                    <div style="margin-top: 20px">
                        <h5>共解锁了${unlockList.size()}次</h5>
                        <hr/>
                        <ul class="layui-timeline">
                            <c:if test="${!empty unlockList}">
                                <c:forEach var="unlock" items="${unlockList}">
                                    <li class="layui-timeline-item">
                                        <i class="layui-icon layui-timeline-axis">&#xe673;</i>
                                        <div class="layui-timeline-content layui-text">
                                            <h3 class="layui-timeline-title">
                                                <fmt:formatDate value="${unlock.unlockTime}" type="date"
                                                pattern="HH:ss:mm"/>
                                            </h3>
                                        </div>
                                    </li>
                                </c:forEach>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../common/parent_footer.jsp"></jsp:include>
</div>
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.14&key=24ddea4abd229753e12e248ac49e3da5"></script>
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.14&key=24ddea4abd229753e12e248ac49e3da5&plugin=AMap.ToolBar"></script>
<script src="${path}/static/plugins/layui/layui.js"></script>
<script src="${path}/static/plugins/jquery/jquery.min.js"></script>
<script src="${path}/static/plugins/highcharts/highcharts.js"></script>
<script>
    var map = new AMap.Map('container', {
        resizeEnable: true
    });
    var listSize = ${locationList.size()};
    if(listSize!=0){
        for (var i=0;i<listSize;i++){
            var marker = new AMap.Marker({
                position: new AMap.LngLat(${locationList.get(i).longitude}, ${locationList.get(i).latitude}),   // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
                title: ${locationList.get(i).description}
            });
            if(i!=0){
                map.add(marker);
            }
        }
    }
    layui.use(['element', 'laydate','form'], function () {
        var element = layui.element;
        var laydate = layui.laydate;
        var form = layui.form;

        //执行一个laydate实例
        laydate.render({
            elem: '#date-picker' //指定元素
        });
    });

    $(document).ready(function () {
        var chart = {
            type: 'column'
        };
        var title = {
            text: '共使用手机${totalUseTime}分钟'
        };
        var xAxis = {
            categories: ['0:00~1:00', '1:00~2:00', '2:00~3:00', '3:00~4:00', '4:00~5:00', '5:00~6:00',
                '6:00~7:00', '7:00~8:00', '8:00~9:00', '9:00~10:00', '10:00~11:00', '11:00~12:00',
                '12:00~13:00', '13:00~14:00', '14:00~15:00', '15:00~16:00', '16:00~17:00', '17:00~18:00',
                '18:00~19:00', '19:00~20:00', '20:00~21:00', '21:00~22:00', '22:00~23:00', '23:00~24:00'
            ]
        };
        var yAxis = {
            min: 0,
            title: {
                text: '手机使用情况(分钟)'
            },
            stackLabels: {  // 堆叠数据标签
                enabled: true,
                style: {
                    fontWeight: 'bold',
                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                }
            }
        };

        var tooltip = {
            formatter: function () {
                return '<b>' + this.x + '</b><br/>' +
                    this.series.name + ': ' + this.y + '分钟' + '<br/>' +
                    '总时长: ' + this.point.stackTotal + '分钟';
            }
        };

        var legend = {
            align: 'right',
            x: -30,
            verticalAlign: 'top',
            y: 25,
            floating: true,
            backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
            borderColor: '#CCC',
            borderWidth: 1,
            shadow: false
        };

        var plotOptions = {
            column: {
                stacking: 'normal',
                dataLabels: {
                    enabled: true,
                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                    style: {
                        // 如果不需要数据标签阴影，可以将 textOutline 设置为 'none'
                        textOutline: '1px 1px black'
                    }
                }
            }
        };

        var series = ${barDateList};

        var json = {};

        json.chart = chart;
        json.title = title;
        json.xAxis = xAxis;
        json.yAxis = yAxis;
        json.tooltip = tooltip;
        json.legend = legend;
        json.plotOptions = plotOptions;
        json.series = series;

        $('#barChart').highcharts(json);
    });
    $(document).ready(function () {
        var chart = {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        };
        var title = {
            text: '应用使用情况'
        };

        var tooltip = {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        };

        var plotOptions = {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        };

        var series = ${pieDateList};

        var json = {};

        json.chart = chart;
        json.title = title;
        json.tooltip = tooltip;
        json.plotOptions = plotOptions;
        json.series = series;

        $('#pieChart').highcharts(json);
    });
    $(document).ready(function () {
        var chart = {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        };
        var title = {
            text: '应用使用情况'
        };

        var tooltip = {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        };

        var plotOptions = {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        };

        var series = ${pieDateList2};

        var json = {};

        json.chart = chart;
        json.title = title;
        json.tooltip = tooltip;
        json.plotOptions = plotOptions;
        json.series = series;

        $('#pieChart2').highcharts(json);
    });

</script>
</body>
</html>
