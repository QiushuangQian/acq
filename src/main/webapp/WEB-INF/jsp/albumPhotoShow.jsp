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

    <script type="text/javascript">
        var pagenum=2;//页号
        var loadFlag = true; //加载标志

        var container;//照片区域容器
        window.onload=function () {
            container = document.getElementById("photoArea");//得到图片区域容器对象div，用于img标签的附加
        }



        $(window).scroll(function() {
            //当滚轮滚动到文档最末位，也就是拉到了最底下
            //$(window).scrollTop():为滚动条在Y轴上的滚动距离。
            //$(window).height():为内容可视区域的高度。
            //$(document).height():当前页面的总高度  为内容可视区域的高度加上溢出（滚动）的距离。
            if( loadFlag && ($(window).scrollTop()+ $(window).height() >= $(document).height()-5)) {
                if(loadFlag){
                    loadFlag=false;
                    //发送ajax请求获取数据
                    $.ajax({
                        type: "POST",
                        url: "/albumPhotoList",
                        data:{
                            "pagenum":pagenum
                        },
                        success: function(data){
                            if(data.result){
                                for(var i=0;i<data.getPhotoList.length;i++ ){
                                    if(data.photoList[i]!=null && data.getPhotoList[i].photoPath!=null && data.getPhotoList[i].thumbnailPath!=null){
                                        //创建img标签对象
                                        var img = document.createElement("img");
                                        img.src=data.getPhotoList[i].thumbnailPath;
                                        img.style="margin: 2px";
                                        img.setAttribute("data-magnify","gallery");
                                        img.setAttribute("data-src",data.getPhotoList[i].photoPath);

                                        //向图片容器内添加图片
                                        container.appendChild(img);
                                    }

                                }

                                //加载完成，开启加载标志
                                setTimeout(openLoadFlag,1000);
                                pagenum++;

                                $('[data-magnify]').magnify({
                                    headToolbar: [
                                        'close'
                                    ],
                                    initMaximized: true,    //初始最大化
                                    multiInstances:false    //禁用多实例
                                });

                            }else{
                            }
                        }
                    });
                }

            }
        });
        function openLoadFlag() {
            loadFlag=true;//启动加载标志
        }

    </script>
</head>
<body>
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
<div id="photoArea">
    <c:forEach var="photo" items="${initial}">
        <img data-magnify="gallery" data-src="${photo.photoPath}" src="${photo.thumbnailPath}" style="margin: 2px">
    </c:forEach>
</div>
</div>
<script  type="text/javascript">
    $(function () {
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

    jQuery(document).ready(function () {
        $("#checkin").on("click",function (){
            if($("#checkin").hasClass("进入中...")){
                return;
            }
            $("#checkin").addClass("进入中...");
            $("#checkin").val("进入中...");

            //传递参数到后端
            var params = {"selected":selected};
            $.ajax({
                type:"post",
                url:"/album/albumPhotoShow",
                dataType:"json",
                data:params,
                success:function(){
                    $("#checkin").removeClass("进入中...");
                    $("#checkin").val("进入");
                },
                error:function() {
                    $("#checkin").removeClass("进入中...");
                    $("#checkin").val("进入");
                }
            })
        })
    })

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
