<%--
  Created by IntelliJ IDEA.
  User: qianqiushuang
  Date: 2019-04-12
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>albumPhotoShow</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/jquery.magnify.min.css" rel="stylesheet"><%--图片展示插件css--%>
    <link href="/css/font-awesome.min.css" rel="stylesheet"><%--Magnify 使用 font-awesome 的图标--%>

    <script src="/js/jquery/jquery-3.3.1.js"></script>
    <script src="/js/jquery.magnify.min.js"></script><%--图片展示插件js--%>
    <link type="text/css" rel="stylesheet" href="/css/albumPhotoShow.css">
</head>
<body>
<div class="first" >
    <div>
        <img src="/photo/4.png" >
    </div>
    <div class="second">
        <input id="album" type="button" value="相册">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input id="photo" type="button" value="照片">
    </div>
    <div>
        <div class="third" >
            <input id="upload" type="button" value="上传照片" style="background-color: aqua">
            <input id="createAlbum" type="button" value="创建相册" style="background-color: aqua">
            <input id="back" type="button" value="返回" style="background-color: aqua">
            <div style="margin-left:1750px"><input type="button" id="recycleBin" value="回收站" style="background-color: white"></div>
        </div>
    </div>
    <div class="selectAlbum">
        <select id="albumSelect" onchange="changeAlbum(this.value)">
            <option value="0" selected="selected">请选择相册</option>
            <c:forEach var="album1" items="${albumList}">
                <option value="${album1.albumId}">${album1.albumName}</option>
            </c:forEach>
        </select>
        <input type="button" id="checkin" value="进入">
    </div>
</div>
<!--
<div id="photoArea">
    <c:forEach var="photo" items="${initial}">
        <img data-magnify="gallery" data-src="${photo.photoPath}" src="${photo.thumbnailPath}" style="margin: 2px">
    </c:forEach>
</div>
-->
<script  type="text/javascript">
    $(function () {
        $("#photo").on("click",function () {
            window.location.href="/homepage/photo"

        })

        $("#album").on("click",function () {
            window.location.href="/homepage/albumPhotoShow"
        })
        $("#recycleBin").on("click",function () {
            window.location.href="/recycleBin"
        });

        $("#photo").on("click",function () {
            window.location.href="/homepage/photo"

        });

        $("#album").on("click",function () {
            window.location.href="/homepage/albumPhotoShow"
        });

        //上传照片——峰
        $("#upload").on("click",function () {
            window.location.href="/homepage/upload"
        });

        //创建相册——峰
        $("#createAlbum").on("click",function () {
            window.location.href="/homepage/createAlbum"
        });

        $("#back").on("click",function(){
            window.location.href="/homepage"
        });

        $("#setAlbum").on("click",function () {
            window.location.href="/album/setAlbum"
        })
    });

    var selected;
    function changeAlbum() {
        selected = $('#albumSelect option:selected').val();
    }

    $("#checkin").on("click",function (){
        window.location.href="/album/doAlbumPhotoShow?selected="+selected;
    })

    //
    // jQuery(document).ready(function () {
    //     $("#checkin").on("click",function (){
    //         if($("#checkin").hasClass("进入中...")){
    //             return;
    //         }
    //         $("#checkin").addClass("进入中...");
    //         $("#checkin").val("进入中...");
    //
    //         //传递参数到后端
    //         var params = {"selected":selected};
    //         $.ajax({
    //             type:"post",
    //             url:"/album/doAlbumPhotoShow",
    //             dataType:"json",
    //             data:params,
    //             success:function(){
    //                 $("#checkin").removeClass("进入中...");
    //                 $("#checkin").val("进入");
    //             },
    //             error:function() {
    //                 $("#checkin").removeClass("进入中...");
    //                 $("#checkin").val("进入失败");
    //             }
    //         })
    //     })
    // })

</script>
<script>
    $('[data-magnify]').magnify({
        headToolbar: [
            'close'
        ],
        initMaximized: true,    //初始最大化
        multiInstances:false    //禁用多实例
    })
</script>
</body>
</html>
