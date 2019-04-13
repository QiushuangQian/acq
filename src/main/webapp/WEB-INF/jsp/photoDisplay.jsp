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
    <link href="/css/jquery.magnify.min.css" rel="stylesheet"><%--图片展示插件css--%>
    <link href="/css/font-awesome.min.css" rel="stylesheet"><%--Magnify 使用 font-awesome 的图标--%>

    <script src="/js/jquery/jquery-3.3.1.js"></script>
    <script src="/js/jquery.magnify.min.js"></script><%--图片展示插件js--%>

    <%--
    <style>
        .magnify-modal {
            box-shadow: 0 0 6px 2px rgba(0, 0, 0, 0.3);
        }

        .magnify-header .magnify-toolbar {
            background-color: rgba(0, 0, 0, .5);
        }

        .magnify-stage {
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            border-width: 0;
        }

        .magnify-footer {
            bottom: 10px;
        }

        .magnify-footer .magnify-toolbar {
            background-color: rgba(0, 0, 0, .5);
            border-radius: 5px;
        }

        .magnify-loader {
            background-color: transparent;
        }

        .magnify-header,
        .magnify-footer {
            pointer-events: none;
        }

        .magnify-button {
            pointer-events: auto;
        }
    </style>
    `--%>

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
                        url: "/photoList",
                        data:{
                            "pagenum":pagenum
                        },
                        success: function(data){
                            if(data.result){
                                for(var i=0;i<data.photoList.length;i++ ){
                                    if(data.photoList[i]!=null && data.photoList[i].photoPath!=null && data.photoList[i].thumbnailPath!=null){
                                        //创建img标签对象
                                        var img = document.createElement("img");
                                        img.src=data.photoList[i].thumbnailPath;
                                        img.style="margin: 2px";
                                        img.setAttribute("data-magnify","gallery");
                                        img.setAttribute("data-src",data.photoList[i].photoPath);

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

    <div id="photoArea">
        <c:forEach var="photo" items="${initial}">
            <img data-magnify="gallery" data-src="${photo.photoPath}" src="${photo.thumbnailPath}" style="margin: 2px">
        </c:forEach>
    </div>
    <script>
        $('[data-magnify]').magnify({
            headToolbar: [
                'close'
            ],
            initMaximized: true,    //初始最大化
            multiInstances:false    //禁用多实例
        });

    </script>
</body>
</html>