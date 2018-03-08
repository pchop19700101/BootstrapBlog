<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reset.css"/>
<script src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
<meta name="viewport"
      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<style>
    .navbar-default {
        background-image: none;
    }

    .transparent_background_text {
        background-color: rgba(255, 255, 255, 0.45) !important;
    }

    .form-group .button {
        background-image: none;
        background-color: rgba(255, 255, 255, 0.9);
    }

    ${backgroundImage.imageCode}

    a {
        font-weight: bold;
    }
</style>


<!--导航条-->
<nav class="navbar navbar-default transparent_background">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/"><strong>首页</strong></a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/aboutme.html">关于我</a></li>
            </ul>
            <form class="navbar-form navbar-right" action="#">
                <div class="form-group">
                    <div class="input-group">
                        <input type="text" name="" class="form-control transparent_background_text"
                               placeholder="请输入查询的关键字">
                        <span class="input-group-btn">
                            <input value="搜索" class="btn btn-default button" type="submit">
                        </span>
                    </div>
                </div>

            </form>
        </div>
    </div>
</nav>






