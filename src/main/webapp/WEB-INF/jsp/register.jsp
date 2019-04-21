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
    <style type="text/css">
        div{
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div align="center">
    <div><img src="/images/loginLogo.png"></div>
    <div>邮箱：<input type="text" id="email"></div>
    <div>密码：<input type="password" id="password"></div>
    <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;验证码：<input type="text" id="idCode"><input type="button" id="sendMail" value="发送验证邮件"></div>
    <%--<div><input type="checkbox" value="已阅读并接受" id="agree"><a href="#" id="agreement">猫爪相册注册协议</a> </div>--%>
    <div>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="register" value="注册"></div>
</div>
<script>
    $(function () {
        //验证邮箱和密码是否正确
        function checkInput() {
            //验证邮箱格式的js正则表达式
//            var mail = $("#email").val();
            var mailTmp =  $.trim($('#email').val());
            var isEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            if(mailTmp.length<=0){
                alert("邮箱不能为空！");
                return false;
            }else if(isEmail.test(mailTmp)==false){
                alert("邮箱格式不正确！");
                return false;
            }
            //密码
            var psw = $("#password").val();
            if (psw.length<=0) {
                alert("密码不能为空！");
                return false;
            }
            return true;
        }

        //发送验证邮件
        $("#sendMail").on("click", function () {
            //等待验证邮件发送
            if ($("#sendMail").hasClass("Sending")) {
                return;
            }

            if(!checkInput()){
                return;
            }
            var email = $("#email").val();
            var password = $("#password").val();

            //使无法再次触发发送右键操作
            $("#sendMail").addClass("Sending");
            $("#sendMail").val("邮件发送中...");

            $.ajax({
                type: "post",
                url: "/register/sendMail",
                dataType: "json",
                data: {
                    "email": email,
                    "password": password
                },
                success: function (result) {

                    if (!result.sendResult) {
                        alert("邮箱已注册！");
                        $("#sendMail").removeClass("Sending");//失败，可以再次发送邮件
                        $("#sendMail").val("发送验证邮件");
                    }else { //成功，无法再次触发发送邮件
                        $("#sendMail").val("邮件已发送");
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
            if(!checkInput()){
                return;
            }
            //验证码
            var code=$("#idCode").val();
            if(code.length<=0){
                alert("验证码为空！");
                return;
            }
            //使无法再次触发注册操作
            $("#register").addClass("Registering");
            $("#register").val("注册中...");

            $.ajax({
                type: "post",
                url: "/activation",
                dataType: "json",
                data: {
                    "idCode": code
                },
                success: function (result) {

                    if (result.idCode == 1) {
                        $("#register").val("注册成功！");
                        window.location.href = "/user/login"
                    }
                    else {
                        $("#register").removeClass("Registering");
                        $("#register").val("注册");
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
