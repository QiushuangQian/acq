<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/3/16
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="/js/jquery/jquery-3.3.1.js"></script>
    <title>PhotoList</title>
    <!--<script type="text/javascript">
        function showFile(name,downURL) {
            var path = "/photo/"+name;
            var html = "<img alt='暂无图片' src="+path+"  width='180px' onclick='showFile(this)'/>";
            var picture = realName(name);
            html+=picture+"<a href="+downURL+">下载</a><br\>";
            $('#picture').append(html);
        }

        function showFile(obj) {
            window.location.href=obj.src;
        }

        function realName(key) {
            var arr = key.split("-");
            var name = arr[5];
            for(var i =6 ;i<arr.length;i++){
                name = name+"-"+arr[i];
            }
            return name;
        }
    </script>-->
</head>
<body>
<c:forEach var="paths" items="${pathList}">
    <img src="${paths}" alt="">
    <!--
    <script type="text/javascript">
    $(function () {
        showFile('${me.key}','${downURL}')
    })
</script>-->
</c:forEach>
<div><img src="images\thumbnail\201903311628091735902705.JPEG"></div>
<img src="images\thumbnail\201903311628091735902705.JPEG">
</body>
</html>