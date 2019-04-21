<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <script src="/js/jquery/jquery-3.3.1.js"></script>
    <link type="text/css" rel="stylesheet" href="/css/login.css">
</head>
<body class="b" >
<div align="center">
<div class="c" >
    <div class="d">
        <img src="/photo/2.png" >
    </div>
    <div>
        <img src="/photo/3.png">
    </div>
    <div class="f">
        <div class="e">
            <span >登录</span>
            <a href="http://localhost:8080/register"><span>注册</span></a>
        </div>
        <div align="center">
            邮箱：<input type="text" id="userName" placeholder="请输入登录邮箱" style="width: 200px;height: 30px">
        </div>
        <br>
        <div align="center">
            密码：<input type="password" id="password"  placeholder="请输入密码"style="width: 200px;height: 30px">
        </div>
        <br>
        <div align="center">
            <a href="http://localhost:8080/changePsw">忘记密码</a>
        </div>
        <div align="center">
            <input type="button"  style= "height:50px;width:120px;background-color: aqua" value="登录" id="btnLogin">
        </div>

            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $("#btnLogin").on("click",function () {
            if ($("#btnLogin").hasClass("正在登录...")){
                return;
            }

            var userId=$("#userName").val();
            if(userId.trim()==""){
                alert("User ID is empty!");
                return;
            }
            var password=$("#password").val();
            if(password.trim()==""){
                alert("Password is empty!");
                return;
            }
            $("#btnLogin").addClass("正在登录...");
            $("#btnLogin").val("正在登录...");
            $.ajax({
                type:"POST",
                url:"/user/doLogin",
                dataType:"json",
                data:{
                  "email":userId,
                  "password":password
                },
                success:function (result) {
                  $("#btnLogin").removeClass("正在登录...");

                  if(result.code==0){
                      $("#btnLogin").val("登录成功！");
                      window.location.href="/homepage"
                  }
                  else{
                      changeButton();
                      alert(result.msg);
                  }
                },
                error:function () {
                  changeButton();
                }
            })
        })

        function changeButton() {
            $("#btnLogin").removeClass("正在登录...");
            $("#btnLogin").val("登录");
        }
    })
</script>
</div>
</body>
</html>