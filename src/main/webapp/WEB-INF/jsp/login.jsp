<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <script src="/js/jquery/jquery-3.3.1.js"></script>
</head>
<body style="text-align: center;align-content: center">
<div style="width: 530px;height: 570px;text-align: center;background-color: #E5E5E5">
    <div align="center" style=" text-align: center;margin-left: 35px;margin-right: 35px">
        <img src="/photo/2.png" >
    </div>
    <div>
        <img src="/photo/3.png">
    </div>
    <div style="background-color: white; height: 380px;text-align: center;margin-left: 74px;margin-right: 74px">
        <div align="center" style="margin-top:1px;">
            <span >登录</span>
            <a href="http://localhost:8080/register"><span>注册</span></a>
        </div>
        <div align="center">
            <input type="text" id="userName"  placeholder="请输入登录邮箱">
        </div>
        <div align="center">
            <input type="password" id="password"  placeholder="请输入密码">
        </div>
        <div align="center">
            <input type="checkbox" id="autoLogin">
            自动登录
            <a href="http://localhost:8080/changePsw">忘记密码</a>
        </div>
        <div align="center">
            <input type="button"  style= "height:50px;width:120px;background-color: aqua" value="登录" id="btnLogin">
        </div>

        <%--<div align="center">-------------使用第三方账号注册-------------</div>--%>
            <%--<div align="center"><img src="/photo/QQ.png">--%>
                <%--<img src="/photo/WX.png">--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>
    <script>
        $(function () {
          $("#btnLogin").on("click",function () {
              if ($("#btnLogin").hasClass("Saving")){
                  return;
              }
              $("#btnLogin").addClass("Saving");
              $("#btnLogin").val("Saving");
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
              $.ajax({
                  type:"POST",
                  url:"/user/doLogin",
                  dataType:"json",
                  data:{
                      "email":userId,
                      "password":password
                  },
                  success:function (result) {
                      $("#btnLogin").removeClass("Saving");
                      $("#btnLogin").val("Save");
                      if(result.code==0){
                          window.location.href="/homepage"
                      }
                      else{
                          alert(result.msg);
                      }
                  },
                  error:function () {
                      $("#btnLogin").removeClass("Saving");
                      $("#btnLogin").val("Save");
                  }
              })
          })
        })
    </script>
</div>
</body>
</html>