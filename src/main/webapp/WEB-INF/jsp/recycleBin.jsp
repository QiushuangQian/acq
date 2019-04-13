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
    <strong>注：只能恢复30天内删除的相片</strong>
</div>
<div >
    <input type="button" value="删除" id="delete">
    <input type="button" value="恢复" id="recover">
    <input type="button" value="清空回收站" id="clear">
</div><br>
<div id="list">
    <div>


        <c:forEach var="photo" items="${initial}">
            <div style="background: url('${photo.thumbnailPath}');width: 300px;height: 200px;float:left" >
                <input type="checkbox"  name="group" value="${photo.photoId}">
            </div>


            <%--<label for="a"><img class="check-box"alt="选中" src="${photo.thumbnailPath}">--%>
            <%--</label>--%>
            <%--<input type="checkbox"  id="a" value="${photo.photo_id}">--%>

        </c:forEach>
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
    //alert(idstr);

    $.ajax({
        type:"POST",
        url:"/doRecycleBin",
        dataType:"json",
        data:{
            "delPhotoList":idstr
        },
        success:function (result) {
            window.location.reload();
        }
    })
})




</script>


</body>
</html>