<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/3/16
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>PhotoList</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/jquery.magnify.min.css" rel="stylesheet">
    <%--图片展示插件css--%>
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <%--Magnify 使用 font-awesome 的图标--%>

    <script src="/js/jquery/jquery-3.3.1.js"></script>
    <script src="/js/jquery.magnify.min.js"></script>
    <link type="text/css" rel="stylesheet" href="/css/photoDisplay.css">
    <%--图片展示插件js--%>
    <script type="text/javascript">
        var pagenum = 2;//页号
        var loadFlag = true; //加载标志
        var container;//照片区域容器
        window.onload = function () {
            container = document.getElementById("photoArea");//得到图片区域容器对象div，用于img标签的附加
        }
        $(window).scroll(function () {
            //当滚轮滚动到文档最末位，也就是拉到了最底下
            //$(window).scrollTop():为滚动条在Y轴上的滚动距离。
            //$(window).height():为内容可视区域的高度。
            //$(document).height():当前页面的总高度  为内容可视区域的高度加上溢出（滚动）的距离。
            if (loadFlag && ($(window).scrollTop() + $(window).height() >= $(document).height() - 5)) {
                if (loadFlag) {
                    loadFlag = false;
                    //发送ajax请求获取数据
                    $.ajax({
                        type: "POST",
                        url: "/photo/photoList",
                        data: {
                            "pagenum": pagenum
                        },
                        success: function (data) {
                            if (data.result) {
                                for (var i = 0; i < data.photoList.length; i++) {
                                    if (data.photoList[i] != null && data.photoList[i].photoPath != null && data.photoList[i].thumbnailPath != null) {
                                        //创建img标签对象
                                        var img = document.createElement("img");
                                        img.src = data.photoList[i].thumbnailPath;
                                        img.style = "margin: 2px";
                                        img.setAttribute("data-magnify", "gallery");
                                        img.setAttribute("data-src", data.photoList[i].photoPath);

                                        //向图片容器内添加图片
                                        container.appendChild(img);
                                    }

                                }

                                //加载完成，开启加载标志
                                setTimeout(openLoadFlag, 1000);
                                pagenum++;

                                $('[data-magnify]').magnify({
                                    headToolbar: [
                                        'close'
                                    ],
                                    initMaximized: true,    //初始最大化
                                    multiInstances: false    //禁用多实例
                                });

                            } else {
                            }
                        }
                    });
                }

            }
        });
        function openLoadFlag() {
            loadFlag = true;//启动加载标志
        }

    </script>

</head>
<body>
<div>
    <input type="button" id="select" value="选择">
    <input type="button" value="全选" id="selectAll">
    <input type="button" id="delete" value="删除">

    <a href="/homepage">&lt;&lt; 返回</a>
</div>
<div id="photoArea">
    <c:forEach var="photo" items="${initial}">
        <div class="c1">
            <img data-magnify="gallery" data-src="${photo.photoPath}" src="${photo.thumbnailPath}"><br>
            <input class="c2" type="checkbox" name="group" value="${photo.photoId}">
        </div>
    </c:forEach>
</div>


<script>
    var show = true;
    $('[data-magnify]').magnify({
        headToolbar: [
            'close'
        ],
        initMaximized: true,    //初始最大化
        multiInstances: false    //禁用多实例
    });
    $("#select").on("click", function () {
        $('input[name="group"]').css("visibility", show ? 'visible' : 'hidden');
        show = !show;
    })
    $("#delete").on("click", function () {
        var id_array = new Array();
        $('input[name="group"]:checked').each(function () {
            id_array.push($(this).val());//向数组中添加元素  
        });

        var idstr = id_array.join(',');//将数组元素连接起来以构建一个字符串  


        $.ajax({
            type: "POST",
            url: "/deletePhoto",
            dataType: "json",
            data: {
                "selectPhotoList": idstr
            },
            success: function (result) {
                console.log(result.msg);
                window.location.reload();
            }
        })
    })
    //全选

    var change = true;//用于鼠标二次点击翻转全选
    $("#selectAll").on("click", function () {
        if (change) {
            $("[name='group']").prop("checked", true);
            change = false;
        } else {
            $("[name='group']").prop("checked", false);
            change = true;
        }

    })
</script>
</body>
</html>