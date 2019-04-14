<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/4/14
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${path}/static/plugins/jquery/jquery.min.js"></script>
</head>
<body>
    <button id="b1">得到Admin</button>
    <button id="b2">以Ajax形式发送一个Admin对象到后台</button>
    <div>

    </div>

    <script>
        $(function () {
            $('#b1').click(function () {
                $.ajax({
                    url:'${path}/json/m1',
                    type:'post',
                    success:function (data) {
                        alert(data.adminName)
                    }
                })
            })

            $('#b2').click(function () {
                var obj = {
                    'adminName':'大老虎',
                    'adminPwd':'456'
                };

                $.ajax({
                    url:'${path}/json/m2',
                    type:'post',
                    data:JSON.stringify(obj),
                    contentType:"application/json",
                    success:function (data) {
                        alert("success")
                    }
                })
            })
        })
    </script>
</body>
</html>
