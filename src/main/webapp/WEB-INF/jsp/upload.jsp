<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.awt.*" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/3/24
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传</title>
    <link rel="stylesheet" type="text/css" href="/css/webuploader.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
    <script type="text/javascript" src="/js/jquery/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="/js/webuploader.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <link type="text/css" rel="stylesheet" href="/css/upload.css">
</head>
<body>

    <div class="a1">
        <div class="header">
            <span>猫爪相册</span>
        </div>
        <div class="a2">
            <input id="album" type="button" value="相册">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input id="photo" type="button" value="照片">
        </div>
        <div class="a3">
            <input id="upload" type="button" value="上传照片" style="background-color: aqua">
            <input id="createAlbum" type="button" value="创建相册" style="background-color: aqua">
            <input id="back" type="button" value="返回" style="background-color: aqua">
            <div style="margin-left:1750px"><input type="button" id="recycleBin" value="回收站"
                                                   style="background-color: white"></div>
        </div><br>
        <div>
            <label>上传到</label>&nbsp;
            <select id="albumSelect" onchange="changeAlbum(this.value)">
                <option value="0" selected="selected">请选择相册</option>
                <c:forEach var="album1" items="${albumList}">
                    <option value="${album1.albumId}">${album1.albumName}</option>
                </c:forEach>
            </select>
        </div>
        <br>
        <div id="uploader-demo">
            <div id="fileList" class="uploader-list"></div>
        </div>
        <br>
        <div>
            <div id="filePicker">选择图片</div>
            <button id="ctlBtn" class="btn btn-default">开始上传</button>
        </div>
    </div>

<script type="text/javascript">
    $(function () {
        $("#photo").on("click",function () {
            window.location.href="/homepage/photo"

        })

        $("#album").on("click",function () {
            window.location.href="/homepage/albumPhotoShow"
        })

        $("#recycleBin").on("click", function () {
            window.location.href = "/recycleBin"
        });

        $("#back").on("click", function () {
            window.location.href = "/homepage"
        });

        //上传照片——峰
        $("#upload").on("click", function () {
            window.location.href = "/homepage/upload"
        });

        //创建相册——峰
        $("#createAlbum").on("click", function () {
            window.location.href = "/homepage/createAlbum"
        })
    });


</script>

<script type="text/javascript">
    var selectedId;
    function changeAlbum() {
        selectedId = $('#albumSelect option:selected').val();
    }

    $(function () {

        var $ = jQuery,
            $list = $('#fileList'),
            // 优化retina, 在retina下这个值是2
            ratio = window.devicePixelRatio || 1,

            // 缩略图大小
            thumbnailWidth = 100 * ratio,
            thumbnailHeight = 100 * ratio,

            // Web Uploader实例
            uploader;
        var $btn = $("#ctlBtn");	 //开始上传

        // 初始化Web Uploader
        uploader = WebUploader.create({

            // 自动上传。
            auto: false,

            // swf文件路径
            swf: '/js/Uploader.swf',

            // 文件接收服务端。-------------------------------------------
            server: '/doUpload/',
            threads: '5',  //同时运行5个线程传输
            fileSizeLimit: 20 * 1024 * 1024,    //最大20M

            // 选择文件的按钮。可选。
            pick: {
                id: '#filePicker', //选择文件的按钮
                multiple: true
            }, //允许可以同时选择多个图片

            // 只允许选择文件，可选。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });

        // 当有文件添加进来的时候
        uploader.on('fileQueued', function (file) {
            var $li = $(
                    '<div id="' + file.id + '" class="file-item thumbnail" style="float: left;">' +
                    '<img>' +
//                        '<div class="info">' + file.name + '</div>' +
                    '</div>'
                ),
                $img = $li.find('img');

            // $list为容器jQuery实例
            $list.append($li);

            // 创建缩略图
            uploader.makeThumb(file, function (error, src) {
                if (error) {
                    $img.replaceWith('<span>不能预览</span>');
                    return;
                }

                $img.attr('src', src);
            }, thumbnailWidth, thumbnailHeight);

            uploader.refresh(); // 重新实例化

        });

        //传递选择的相册Id
        uploader.on('uploadBeforeSend', function (obj, data, headers) {
            data.selectedAlbumId = selectedId;
        });

        // 文件上传过程中创建进度条实时显示。    uploadProgress事件：上传过程中触发，携带上传进度
        uploader.on('uploadProgress', function (file, percentage) {
            var $li = $('#' + file.id),
                $percent = $li.find('.progress .progress-bar');

            // 避免重复创建
            if (!$percent.length) {
                $percent = $('<p class="progress"><span></span></p>')
                    .appendTo($li)
                    .find('span');
            }

            $percent.css('width', percentage * 100 + '%');
        });

        // 文件上传成功，给item添加成功class, 用样式标记上传成功。
        uploader.on('uploadSuccess', function (file) {
            $('#' + file.id).addClass('upload-state-done');

        });


        // 文件上传失败，现实上传出错。    file:文件对象 ， code：出错代码
        uploader.on('uploadError', function (file, code) {
            var $li = $('#' + file.id),
                $error = $li.find('div.error');

            // 避免重复创建
            if (!$error.length) {
                $error = $('<div class="error"></div>').appendTo($li);
            }

            $error.text('上传失败');
        });

        // 完成上传完了，成功或者失败，先删除进度条。
        uploader.on('uploadComplete', function (file) {
            $('#' + file.id).find('.progress').remove();

            uploader.refresh(); // 重新实例化
        });

        $btn.on('click', function () {
            console.log("上传...");
            uploader.upload();
            console.log("上传成功");

        });


    });
</script>
</body>
</html>
