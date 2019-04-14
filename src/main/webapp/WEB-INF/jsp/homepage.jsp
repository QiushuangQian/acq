<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人主页</title>
    <script src="/js/jquery/jquery-3.3.1.js"></script>

    <style type="text/css">
        .widthSet{
            width: 969px;
        }
    </style>
</head>
<body style="text-align: center">
<div>
    <div class="widthSet">
        <img src="/photo/4.png" >
    </div>
    <div class="widthSet" style="text-align: center">
        <input id="album" type="button" value="相册">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input id="photo" type="button" value="照片">
       <%--<div style="margin-right: -760px"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"   placeholder="搜索照片"></div>--%>
    </div>
    <div class="widthSet">
         <%--<div style="margin-left: -830px">--%>
         <input id="upload" type="button" value="上传照片" style="background-color: aqua">
         <input id="createAlbum" type="button" value="创建相册" style="background-color: aqua;margin-left: 10px">
         <input id="setAlbum" type="button" value="管理相册" style="background-color: aqua;margin-left: 10px">
         <input type="button" id="recycleBin" value="回收站" style="background-color: white;float: right">
    </div><br>

</div>
<script  type="text/javascript">
    $(function () {
        $("#photo").on("click",function () {
            window.location.href="/homepage/photo"
        })

        $("#album").on("click",function () {
            window.location.href="/album/albumPhotoShow"
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
            window.location.href="/album/createAlbum"
        });

    $("#setAlbum").on("click",function () {
        window.location.href="/album/setAlbum"
    })
    });

</script>
</body>
</html>
