<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>回收站</title>
    <script src="/js/jquery/jquery-3.3.1.js"></script>
</head>
<body>
<div class="p-body j-album">
    <div class="m-recycle-wrapper">
        <div class="m-recycle-bin" id="J-photo_recycle_body">
            <div class="m-title">
                <h1>回收站</h1> <span class="count">(共<b id="J-recycle-photo-count">0</b>张)</span>　
                <a href="/homepage">&lt;&lt; 返回</a>
                <strong>注：只能恢复30天内删除的相片</strong>
            </div>
            <div class="m-panel clearfix">
                <p class="select"><a href="javascript:;" id="J-select-all">全选</a> <span class="bar">|</span> <a href="javascript:;" id="J-select-inverse">反选</a></p>
                <p class="restore"><a href="javascript:;" id="J-restore">恢复</a></p>
                <p class="remove"><a href="javascript:;" id="J-remove">删除</a></p>
                <p class="clear"><a href="javascript:;" id="J-clear">清空回收站</a></p>
            </div>
            <div id="J-recycle-hint" class="m-hint w-hint" style="display:none;">
                <div class="w-hint-head"><span class="icn0 icn0-49"></span></div>
                <div class="w-hint-body bdwa bds0 bdc23 bgc7 fc2"><b>:)  </b> <div class="msg">暂无可恢复的相片。</div></div>
            </div>
            <ul class="m-gallery" id="J-recycle-gallery"></ul>
            <ul class="m-pager pager" id="J-recycle-pager"></ul>
        </div>
    </div>
</div>
</body>
</html>