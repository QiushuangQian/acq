<%--
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/3/23
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script src="/js/jquery/jquery-3.3.1.js"></script>
</head>
<body>
<div align="center">
    <div><img src="/images/loginLogo.png"></div>
    <div>昵称：<input type="text" id="nickName"></div>
    <div>密码：<input type="password" id="password"></div>
    <div>邮箱：<input type="email" id="email"></div>
    <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;验证码：<input type="text" id="idCode"><input type="button" id="sendMail" value="发送验证邮件"></div>
    <%--<div><input type="checkbox" value="已阅读并接受" id="agree"><a href="#" id="agreement">猫爪相册注册协议</a> </div>--%>
    <div>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="register" value="注册"></div>
</div>
<%--<div>----------------使用第三方注册-----------------</div>--%>
<%--<div><img src="/images/qqLogo.png" id="qq"><img src="/images/wechatLogo.png" id="wechat"></div>--%>
<script>
    $(function () {
        //发送验证邮件
        $("#sendMail").on("click", function () {
            //等待验证邮件发送
            if ($("#sendMail").hasClass("Sending")) {
                return;
            }
            $("#sendMail").addClass("Sending");
            $("#sendMail").val("邮件发送中...");

            //昵称
            var nickname = $("#nickName").val();
            //密码
            var password = $("#password").val();
            if (password == null) {
                alert("密码不能为空！");
            }

            //邮箱
            var email = $("#email").val();
            if (email == null) {
                alert("邮箱不能为空！");
            }

            $.ajax({
                type: "post",
                url: "/register/sendMail",
                dataType: "json",
                data: {
                    "nickName": nickname,
                    "email": email,
                    "password": password
                },
                success: function (result) {
                    $("#sendMail").removeClass("Sending");
                    $("#sendMail").val("邮件已发送");
                    if (!result) {
                        alert("邮箱已注册！");
                    }
                },
                error: function () {
                    $("#sendMail").removeClass("Sending");
                    $("#sendMail").val("发送验证邮件");
                }
            })

        })
        //验证码激活注册
        $("#register").on("click", function () {

            //等待注册返回
            if ($("#register").hasClass("Registering")) {
                return;
            }
            $("#register").addClass("Registering");
            $("#register").val("注册中...");

            //验证码
            var idCode = $("#idCode").val();
            //alert(idCode)

            $.ajax({
                type: "post",
                url: "/activation",
                dataType: "json",
                data: {
                    "idCode": idCode
                },
                success: function (result) {
                    $("#register").removeClass("Registering");
                    $("#register").val("注册");
                    if (result.idCode == 1) {
                        window.location.href = "/user/login"
                    }
                    else {
                        alert(result.msg);
                    }
                },
                error: function () {
                    $("#register").removeClass("Registering");
                    $("#register").val("注册");
                }
            })
        })
    })
</script>
</body>
</html>
