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
    <script src="/js/jquery/jquery-3.3.1.js"></script>
    <title>PhotoList</title>

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
                                    //创建img标签对象
                                    var img = document.createElement("img");
                                    img.src=data.photoList[i];
                                    img.style="margin: 2px";
                                    //向图片容器内添加图片
                                    container.appendChild(img);
                                }
                                //加载完成，开启加载标志
                                setTimeout(openLoadFlag,1000);
                                pagenum++;

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
            <img src='${photo}' alt='' style="margin: 2px">
        </c:forEach>

    </div>
</body>
</html>