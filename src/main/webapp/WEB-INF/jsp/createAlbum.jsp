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
    <script src="/js/jquery/jquery-3.3.1.js"></script>
    <link type="text/css" rel="stylesheet" href="/css/createAlbum.css">
</head>
<body>
<div class = "body">
    <div class="e1">
        <div class="header">
            <span>猫爪相册</span>
        </div>
        <div class="e2">
            <input id="album" type="button" value="相册">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input id="photo" type="button" value="照片">
        </div>
        <div class="e3">
            <input id="upload" type="button" value="上传照片">
            <input id="back" type="button" value="返回">
        </div><br>
        <div class="name">
            输入相册名：<input type="text" id="albumName" placeholder="相册名">
        </div>
        <div class="submit">
            <input type="button" id="submit" value="Create">
        </div>
    </div>
    <script  type="text/javascript">

        $("#back").on("click",function(){
            window.location.href="/homepage"
        });
            //上传照片——峰
        $("#upload").on("click",function () {
            window.location.href="/upload"
        })
    </script>
    <script>
        $(function(){
            $("#photo").on("click",function () {
                window.location.href="/homepage/photo"

            })

            $("#album").on("click",function () {
                window.location.href="/homepage/albumPhotoShow"
            })
            $("#submit").on("click",function () {
                //等待创建返回
                if($("#submit").hasClass("Creating")){
                    return;
                }
                $("#submit").addClass("Creating");
                $("#submit").val("Create");

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
                    window.location.href="/album/albumPhotoShow"
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
