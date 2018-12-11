<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>关于本站</title>
    <meta name="keywords" content="个人,信息,关于"/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/icon.png" type="image/x-icon">
    <meta name="description" content="关于我个人的一些信息"  />
</head>
<body>
<jsp:include page="common/header.jsp"/>


<style>
    .about_container{
        border-radius: 8px;
    }
    .about_content{
        margin: 10px 15px;
    }
    .about_container .aboutme_title {
        font-size: 16px;
        margin: 8px 15px;
        text-align: center;
    }

    @media (min-width: 1200px) {
        .about_container .aboutme_title {
            font-size: 16px;
            text-align: left;
        }
    }
</style>


<div class="container">
    <div class="row">
        <!--博主信息列表-->
        <div class="col-lg-9" style="margin-bottom: 10px">






            <div class="transparent_background about_container clearfix">
                <div class="aboutme_title">
                    <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> <strong>关于我</strong>
                </div>
                <div class="about_content">
                   ${user.profile}
                </div>
            </div>






        </div>
        <!--end 博主信息列表-->
        <div class="col-lg-3">
            <jsp:include page="common/type.jsp"/>
        </div>
    </div>
    <!-- end row -->

</div>

<jsp:include page="common/foot.jsp"/>
</body>
</html>
