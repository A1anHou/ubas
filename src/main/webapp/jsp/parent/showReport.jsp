<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/5/3
  Time: 16:47
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
    <link rel="shortcut icon" href="${path}/static/custom/image/favicon.ico" />
    <link rel="bookmark"href="${path}/static/custom/image/favicon.ico" />
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
            <button class="layui-btn" onclick="showData(${user.userId})">
                <i class="layui-icon layui-icon-return"></i> 返回
            </button>
            <h2>${user.userName}的周度报告</h2>
            <div class="layui-row">
                <c:if test="${!empty useTime}">
                    <div id="timeChart" ></div>
                </c:if>
                <c:if test="${empty useTime}">
                    <h1>暂无数据</h1>
                </c:if>
            </div>
            <br/>
            <div class="layui-row layui-col-space10">
                <div class="layui-col-md6 layui-anim layui-anim-scale">
                    <h2><i class="layui-icon layui-icon-app"></i> 本周应用时长<span class="layui-badge">Top5</span></h2>
                    <table class="layui-table" lay-even lay-skin="nob">
                            <tr>
                                <th>图标</th>
                                <th>应用名</th>
                                <th>类别</th>
                                <th>开启次数</th>
                                <th>使用时长</th>
                            </tr>
                            <c:if test="${!empty appListTop5}">
                                <c:forEach var="app" items="${appListTop5}">
                                    <tr>
                                        <td><img src="${app.appIcon}" style="max-width: 20px"></td>
                                        <td>${app.appName}</td>
                                        <td>${app.appType}</td>
                                        <td>${app.useStateList.size()}</td>
                                        <td>
                                                ${app.useTime}分钟
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty appListTop5}">
                                <tr><td colspan="5"><h1>暂无数据</h1></td></tr>
                            </c:if>
                    </table>
                </div>
                <div class="layui-col-md6 layui-anim layui-anim-scale">
                    <h2><i class="layui-icon layui-icon-form"></i> 本周类别时长<span class="layui-badge">Top5</span></h2>
                    <table class="layui-table" lay-even lay-skin="nob">
                        <tr>
                            <th>应用类别</th>
                            <th>使用时长</th>
                        </tr>
                        <c:if test="${!empty typeListTop5}">
                            <c:forEach var="type" items="${typeListTop5}">
                                <tr>
                                    <td>${type.name}</td>
                                    <td>
                                            ${type.typeUseTime}分钟
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty typeListTop5}">
                            <tr><td colspan="2"><h1>暂无数据</h1></td></tr>
                        </c:if>
                    </table>
                </div>
            </div>
            <br/>
            <div class="layui-row">
                <fieldset class="layui-elem-field layui-anim layui-anim-up">
                    <legend>
                        <i class="layui-icon layui-icon-about"></i>
                        本周总结
                    </legend>
                    <div class="layui-field-box" style="max-height: 300px">
                        <c:if test="${empty appList}">
                            <h1>暂无数据</h1>
                        </c:if>
                        <c:if test="${!empty appList}">
                            <div id="container">
                                <h2>您的孩子 ${user.userName} 在过去的一周中</h2>
                                <hr/>
                                <div class="layui-col-md6" style="margin-top: 10px">
                                    <div>
                                        <h3><span class="layui-badge-dot"></span>
                                            使用了<span style="color: #FF5722">${appList.size()}</span>款应用，
                                            共计<span style="color: #FF5722">${thisWeekCount}</span>次，
                                            总时长为<span style="color: #FF5722">${thisWeekTotal}</span>分钟
                                        </h3>
                                        <h3>
                                            使用时长相对于上周&nbsp
                                            <c:if test="${thisWeekTotal>lastWeekTotal}">
                                                <i class="layui-icon layui-icon-up" style="color: #FF5722;"></i>
                                                增加了<fmt:formatNumber type="number" value="${(thisWeekTotal-lastWeekTotal)/(lastWeekTotal)*100}" pattern="0.00" maxFractionDigits="2"/>%
                                                <br/>
                                                <i class="layui-icon layui-icon-face-cry" style="color: #FF5722;"></i>请提醒
                                                <c:if test="${user.userGender==0}">她</c:if>
                                                <c:if test="${user.userGender==1}">他</c:if>
                                                注意控制手机使用时间哦！
                                            </c:if>
                                            <c:if test="${thisWeekTotal<lastWeekTotal}">
                                                <i class="layui-icon layui-icon-down" style="color: #5FB878;"></i>
                                                减少了<fmt:formatNumber type="number" value="${(lastWeekTotal-thisWeekTotal)/(lastWeekTotal)*100}" pattern="0.00" maxFractionDigits="2"/>%
                                                <br/>
                                                <i class="layui-icon layui-icon-face-smile" style="color: #5FB878;"></i>
                                                很棒！请再接再厉！
                                            </c:if>
                                            <c:if test="${thisWeekTotal==lastWeekTotal}">
                                                ---&nbsp
                                                保持不变
                                            </c:if>
                                        </h3>
                                    </div>
                                    <br/>
                                    <div>
                                        <h3>
                                            <span class="layui-badge-dot layui-bg-orange"></span>
                                            最喜欢使用<span style="color: #FF5722">${typeListTop5.get(0).name}</span>类应用，有
                                            <fmt:formatNumber type="number" value="${typeListTop5.get(0).typeUseTime/(thisWeekTotal)*100}" pattern="0.00" maxFractionDigits="2"/>%
                                            的时间在使用此类应用
                                        </h3>
                                        <h3>
                                            最喜欢使用的应用是<span style="color: #FF5722">${appListTop5.get(0).appName}</span>，有
                                            <fmt:formatNumber type="number" value="${appListTop5.get(0).useTime/(thisWeekTotal)*100}" pattern="0.00" maxFractionDigits="2"/>%
                                            的时间在使用此应用
                                        </h3>
                                    </div>
                                    <br/>
                                    <div>
                                        <h3>
                                            <span class="layui-badge-dot layui-bg-green"></span>
                                            <c:if test="${thisWeekTotalNightTime==0}">
                                                从不熬夜使用手机，请继续保持！<i class="layui-icon layui-icon-praise"></i>
                                            </c:if>
                                            <c:if test="${thisWeekTotalNightTime!=0}">
                                                熬夜使用手机共<span style="color: #FF5722">${thisWeekTotalNightTime}</span>分钟，占全部使用时间的
                                                <fmt:formatNumber type="number" value="${thisWeekTotalNightTime/(thisWeekTotal)*100}" pattern="0.00" maxFractionDigits="2"/>%
                                                <br/>
                                                熬夜使用手机时间相较于上周
                                                &nbsp
                                                <c:if test="${thisWeekTotalNightTime>lastWeekTotal}">
                                                    <i class="layui-icon layui-icon-up" style="color: #FF5722;"></i>
                                                    增加了<fmt:formatNumber type="number" value="${(thisWeekTotalNightTime-lastWeekTotal)/(lastWeekTotal)*100}" pattern="0.00" maxFractionDigits="2"/>%
                                                    <br/>
                                                </c:if>
                                                <c:if test="${thisWeekTotalNightTime<lastWeekTotal}">
                                                    <i class="layui-icon layui-icon-down" style="color: #5FB878;"></i>
                                                    减少了<fmt:formatNumber type="number" value="${(lastWeekTotal-thisWeekTotalNightTime)/(lastWeekTotal)*100}" pattern="0.00" maxFractionDigits="2"/>%
                                                    <br/>
                                                </c:if>
                                                <c:if test="${thisWeekTotalNightTime==lastWeekTotalNightTime}">
                                                    ---&nbsp
                                                    保持不变
                                                    <br/>
                                                </c:if>
                                                熬夜伤身，请提醒
                                                <c:if test="${user.userGender==0}">她</c:if>
                                                <c:if test="${user.userGender==1}">他</c:if>
                                                注意休息，养成健康的作息习惯！
                                            </c:if>
                                        </h3>
                                    </div>
                                </div>
                                <div class="layui-col-md6" style="margin-top: 10px">
                                    <h2><i class="layui-icon layui-icon-chart-screen" style="color: #c2c2c2;"></i>趋势</h2>
                                    <table class="layui-table" lay-even lay-skin="nob">
                                        <tr><th>使用情况</th><th>变化趋势</th></tr>
                                        <tr>
                                            <td>使用时长：${thisWeekTotal}分钟</td>
                                            <td>相对上周&nbsp
                                                <c:if test="${thisWeekTotal>lastWeekTotal}">
                                                    <i class="layui-icon layui-icon-up" style="color: #FF5722;"></i>
                                                    增加了<fmt:formatNumber type="number" value="${(thisWeekTotal-lastWeekTotal)/(lastWeekTotal)*100}" pattern="0.00" maxFractionDigits="2"/>%
                                                </c:if>
                                                <c:if test="${thisWeekTotal<lastWeekTotal}">
                                                    <i class="layui-icon layui-icon-down" style="color: #5FB878;"></i>
                                                    减少了<fmt:formatNumber type="number" value="${(lastWeekTotal-thisWeekTotal)/(lastWeekTotal)*100}" pattern="0.00" maxFractionDigits="2"/>%
                                                </c:if>
                                                <c:if test="${thisWeekTotal==lastWeekTotal}">
                                                    ---&nbsp
                                                    保持不变
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>使用次数：${thisWeekCount}次</td>
                                            <td>相对上周&nbsp
                                                <c:if test="${thisWeekCount>lastWeekCount}">
                                                    <i class="layui-icon layui-icon-up" style="color: #FF5722;"></i>
                                                    增加了<fmt:formatNumber type="number" value="${(thisWeekCount-lastWeekCount)/(lastWeekCount)*100}" pattern="0.00" maxFractionDigits="2"/>%
                                                </c:if>
                                                <c:if test="${thisWeekCount<lastWeekCount}">
                                                    <i class="layui-icon layui-icon-down" style="color: #5FB878;"></i>
                                                    减少了<fmt:formatNumber type="number" value="${(lastWeekCount-thisWeekCount)/(lastWeekCount)*100}" pattern="0.00" maxFractionDigits="2"/>%
                                                </c:if>
                                                <c:if test="${thisWeekCount==lastWeekCount}">
                                                    ---&nbsp
                                                    保持不变
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>解锁次数：${thisWeekUnlock}次</td>
                                            <td>相对上周&nbsp
                                                <c:if test="${thisWeekUnlock>lastWeekUnlock}">
                                                    <i class="layui-icon layui-icon-up" style="color: #FF5722;"></i>
                                                    增加了<fmt:formatNumber type="number" value="${(thisWeekUnlock-lastWeekUnlock)/(lastWeekUnlock)*100}" pattern="0.00" maxFractionDigits="2"/>%
                                                </c:if>
                                                <c:if test="${thisWeekUnlock<lastWeekUnlock}">
                                                    <i class="layui-icon layui-icon-down" style="color: #5FB878;"></i>
                                                    减少了<fmt:formatNumber type="number" value="${(lastWeekUnlock-thisWeekUnlock)/(lastWeekUnlock)*100}" pattern="0.00" maxFractionDigits="2"/>%
                                                </c:if>
                                                <c:if test="${thisWeekUnlock==lastWeekUnlock}">
                                                    ---&nbsp
                                                    保持不变
                                                </c:if>
                                            </td>
                                        </tr>

                                    </table>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </fieldset>

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
    function showData(userId) {
        var date = new Date();
        var formatDate = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
        window.location.href="${path}/parent/showData?userId="+userId+"&formatDate="+formatDate;
        window.event.returnValue = false
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
        var title = {
            text: '日均使用${thisWeekAverage}分钟'
        };
        var xAxis = {
            categories: ${lastWeek}
        };
        var yAxis = {
            title: {
                text: '时间 (分钟)'
            },
            plotLines:[{
                color:'red',            //线的颜色，定义为红色
                dashStyle:'longdashdot',//标示线的样式，默认是solid（实线），这里定义为长虚线
                value:${totalAverage},                //定义在哪个值上显示标示线，这里是在x轴上刻度为3的值处垂直化一条线
                width:2,                 //标示线的宽度，2px
                label:{
                    text:'历史均值',     //标签的内容
                    align:'left',                //标签的水平位置，水平居左,默认是水平居中center
                    x:10                         //标签相对于被定位的位置水平偏移的像素，重新定位，水平居左10px
                }
            }]
        };

        var tooltip = {
            valueSuffix: '分钟'
        }

        var legend = {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        };

        var series = [
            {
                name: '使用时长',
                data: ${useTime}
            }
        ];

        var json = {};

        json.title = title;
        json.xAxis = xAxis;
        json.yAxis = yAxis;
        json.tooltip = tooltip;
        json.legend = legend;
        json.series = series;

        $('#timeChart').highcharts(json);
    });


</script>
</body>
</html>

