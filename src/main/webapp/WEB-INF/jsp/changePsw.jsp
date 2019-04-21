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
    <title>修改密码</title>
    <script src="/js/jquery/jquery-3.3.1.js"></script>
    <link type="text/css" rel="stylesheet" href="/css/changePsw.css">
</head>
<body>
<div style="text-align: center">
    <div class="a">
        <div><img src="/images/loginLogo.png"></div>
        <div>邮箱：<input type="email" id="email"></div>
        <div>新密码：<input type="password" id="password">&nbsp;&nbsp;&nbsp;</div>
        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;验证码：<input
                type="text" id="idCode"><input type="button" id="sendMail" value="发送验证邮件"></div>
        <div><input type="button" id="submit" value="确定"></div>
    </div>
</div>
<script>
    $(function () {
        //发送验证邮件
        $("#sendMail").on("click", function () {
            //等待验证邮件发送
            if ($("#sendMail").hasClass("Sending")) {
                return;
            }
            //邮箱
            var email = $("#email").val();
            var isEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            if (email == null||email=="") {
                alert("邮箱不能为空！");
                return;
            }else if(isEmail.test(email)==false){
                alert("邮箱格式不正确！");
                return false;
            }
            //密码
            var password = $("#password").val();
            if (password == ""||password==null) {
                alert("密码不能为空！");
                return;
            }

            $("#sendMail").addClass("Sending");
            $("#sendMail").val("邮件发送中...");

            $.ajax({
                type: "post",
                url: "/changePsw/sendMailChangePsw",
                dataType: "json",
                data: {
                    "email": email,
                    "password": password
                },
                success: function (result) {
                    if (!result.sendResult) {
                        alert("邮箱未注册！");
                        $("#sendMail").removeClass("Sending");
                        $("#sendMail").val("发送验证邮件");
                    }else {
                        $("#sendMail").val("邮件已发送");
                    }
                },
                error: function () {
                    $("#sendMail").removeClass("Sending");
                    $("#sendMail").val("发送验证邮件");
                }
            })

        })
        //确认验证码
        $("#submit").on("click", function () {

            //等待注册返回
            if ($("#submit").hasClass("Submitting")) {
                return;
            }
            $("#submit").addClass("Submitting");
            $("#submit").val("确认中...");
            //邮箱
            var email = $("#email").val();
            var isEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            if (email == null||email=="") {
                alert("邮箱不能为空！");
                return;
            }else if(isEmail.test(email)==false){
                alert("邮箱格式不正确！");
                return false;
            }
            //密码
            var password = $("#password").val();
            if (password == ""||password==null) {
                alert("密码不能为空！");
                return;
            }
            //验证码
            var idCode = $("#idCode").val();
            if(idCode==""||idCode==null){
                alert("验证码不能为空！");
                return;
            }

            $.ajax({
                type: "post",
                url: "/activation",
                dataType: "json",
                data: {
                    "idCode": idCode
                },
                success: function (result) {
                    if (result.idCode == 1) {
                        window.location.href = "/user/login"
                    }else {
                        alert(result.msg);
                        $("#submit").removeClass("Submitting");
                        $("#submit").val("确定");
                    }
                },
                error: function () {
                    $("#submit").removeClass("Submitting");
                    $("#submit").val("确定");
                }
            })
        })
    })
</script>
</body>
</html>
