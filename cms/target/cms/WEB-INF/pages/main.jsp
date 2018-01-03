<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasRole name="user"></shiro:hasRole>
<head>
    <%@ page language="java" contentType="text/html; charset=utf-8"
             pageEncoding="utf-8" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>网站后台管理系统<shiro:authenticated>认证</shiro:authenticated>
        <shiro:notAuthenticated>未认证</shiro:notAuthenticated></title></head>
<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="findTopJsp.do" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame"/>
    <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
        <frame src="findLeftJsp.do" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame"/>
        <frame src="findIndexPage.do" name="rightFrame" id="rightFrame" title="rightFrame" style="overflow-x: hidden"/>
    </frameset>
</frameset>
<noframes>
    <body>
    </body>
</noframes>
</html>
