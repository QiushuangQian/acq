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
</head>
<body>
<div><img src="/images/loginLogo.png"></div>
<div>邮箱：<input type="email" id="email"></div>
<div>新密码：<input type="password" id="password"></div>
<div>验证码：<input type="text" id="idCode"><input type="button" id="sendMail" value="发送验证邮件"></div>
<div><input type="button" id="submit" value="确定"></div>
<script>
    $(function () {
        //发送验证邮件
        $("#sendMail").on("click",function () {
            //等待验证邮件发送
            if($("#sendMail").hasClass("Sending")){
                return;
            }
            $("#sendMail").addClass("Sending");
            $("#sendMail").val("邮件发送中...");

            //密码
            var password=$("#password").val();
            if(password==null){
                alert("密码不能为空！");
            }

            //邮箱
            var email=$("#email").val();
            if(email==null){
                alert("邮箱不能为空！");
            }

            $.ajax({
                type:"post",
                url:"/changePsw/sendMailChangePsw",
                dataType:"json",
                data:{
                    "email":email,
                    "password":password
                },
                success:function (result) {
                    $("#sendMail").removeClass("Sending");
                    $("#sendMail").val("邮件已发送");
                    if(!result){
                        alert("邮箱未注册！");
                    }
                },
                error:function () {
                    $("#sendMail").removeClass("Sending");
                    $("#sendMail").val("发送验证邮件");
                }
            })

        })
        //确认验证码
        $("#submit").on("click",function () {

            //等待注册返回
            if($("#submit").hasClass("Submiting")){
                return;
            }
            $("#submit").addClass("Submiting");
            $("#submit").val("确认中...");

            //验证码
            var idCode=$("#idCode").val();

            $.ajax({
                type:"post",
                url:"/activation",
                dataType:"json",
                data:{
                    "idCode":idCode
                },
                success:function (result) {
                    $("#submit").removeClass("Submiting");
                    $("#submit").val("确定");
                    if(result.idCode==1){
                        window.location.href="/login"
                    }
                    else {
                        alert(result.msg);
                    }
                },
                error:function () {
                    $("#submit").removeClass("Submiting");
                    $("#submit").val("确定");
                }
            })
        })
    })
</script>
</body>
</html>
