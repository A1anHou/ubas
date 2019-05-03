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
    <title>用户行为分析系统 | 家长</title>
    <link rel="shortcut icon" href="${path}/static/custom/image/favicon.ico" />
    <link rel="bookmark"href="${path}/static/custom/image/favicon.ico" />
    <!--引入CSS-->
    <link rel="stylesheet" href="${path}/static/plugins/layui/css/layui.css">
</head>
<style>
    .add-button {
        width: 300px;
        height: 50px;
        opacity: 0.5;
    }
</style>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <jsp:include page="../common/parent_header.jsp"></jsp:include>

    <div class="layui-body" style="left: 0">
        <!-- 内容主体区域 -->
        <div class="layui-container" style="padding: 15px;">
            <h1>我的孩子</h1>
            <div class="layui-collapse" lay-accordion style="margin-top: 20px">
                <c:if test="${!empty relationList}">
                    <c:forEach var="relation" items="${relationList}">
                        <c:set var="user" value="${userMap.get(relation.relationId)}"/>
                        <div class="layui-colla-item">
                            <h2 class="layui-colla-title">
                                    ${user.userName}(ID:${user.userId})
                                <button class="layui-btn layui-btn-sm layui-btn-primary"
                                        style="float: right; margin-top: 5px"
                                        onclick="onDelBtn(${relation.relationId})">
                                    <i class="layui-icon">&#xe640;</i> 解除绑定
                                </button>
                                <button class="layui-btn layui-btn-sm " style="float: right; margin-top: 5px" onclick="showData(${user.userId})">
                                    <i class="layui-icon">&#xe629;</i> 查看数据
                                </button>

                            </h2>
                            <div class="layui-colla-content">
                                <ul>
                                    <li>关系：${relation.relationship}</li>
                                    <li>电话：${user.userTel}</li>
                                    <li>
                                        性别：
                                        <c:if test="${user.userGender == 0}">
                                            女
                                        </c:if>
                                        <c:if test="${user.userGender == 1}">
                                            男
                                        </c:if>
                                    </li>
                                    <li>年龄：${currentYear-user.userBirthday.year}岁</li>
                                    <li>工作：${user.userJob}</li>
                                    <li>
                                        注册时间：
                                        <fmt:formatDate value="${user.userRegTime}" type="date"
                                                        pattern="yyyy年MM月dd日 HH:ss:mm"/>
                                    </li>
                                    <li>
                                        关联时间：
                                        <fmt:formatDate value="${relation.relateTime}" type="date"
                                                        pattern="yyyy年MM月dd日 HH:ss:mm"/>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
            <div style="text-align: center">
                <button class="layui-btn add-button" id="bind_child" onclick="onAddBtn()">
                    <i class="layui-icon">&#xe608;</i>绑定孩子
                </button>
            </div>
        </div>
        <!-- END PAGE CONTAINER -->
    </div>

    <jsp:include page="../common/parent_footer.jsp"></jsp:include>
</div>
<div id="add-child" style="display: none;">
    <form class="layui-form" id="add-form" action="" style="margin-top: 10px">
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">孩子账号电话号码</label>
            <div class="layui-input-block">
                <input type="text" name="userTel" required style="width: 240px" lay-verify="phone"
                       placeholder="请输入孩子账号的电话号码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">孩子账号密码</label>
            <div class="layui-input-block">
                <input type="password" name="userPwd" id="childPwd" required style="width: 240px" lay-verify="required"
                       placeholder="请输入孩子账号的密码" autocomplete="off" class="layui-input">
                <!-- <input type="hidden" name="id" style="width: 240px" autocomplete="off" class="layui-input"> -->
            </div>
        </div>
        <div class="layui-form-item" style="margin-top: 30px">
            <label class="layui-form-label">关系</label>
            <div class="layui-input-block">
                <select name="relationship" lay-verify="required">
                    <option value="父亲">父亲</option>
                    <option value="母亲">母亲</option>
                    <option value="祖父">祖父</option>
                    <option value="祖母">祖母</option>
                    <option value="祖父">外祖父</option>
                    <option value="祖母">外祖母</option>
                    <option value="姑姑">姑姑</option>
                    <option value="姑父">姑父</option>
                    <option value="叔叔">叔叔</option>
                    <option value="婶婶">婶婶</option>
                    <option value="伯父">伯父</option>
                    <option value="伯母">伯母</option>
                    <option value="舅舅">舅舅</option>
                    <option value="舅妈">舅妈</option>
                    <option value="姨夫">姨夫</option>
                    <option value="姨妈">姨妈</option>
                    <option value="哥哥">哥哥</option>
                    <option value="姐姐">姐姐</option>
                    <option value="其他">其他</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item" style="margin-top: 50px">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="save">提交</button>
                <button class="layui-btn layui-btn-primary" id="closeBtn">关闭</button>
            </div>
        </div>
    </form>
</div>
<script src="${path}/static/plugins/layui/layui.js"></script>
<script src="${path}/static/plugins/jquery/jquery.min.js"></script>
<script>
    function showData(userId) {
        /*var temp_form = document.createElement("form");
        temp_form.method = "post";
        temp_form.style.display = "none";
        temp_form.action = "/parent/showData";
        var opt = document.createElement("textarea");
        opt.name = "userId";
        opt.value = userId;
        temp_form.appendChild(opt);
        temp_form.submit();*/
        var date = new Date();
        var formatDate = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
        window.location.href="${path}/parent/showData?userId="+userId+"&formatDate="+formatDate;
        window.event.returnValue = false
    }

    function onDelBtn(relationId) {
        var r = confirm("确认删除？");
        if (r == true) {
            $.post('${path}/parent/unbindChild',
                {
                    relationId: relationId
                },
                function (res) {
                    if (res.status == 1) {
                        layer.msg(res.message, {icon: 1});
                        window.location.href = "${path}/parent/index";
                        window.event.returnValue = false
                    } else if (res.status == 2) {
                        layer.msg(res.message, {icon: 0});
                        window.location.href = "${path}/parent/index";
                        window.event.returnValue = false
                    } else {
                        layer.msg(res.message, {icon: 0}, function () {
                            location.reload(); // 页面刷新
                            return false
                        })
                    }
                }, 'json');
        }
    }

    //JavaScript代码区域
    function onAddBtn() {
        //页面层-自定义
        layer.open({
            type: 1,
            title: "绑定孩子",
            closeBtn: false,
            shift: 2,
            area: ['400px', '500px'],
            shadeClose: true,
            content: $("#add-child"),
            success: function (layero, index) {
            },
            yes: function () {
            }
        });
    }

    //JavaScript代码区域
    layui.use(['element', 'layer', 'form'], function () {
        var element = layui.element;
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;


        //提交监听事件
        form.on('submit(save)', function (data) {
            params = data.field;
            /*alert(JSON.stringify(params))*/
            submit($, params);
            return false;
        });
        var obj = document.getElementById('closeBtn');
        obj.addEventListener('click', function cancel() {
            CloseWin();
        });

        //提交
        function submit($, params) {
            $.post('${path}/parent/bindChild', params, function (res) {
                if (res.status == 1) {
                    layer.msg(res.message, {icon: 1}, function (index) {
                        CloseWin();
                    })
                } else if (res.status == 2) {
                    layer.msg(res.message, {icon: 0}, function () {
                        parent.location.href = '${path}/parent/index';
                        CloseWin();
                    })
                } else {
                    layer.msg(res.message, {icon: 0}, function () {
                        location.reload(); // 页面刷新
                        return false
                    })
                }
            }, 'json');
        }

        debugger;

        //关闭页面
        function CloseWin() {
            window.location.href = "${path}/parent/index";
            window.event.returnValue = false
        }

    });
</script>
</body>
</html>
