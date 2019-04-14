<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>回收站</title>
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
    <div id="photoArea" style="width: 100%">
        <c:forEach var="photo" items="${initial}">
            <div style="background: url('${photo.thumbnailPath}');width: 300px;height: 150px;float:left" >
                <input type="checkbox"  name="group" value="${photo.photoId}" style="float: right;bottom: auto;" >
            </div>
        </c:forEach>

    </div><br>
    <div style="width: 100%">
        <input type="button" value="首页" id="first">
        <input type="button" value="上一页" id="before">
        <input type="button" value="下一页" id="after">
        <input type="button" value="末页" id="last">
    </div>
</div>

<script>
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

        var idstr=id_array.join(',');//将数组元素连接起来以构建一个字符串  
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
        if(change){
            $("[name='group']").prop("checked",true);
            change=false;
        }else {
            $("[name='group']").prop("checked",false);
            change=true;
        }

    })




</script>


</body>
</html>