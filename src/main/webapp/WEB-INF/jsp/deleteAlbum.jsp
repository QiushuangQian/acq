<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qianqiushuang
  Date: 2019-03-30
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DeleteAlbum</title>
    <script src="/js/jquery-3.3.1.js"></script>
</head>
<body>
<div class="selectAlbum">
    <select id="albumSelect" onchange="changeAlbum(this.value)">
        <option value="0" selected="selected">请选择相册</option>
        <c:forEach var="album1" items="${albumList}">
        <option value="${album1.albumId}">${album1.albumName}</option>
        </c:forEach>
        </select>
    <input type="button" id="delete" value="删除">
</div>

<script>
    var selected;
    function changeAlbum() {
        selected = $('#albumSelect option:selected').val();
    }

    jQuery(document).ready(function () {
        $("#delete").on("click",function () {
            if($("#delete").hasClass("Deleting")){
                return;
            }
            $("#delete").addClass("Deleting");
            $("#delete").val("相册删除中...");

            var params = {"selected":selected};
            $.ajax({
                type:"post",
                url:"/album/doDeleteAlbum",
                dataType:"json",
                data:params,
                success:function(){
                    $("#delete").removeClass("Deleting");
                    $("#delete").val("Deleted");
                },
                error:function() {
                    $("#delete").removeClass("Deleting");
                    $("#delete").val("Delete");
                }
            })
        })
    })
</script>
</body>
</html>
