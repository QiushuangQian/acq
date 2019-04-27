<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人主页</title>
    <script src="/js/jquery/jquery-3.3.1.js"></script>
    <link type="text/css" rel="stylesheet" href="/css/homepage.css">
    <style type="text/css">
        .widthSet{
            width: 969px;
        }
    </style>
</head>
<body class="d1">
<div>
    <div class="header">
        <span>猫爪相册</span>
    </div>
    <div class="btn1">
        <input id="album" type="button" value="相册">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input id="photo" type="button" value="照片">
       <%--<div style="margin-right: -760px"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"   placeholder="搜索照片"></div>--%>
    </div>
    <div class="btn2">
         <%--<div style="margin-left: -830px">--%>
         <input id="upload" type="button" value="上传照片">
         <input id="createAlbum" type="button" value="创建相册">
         <input id="setAlbum" type="button" value="管理相册">
         <input type="button" id="recycleBin" value="回收站">
    </div>
</div>
<script  type="text/javascript">
    $(function () {
        $("#photo").on("click",function () {
            window.location.href="/homepage/photo"

        });

        $("#album").on("click",function () {
            window.location.href="/homepage/albumPhotoShow"
        });

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
            window.location.href="/homepage/setAlbum"
        })
    });

</script>
</body>
</html>
