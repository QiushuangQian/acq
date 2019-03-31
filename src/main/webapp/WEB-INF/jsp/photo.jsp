<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>PhotoList</title>
    <!-- Link Swiper's CSS -->
    <link rel="stylesheet" href="/css/swiper.min.css">


    <style>
        html, body {
            position: relative;
            height: 100%;
        }
        body {
            background: #000;
            font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
            font-size: 14px;
            color:#000;
            margin: 0;
            padding: 0;
        }
        .swiper-container {
            width: 100%;
            height: 300px;
            margin-left: auto;
            margin-right: auto;
        }
        .swiper-slide {
            background-size: cover;
            background-position: center;
        }
        .gallery-top {
            height: 80%;
            width: 100%;
        }
        .gallery-thumbs {
            height: 20%;
            box-sizing: border-box;
            padding: 10px 0;
        }
        .gallery-thumbs .swiper-slide {
            height: 100%;
            opacity: 0.4;
        }
        .gallery-thumbs .swiper-slide-thumb-active {
            opacity: 1;
        }

    </style>
</head>
<body>
    <!-- Swiper -->
    <div class="swiper-container gallery-top">
        <div class="swiper-wrapper">
            <%--<c:forEach var="photo" items="${photoList}">--%>
                <%--<div class="swiper-slide" style="background-image:url(${photo.photoPath})"></div>--%>
            <%--</c:forEach>--%>
                <div class="swiper-slide" style="background-image:url(../resources/static/photo)"></div>
        <!-- Add Arrows -->
        <div class="swiper-button-next swiper-button-white"></div>
        <div class="swiper-button-prev swiper-button-white"></div>
    </div>
    <div class="swiper-container gallery-thumbs">
        <div class="swiper-wrapper">
            <%--<div class="swiper-slide" style="background-image:url(http://lorempixel.com/1200/1200/nature/1/)"></div>--%>
        </div>
    </div>

    <!-- Swiper JS -->
    <script src="/js/swiper.min.js"></script>

    <!-- Initialize Swiper -->
    <script>
        var galleryThumbs = new Swiper('.gallery-thumbs', {
            spaceBetween: 10,   //在slide之间设置距离（单位px）
            slidesPerView: 4,   //设置slider容器能够同时显示的slides数量(carousel模式)。
            loop: true, //设置为true 则开启loop模式。loop模式：会在原本slide前后复制若干个slide(默认一个)并在合适的时候切换，让Swiper看起来是循环的。
            freeMode: true, //默认为false，普通模式：slide滑动时只滑动一格，并自动贴合wrapper，设置为true则变为free模式，slide会根据惯性滑动可能不止一格且不会贴合。
            loopedSlides: 5, //looped slides should be the same     在loop模式下使用slidesPerview:'auto'，还需使用该参数设置所要用到的loop个数(一般设置大于可视slide个数2个即可)。
            watchSlidesVisibility: true,   //开启watchSlidesVisibility选项前需要先开启watchSlidesProgress，如果开启了watchSlidesVisibility，则会在每个可见slide增加一个classname，默认为'swiper-slide-visible'。
            watchSlidesProgress: true,      //开启这个参数来计算每个slide的progress(进度、进程)，Swiper的progress无需设置即开启。
        });
        var galleryTop = new Swiper('.gallery-top', {
            spaceBetween: 10,
            loop:true,
            loopedSlides: 5, //looped slides should be the same
            navigation: {   //使用前进后退按钮来控制Swiper切换。
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
            thumbs: {   //thumbs组件专门用于制作带缩略图的swiper，比使用controller组件更为简便。
                swiper: galleryThumbs,
            },
        });
    </script>
</body>
</html>