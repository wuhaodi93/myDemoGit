<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ page language="java" contentType="text/html; charset=utf-8"
             pageEncoding="utf-8" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>欢迎登录后台管理系统</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        .errorMessage {
            font-size: 20px;
            font-weight: 200;
            color: orangered;
            letter-spacing: 2px;
            text-align: center;
        }
    </style>
    <script language="JavaScript" src="js/jquery/jquery.js"></script>
    <script src="js/cloud.js" type="text/javascript"></script>

    <script language="javascript">
        $(function () {
            $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            $(window).resize(function () {
                $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            })
        });
    </script>

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">


<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>


<div class="logintop">
    <span>欢迎登录后台管理界面平台</span>
</div>

<div class="loginbody">

    <span class="systemlogo"></span>

    <div class="loginbox">
        <ul>
            <div><span class="errorMessage"><%=session.getAttribute("loginErorr")%></span></div>
            <form method="post" action="doLogin.do">
                <li><input type="text" class="loginuser" placeholder="用户名" name="name"/></li>
                <li><input type="password" class="loginpwd" placeholder="密码" name="password"/></li>
                <li><input type="submit" class="loginbtn" value="登录"/><label><input name="" type="checkbox" value=""
                                                                                    checked="checked"/>记住密码</label><label><a
                        href="#">忘记密码？</a></label></li>
            </form>
        </ul>


    </div>

</div>


<div class="loginbm">版权所有 2013 .com 仅供学习交流，勿用于任何商业用途</div>
</body>
</html>
