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
</head>
<body>
<div class = "body">
    <div style="text-align: center">
        <div>
            <img src="/photo/4.png" >
        </div>
        <div style="text-align: center">
            <span >相册</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>照片</span>
        </div>
        <div>
            <div style="margin-left: -830px">
                <input id="upload" type="button" value="上传照片" style="background-color: aqua">
                <input id="back" type="button" value="返回" style="background-color: aqua">
            </div>
        </div>


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
