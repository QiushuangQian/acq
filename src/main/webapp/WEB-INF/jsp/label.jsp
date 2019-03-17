<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/3/16
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Label</title>

    <script src="../../js/jquery-3.3.1.js"></script>


</head>

<body>
<div>Label Id:${label.labelId}</div>
<div>Label name:${label.labelName}</div>


<div>
    <input id="labelName" type="text" style="width: 100px;height:50px">
    <input id="button" type="button" onclick="getlabel()">
    <script type="text/javascript" >
        //    $("#button").on("click",insertLabel);
        //    function insertLabel() {
        //        alert($("#labelName").text());
        //
        //    }

       // $("#button").click(function () {
      //      alert(1);
       // })
        function getlabel() {
            alert($("#labelName").val());
        }

    </script>
</div>
</body>
</html>