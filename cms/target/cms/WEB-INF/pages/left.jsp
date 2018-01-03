<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ page language="java" contentType="text/html; charset=utf-8"
             pageEncoding="utf-8" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="js/jquery/jquery.js"></script>

    <script type="text/javascript">
        $(function () {
            //导航切换
            $(".menuson li").click(function () {
                $(".menuson li.active").removeClass("active")
                $(this).addClass("active");
            });
            $('.title').click(function () {
                var $ul = $(this).next('ul');
                $('dd').find('ul').slideUp();
                if ($ul.is(':visible')) {
                    $(this).next('ul').slideUp();
                } else {
                    $(this).next('ul').slideDown();
                }
            });
            //导航切换
            $(".menuson2 li").click(function () {
                $(".menuson2 li.active").removeClass("active")
                $(this).addClass("active");
            });
            $('.title2').click(function () {
                var $ul = $(this).next('ul');
                $('dd').find('ul').slideUp();
                if ($ul.is(':visible')) {
                    $(this).next('ul').slideUp();
                } else {
                    $(this).next('ul').slideDown();
                }
            });
        })
    </script>


</head>

<body style="background:#f0f9fd;">
<div class="lefttop"><span></span>管理设置</div>

<dl class="leftmenu">
    <dd>
        <shiro:hasRole name="admin">
            <div class="title"><span><img src="images/leftico04.png"/></span>app管理</div>
            <ul class="menuson">
                <li><cite></cite><a href="app/userAuth/app_authencation.html" target="rightFrame">认证审核</a><i></i>
                </li>
                <li><cite></cite><a href="app/merchantEnterAuthInfo/app_merchantEnterAudit.html"
                                    target="rightFrame">入驻审核</a><i></i>
                </li>
                <li><cite></cite><a href="app/merchantEnterInfo/app_merchantEnterInfo.html"
                                    target="rightFrame">入驻信息管理</a><i></i></li>
                <li><cite></cite><a href="app/userApplyInfo/userApplyInfo.html" target="rightFrame">贷款信息</a><i></i></li>
                <li><cite></cite><a href="app/pageManage/pageManage.html" target="rightFrame">页面管理</a><i></i></li>
                <li><cite></cite><a href="app/infoManage/authData.html" target="rightFrame">信息统计</a><i></i></li>
                <li><cite></cite><a href="app/userInfo/app_userInfo.html" target="rightFrame">用户信息</a><i></i></li>
                <li><cite></cite><a href="app/version/app_version.html" target="rightFrame">版本更新</a><i></i></li>
                <li><cite></cite><a href="https://aso100.com/andapp/samePubApp/appid/5132978"
                                    target="rightFrame">下载量统计</a><i></i></li>
                <li><cite></cite><a href="app/userFeedBack/app_userFeedBack.html" target="rightFrame">用户反馈</a><i></i>
                </li>
            </ul>
        </shiro:hasRole>
    </dd>
</dl>
</body>
</html>
