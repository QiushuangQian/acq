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
</head>
<body>
<c:forEach var="paths" items="${pathList}">
    <img src="<%=request.getContextPath()%>/${paths}" alt="">
</c:forEach>
</body>
</html>