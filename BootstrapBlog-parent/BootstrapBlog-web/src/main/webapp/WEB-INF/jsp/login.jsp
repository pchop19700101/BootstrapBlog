<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="no-js">
<head>
    <meta charset="utf-8">
    <title>登录(Login)</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/icon.png" type="image/x-icon">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/loginreset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/supersized.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/js/html5.js"></script>
    <![endif]-->

    <script src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/supersized.3.2.7.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/supersized-init.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/scripts.js"></script>
</head>

<body>

<div class="page-container">
    <h1>登录博客后台</h1>
    <form action="${pageContext.request.contextPath}/logins.html" method="post">
        <input type="text" value="${user.username}" name="username" class="username" placeholder="请输入您的用户名！"
               style="width:318px"/>
        <input type="password" name="password" class="password" placeholder="请输入您的用户密码！" style="width:318px"/>
        <input type="Captcha" class="Captcha" name="captcha" placeholder="请输入验证码！"/>
        <img src="${pageContext.request.contextPath}/validatecode.jsp" style="margin-top: 30px"
             onclick="this.src='${pageContext.request.contextPath }/validatecode.jsp?'+Math.random();"/>
        <button type="submit" class="submit_button">登录</button>
        <div style="text-align: center;margin-top: 20px;color: red;font-size: 20px">${errorInfo}</div>
        <div class="error"><span>+</span></div>
    </form>
</div>


</body>
</html>