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
    <link type="text/css" rel="stylesheet" href="/css/photoDisplay.css">
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/jquery.magnify.min.css" rel="stylesheet">
    <%--图片展示插件css--%>
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <%--Magnify 使用 font-awesome 的图标--%>

    <script src="/js/jquery/jquery-3.3.1.js"></script>
    <script src="/js/jquery.magnify.min.js"></script>
</head>
<body>
<div class="menu-button">
    <a href="/homepage">&lt;&lt; 返回</a>

    <input type="button" id="select" value="选择">
    <input type="button" id="selectAll" value="全选" disabled="disabled">
    <input type="button" id="delete" value="删除" disabled="disabled">
</div>
<div id="photoArea">
    <c:forEach var="photo" items="${initial}">
        <div class="photo">
            <img data-magnify="gallery" data-src="${photo.photoPath}" src="${photo.thumbnailPath}">
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

<script>
    var show = false;//是否显示复选框
    $(function () {
        var change = true;//用于鼠标二次点击翻转全选
        var pagenum=1;//页号，第几页
        var maxPageNum = ${maxPageNum};//最大分页数
        if(maxPageNum==1){//只有一页
            $("#first").prop('disabled',true);//禁用首页
            $("#before").prop('disabled',true);//禁用上一页
            $("#after").prop('disabled',true);  //禁用下一页
            $("#last").prop('disabled',true);   //禁用尾页
        }else{//多于一页
            $("#first").prop('disabled',true);//禁用首页
            $("#before").prop('disabled',true);//禁用上一页
            $("#after").removeAttr('disabled'); //启用下一页
            $("#last").removeAttr('disabled');//启用尾页
        }
        var container;//照片区域容器

        //照片浏览插件
        $('[data-magnify]').magnify({
            headToolbar: [
                'close'
            ],
            initMaximized: true,    //初始最大化
            multiInstances: false    //禁用多实例
        });

        //选择
        $("#select").on("click", function () {
            show = !show;
            $('input[name="group"]').css("visibility", show ? 'visible' : 'hidden');
            //删除和全选按钮是否可用
            if(!show){//禁用
                $("#delete").prop('disabled',true);
                $("#selectAll").prop('disabled',true);
            }else {//启用
                $("#delete").removeAttr('disabled');
                $("#selectAll").removeAttr('disabled');
            }
        })

        //删除
        $("#delete").on("click", function () {
            show=false;

            var id_array = new Array();
            $('input[name="group"]:checked').each(function () {
                id_array.push($(this).val());//向数组中添加元素  
            });

            var idstr = id_array.join(',');//将数组元素连接起来以构建一个字符串 
            $.ajax({
                type: "POST",
                url: "/deletePhoto",
                dataType: "json",
                data: {
                    "selectPhotoList": idstr
                },
                success: function (result) {
                    console.log(result.msg);
                    change=!change;
                    show=true;//删除照片之后继续显示复选框
                    maxPageNum=result.maxPageNum;
                    if(pagenum>result.maxPageNum){
                        pagenum--;
                    }
                    changeDisabled(pagenum);//改变翻页键的可用性
                    loadPhoto(pagenum); //重新加载这一页的照片
                },
                error:function (errorMsg) {
                    console.log(errorMsg.error());
                    change=!change;
                    show=true;
                    changeDisabled(pagenum);
                    loadPhoto(pagenum);
                }
            })
        })
        //全选
        $("#selectAll").on("click", function () {
            $("[name='group']").prop("checked",change);
            change = !change;
        })

        //首页
        $("#first").on("click",function () {
            pagenum=1;
            changeDisabled(pagenum);
            //返回第一页
            loadPhoto(pagenum);
        })
        //上一页
        $("#before").on("click",function () {
            pagenum--;
            changeDisabled(pagenum);
            loadPhoto(pagenum);
        })
        //下一页
        $("#after").on("click",function () {
            pagenum++;
            changeDisabled(pagenum);
            loadPhoto(pagenum);
        })
        //尾页
        $("#last").on("click",function () {
            pagenum=maxPageNum;
            changeDisabled(pagenum);
            loadPhoto(pagenum);
        })

        //根据pagenum确定翻页键的禁用与启动
        function changeDisabled(pagenum) {
            if(maxPageNum==1){//只有一页
                $("#first").prop('disabled',true);//禁用首页
                $("#before").prop('disabled',true);//禁用上一页
                $("#after").prop('disabled',true);  //禁用下一页
                $("#last").prop('disabled',true);   //禁用尾页
            }else{//多于一页
                if(pagenum==1){//首页
                    $("#first").prop('disabled',true);//禁用首页
                    $("#before").prop('disabled',true);//禁用上一页
                    $("#after").removeAttr('disabled'); //启用下一页
                    $("#last").removeAttr('disabled');//启用尾页
                }else if(pagenum==maxPageNum){//尾页
                    $("#first").removeAttr('disabled'); //启用首页
                    $("#before").removeAttr('disabled');//启用上一页
                    $("#after").prop('disabled',true);  //禁用下一页
                    $("#last").prop('disabled',true);   //禁用尾页
                }else {//在中间页
                    $("#first").removeAttr('disabled'); //启用首页
                    $("#before").removeAttr('disabled');//启用上一页
                    $("#after").removeAttr('disabled'); //启用下一页
                    $("#last").removeAttr('disabled');//启用尾页
                }
            }
        }
        //加载照片函数
        function loadPhoto(pagenum) {
            if(pagenum==0){
                pagenum=1;
            }
            //移除photoArea区域中的照片div
            $("#photoArea .photo").remove();
            //加载新的照片div
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
                                //创建照片div
                                var html='<div class="photo">'
                                    +'<img data-magnify="gallery" data-src="'+data.photoList[i].photoPath+'" src="'+data.photoList[i].thumbnailPath+'">'
                                    +'<input type="checkbox" name="group" value="'+data.photoList[i].photoId+'" style="visibility:'+(show ? "visible" : "hidden")+';" >'
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