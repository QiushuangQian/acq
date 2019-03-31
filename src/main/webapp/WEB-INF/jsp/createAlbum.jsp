<%--
  Created by IntelliJ IDEA.
  User: qianqiushuang
  Date: 2019-03-24
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateAlbum</title>
    <script src="/js/jquery-3.3.1.js"></script>
</head>
<body>
<div class = "body">
    <div><img src="/images/loginLogo.png"></div>
    <div class="name">
        输入相册名：<input type="text" id="albumName" placeholder="相册名">
    </div>
    <div class="submit">
        <input type="button" id="submit" value="Create">
    </div>
    <script>
        $(function(){
            $("#submit").on("click",function () {
                //等待创建返回
                if($("#submit").hasClass("Creating")){
                    return;
                }
                $("#submit").addClass("Creating");
                $("#submit").val("相册创建中...");

                var albumName = $("#albumName").val();
                if(albumName==''){
                    alert("相册名不能为空！");
                    return;
                }
                //传递参数
                $.ajax({
                    type:"post",
                    url:"/album/doCreateAlbum",
                    dataType:"json",
                    data:{
                        "albumName":albumName
                    },
                success:function(){
                    $("#submit").removeClass("Creating");
                    $("#submit").val("Created");
                },
                error:function() {
                    $("#submit").removeClass("Creating");
                    $("#submit").val("Create");
                }
                })
            })
        })
    </script>
</div>
</body>
</html>
