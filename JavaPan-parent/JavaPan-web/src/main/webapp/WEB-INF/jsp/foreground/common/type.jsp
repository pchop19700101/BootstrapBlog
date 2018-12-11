<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .side_container {
        border-radius: 5px;
        padding: 8px;
        margin-bottom: 15px;
    }

    .side_container .side_title {
        font-size: 16px;

    }

    .side_container div {
        margin-bottom: 5px;
    }

</style>
<div class="transparent_background side_container clearfix text-center">
    <div class="side_title">
        <span class="glyphicon glyphicon-usd" aria-hidden="true"></span> <strong>福利</strong>
    </div>

    <c:forEach items="${advertisements}" var="adv">
        <div class="col-xs-12">
            <a href="${adv.linkUrl}" target="_blank">
                <img src="${adv.imageUrl}" class="img-responsive img-center">
            </a>
            <strong class="text-center" style="font-size: 15px;">${adv.comment}</strong>
        </div>
    </c:forEach>


    <%--    <div class="col-xs-12">
            <a href="https://www.vultr.com/?ref=7064755" target="_blank">
                <img src="https://www.vultr.com/media/banner_3.png" class="img-responsive img-center">
            </a>
            <strong class="text-center" style="font-size: 15px;">注册立即获赠10美元!</strong>
        </div>--%>


</div>
<div class="transparent_background side_container clearfix text-center">
    <div class="side_title">
        <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span> <strong>发布日期</strong>
    </div>


    <c:forEach var="month" items="${monthes}">
        <div class="col-xs-6">
            <a href="${pageContext.request.contextPath}/index.html?releaseMonth=${month.releaseMonth}">${month.releaseMonth}(${month.blogCount})</a>
        </div>
    </c:forEach>


    <%--    <div class="col-xs-6">
            <a href="#">2018.02(11111)</a>
        </div>
        <div class="col-xs-6">
            <a href="#">2018.02(11111)</a>
        </div>--%>

</div>
<div class="transparent_background side_container clearfix text-center">
    <div class="side_title">
        <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> <strong>资源类别</strong>
    </div>
    <c:forEach var="type" items="${blogTypes}">

        <div class="col-xs-6">
            <a href="${pageContext.request.contextPath}/index.html?typeId=${type.id}">${type.typeName}(${type.blogCount})</a>
        </div>

    </c:forEach>


<%--    <div class="col-xs-6">
        <a href="#">java(6)</a>

    </div>--%>

</div>
<div class="transparent_background side_container clearfix text-center">
    <div class="side_title">
        <span class="glyphicon glyphicon-link" aria-hidden="true"></span> <strong>友情链接</strong>
    </div>


    <c:forEach var="link" items="${links}">
        <div class="col-xs-6">
            <a href="${link.linkUrl}" target="_blank">${link.linkName}</a>
        </div>
    </c:forEach>


    <%--    <div class="col-xs-6">
            <a href="https://www.baidu.com/" target="_blank">百度一下</a>
        </div>--%>
</div>



















