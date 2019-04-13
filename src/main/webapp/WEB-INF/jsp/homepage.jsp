<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人主页</title>
    <script src="/js/jquery/jquery-3.3.1.js"></script>
</head>
<body>
<div style="text-align: center">
<div>
    <img src="/photo/4.png" >
</div>
    <div style="text-align: center">
        <input id="ablum" type="button" value="相册">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input id="photo" type="button" value="照片">
       <div style="margin-right: -760px"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"   placeholder="搜索照片"></div>
    </div>
    <div>
         <div style="margin-left: -830px">
             <input id="upload" type="button" value="上传照片" style="background-color: aqua">
             <input id="createAlbum" type="button" value="创建相册" style="background-color: aqua">
             <input id="setAlbum" type="button" value="管理相册" style="background-color: aqua">
             <div style="margin-left:1750px"><input type="button" id="recycleBin" value="回收站" style="background-color: white"></div>
         </div>
    </div>

</div>
<script  type="text/javascript">
    $(function () {
        $("#photo").on("click",function () {
            window.location.href="/homepage/photo"
        })

        $("#ablum").on("click",function () {
            window.location.href="/homepage/ablum"
        })

        $("#recycleBin").on("click",function () {
            window.location.href="/recycleBin"
        });


        //上传照片——峰
        $("#upload").on("click",function () {
            window.location.href="/homepage/upload"
        });

        //创建相册——峰
        $("#createAlbum").on("click",function () {
            window.location.href="/homepage/createAlbum"
        });

    $("#setAlbum").on("click",function () {
        window.location.href="/album/setAlbum"
    })
    });

</script>
</body>
</html>
