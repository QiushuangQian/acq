<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>回收站</title>
    <link type="text/css" rel="stylesheet" href="/css/recycleBin.css">

    <script src="/js/jquery/jquery-3.3.1.js"></script>
</head>
<body>
<div >
    <h1>回收站</h1>
    <a href="/homepage">&lt;&lt; 返回</a>
    <%--<strong>注：只能恢复30天内删除的相片</strong>--%>
</div>
<div >
    <input type="button" value="删除" id="delete">
    <input type="button" value="恢复" id="recover">
    <input type="button" value="全选" id="selectAll">
</div><br>
<div id="list">
    <div id="photoArea">
        <c:forEach var="photo" items="${initial}">
            <div class="photo" >
                <img src="${photo.thumbnailPath}"><br>
                <input type="checkbox" name="group" value="${photo.photoId}" >
            </div>
        </c:forEach>
    </div><br>

    <div class="fenye">
        <input type="button" value="首页" id="first">
        <input type="button" value="上一页" id="before">
        <input type="button" value="下一页" id="after">
        <input type="button" value="末页" id="last">
    </div>
</div>

<script>
    var pagenum=0;//页号，第几页
    var container;//照片区域容器
    $(function () {
        //彻底删除照片
        $("#delete").on("click",function () {
            //选中照片id的列表
            var id_array=new Array();
            $('input[name="group"]:checked').each(function(){
                id_array.push($(this).val());//向数组中添加元素  
            });

            var idstr=id_array.join(',');//将数组元素连接起来以构建一个字符串  

            $.ajax({
                type:"POST",
                url:"/doRecycleBin",
                dataType:"json",
                data:{
                    "selectPhotoList":idstr
                },
                success:function (result) {
                    console.log(result.msg);
                    window.location.reload();

                }
            })
        })

        //恢复
        $("#recover").on("click",function () {
            //选中照片id的列表
            var id_array=new Array();
            $('input[name="group"]:checked').each(function(){
                id_array.push($(this).val());//向数组中添加元素  
            });

            var  idstr=id_array.join(',');//将数组元素连接起来以构建一个字符串  
            //alert(idstr);

            $.ajax({
                type:"POST",
                url:"/restorePhoto",
                dataType:"json",
                data:{
                    "selectPhotoList":idstr
                },
                success:function (result) {
                    console.log(result.msg);
                    window.location.reload();

                }
            })
        })

        //全选
        var change=true;//用于鼠标二次点击翻转全选
        $("#selectAll").on("click",function () {
            $("[name='group']").prop("checked",change);
            change = !change;
        })


        //首页
        $("#first").on("click",function () {
            //返回第一页
            loadPhoto(1);
        })

        function loadPhoto(pagenum) {

            //移除photoArea区域中的照片div
            $("#photoArea .photo").remove();
            //加载新的照片div
            $.ajax({
                type: "POST",
                url: "/delPhotoList",
                data:{
                    "pagenum":pagenum
                },
                success: function(data){
                    if(data.result){
                        for(var i=0;i<data.delPhotoList.length;i++ ){
                            if(data.delPhotoList[i]!=null && data.delPhotoList[i].photoPath!=null && data.delPhotoList[i].thumbnailPath!=null){
                                //创建照片div
                                var html='<div class="photo">'
                                        +'<div class="container" style="background-image: url('+data.delPhotoList[i].thumbnailPath+')"></div>'
                                        +'<input type="checkbox" name="group" value="'+data.delPhotoList[i].photoId+'">'
                                        +'</div>';

                                $("#photoArea").append(html);
                            }
                        }
                    }else{
                        alert("pagenum为空！");
                    }
                },
                error:function () {
                }
            });
        }
    })




</script>


</body>
</html>