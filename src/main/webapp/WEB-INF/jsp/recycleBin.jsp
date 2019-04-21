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

    <%--<strong>注：只能恢复30天内删除的相片</strong>--%>
</div>
<div class="menu-button">
    <a href="/homepage">&lt;&lt; 返回</a>
    <input type="button" value="删除" id="delete">
    <input type="button" value="恢复" id="recover">
    <input type="button" value="全选" id="selectAll">
</div><br>
<div id="photoArea">
    <c:forEach var="photo" items="${initial}">
        <div class="photo">
            <div class="container" style="background-image: url('${photo.thumbnailPath}')"></div>
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
    var maxPageNum;
    $(function () {
        var change=true;//用于鼠标二次点击翻转全选
        var pagenum=1;//页号，第几页
        maxPageNum = (${maxPageNum}==0?1:${maxPageNum});//最大分页数
//        changeDisabled(maxPageNum);//根据最大页数改变翻页按钮状态
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
                    change=!change;
                    maxPageNum=result.maxPageNum;
                    if(pagenum>result.maxPageNum){
                        pagenum--;
                    }
                    changeDisabled(pagenum);//改变翻页键的可用性
                    loadPhoto(pagenum); //重新加载这一页的照片
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
                    change=!change;
                    maxPageNum=result.maxPageNum;
                    if(pagenum>result.maxPageNum){
                        pagenum--;
                    }
                    changeDisabled(pagenum);//改变翻页键的可用性
                    loadPhoto(pagenum); //重新加载这一页的照片
                }
            })
        })
        //全选
        $("#selectAll").on("click",function () {
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