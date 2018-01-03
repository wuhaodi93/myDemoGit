<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ page language="java" contentType="text/html; charset=utf-8"
             pageEncoding="utf-8" %>
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="js/jquery/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            //顶部导航切换
            $(".nav li a").click(function () {
                $(".nav li a.selected").removeClass("selected")
                $(this).addClass("selected");
            })
        })
    </script>
</head>
<body style="background:url(images/topbg.gif) repeat-x;">
<div class="topleft">
    <a href="main.html" target="_parent"><img src="images/logo.png" title="系统首页"/></a>
</div>
<ul class="nav">
    <shiro:hasRole name="admin">
        <li><a href="findIndexPage.do" target="rightFrame" class="selected"><img src="images/icon01.png" title="首页"/>
            <h2>首页</h2></a></li>
        <li><a href="user/html/userManage.html" target="rightFrame"><img src="images/icon02.png" title="用户管理"/>
            <h2>用户管理</h2></a></li>
        <li><a href="#"><img src="images/icon02.png" title="权限管理"/>
            <h2>权限管理</h2></a></li>
    </shiro:hasRole>
    <shiro:hasPermission name="query">
        <li><a href="query/queryservice.html" target="rightFrame"><img src="images/service.png" title="服务查询"/>
            <h2>服务查询</h2></a></li>
    </shiro:hasPermission>
    <shiro:hasRole name="admin">
        <li><a href="query/queryphone.html" target="rightFrame"><img src="images/apple_45x45.png" title="序列号查询"/>
            <h2>序列号查询</h2></a></li>
    </shiro:hasRole>
    <shiro:hasRole name="admin">
        <li><a href="tab.html" target="rightFrame"><img src="images/icon06.png" title="系统设置"/>
            <h2>系统设置</h2></a></li>
    </shiro:hasRole>
</ul>
<div class="topright">
    <ul>
        <li><span><img src="images/help.png" title="帮助" class="helpimg"/></span><a href="#">帮助</a></li>
        <li><a href="#">关于</a></li>
        <li><a href="logout.do" target="_parent">退出</a></li>
    </ul>

    <div class="user">
        <span><shiro:principal/></span>
        <i>消息</i>
        <b>5</b>
    </div>
</div>
</body>
</html>
