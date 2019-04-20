<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qianqiushuang
  Date: 2019-03-30
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>SetAlbum</title>
    <script src="/js/jquery/jquery-3.3.1.js"></script>
</head>
<body>
<% request.setCharacterEncoding("UTF-8");%>
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
        <input type="button" id="delete" value="删除">
        <input type="button" id="changeAlbumName" value="修改相册名" onClick="if(show){document.all('newName').style.visibility='visible';document.all('doChange').style.visibility='visible';show=false;}
    else{document.all('newName').style.visibility='hidden';document.all('doChange').style.visibility='hidden';show=true;}">
    </div>
    <div class="setName">
        <input type="text" id="newName" placeholder="给你的相册取个新名字" style="visibility: hidden">
        <input type="button" id="doChange" value="修改" style="visibility: hidden">
    </div>
</div>
<script  type="text/javascript">
    $(function () {
        $("#recycleBin").on("click",function () {
            window.location.href="/recycleBin"
        });

        $("#back").on("click",function(){
            window.location.href="/homepage"
        });

        //上传照片——峰
        $("#upload").on("click",function () {
            window.location.href="/homepage/upload"
        });

        //创建相册——峰
        $("#createAlbum").on("click",function () {
            window.location.href="/homepage/createAlbum"
        })
    });


</script>
<script language="JavaScript">
    var show= true;
</script>


<script>
    //获取选中要删除的相册ID
    var selected;
    function changeAlbum() {
        selected = $('#albumSelect option:selected').val();
    }

    jQuery(document).ready(function () {

        $("#delete").on("click",function (){
            if($("#delete").hasClass("Deleting")){
                return;
            }
            $("#delete").addClass("Deleting");
            $("#delete").val("相册删除中...");

            //传递参数到后端
            var params = {"selected":selected};
            $.ajax({
                type:"post",
                url:"/album/doDeleteAlbum",
                dataType:"json",
                data: params,
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

<script>
    jQuery(document).ready(function () {
        $("#doChange").on("click",function () {
            if($("#doChange").hasClass("Changing")){
                return;
            }
            $("#doChange").addClass("Changing");
            $("#doChange").val("正在修改您的相册名");

            //获取选中要修改的相册ID和相册的新名字
            var selected = $('#albumSelect option:selected').val();
            var newAlbumName = $('#newName').val();

            //传递参数到后端
            $.ajax({
                type:"post",
                url:"/album/changeAlbumName",

                dataType:"json",
                data:{
                    "selected":selected,
                    "newAlbumName":newAlbumName,

                },
                success:function(){
                    $("#doChange").removeClass("Changing");
                    $("#doChange").val("Changed");
                },
                error:function() {
                    $("#doChange").removeClass("Changing");
                    $("#doChange").val("Change");
                }
            })
        })
    })
</script>
</body>
</html>
