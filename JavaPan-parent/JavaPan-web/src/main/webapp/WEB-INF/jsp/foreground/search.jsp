<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>搜索"${queryString}"的结果页面-资源精灵</title>
    <meta name="keywords" content="资源精灵,java,spring,数据库"/>
    <meta name="description" content="资源精灵"/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/icon.png" type="image/x-icon">
</head>
<body>
<jsp:include page="common/header.jsp"/>


<style>


    .content_list_container .content_container {
        margin-left: 15px;
    }

    .content_list_container .content_seperator {
        border-top-color: darkgray;
        margin: 0px;
    }

    .content_container {
        margin: 8px 15px;
    }

    .content_container .content_title {
        font-size: 14px;
    }

    .content_container .content_summary {
        font-size: 13px;
        margin-top: 8px;
    }

    .content_container .content_info {
        margin-top: 8px;
        font-size: 13px;
    }

    .content_container .content_info div {
        float: right;
        margin-right: 8px;
    }

    .content_container img {
        max-height: 150px;
    }

    .content_list_container .content_list_title {
        font-size: 16px;
        text-align: center;
        margin: 8px 15px;
    }

    @media (min-width: 1200px) {
        .content_list_container .content_list_title {
            font-size: 16px;
            text-align: left;
        }
    }
</style>


<div class="container">
    <div class="row">
        <!--内容列表-->
        <div class="col-lg-9" style="margin-bottom: 10px">
            <div class="content_list_container transparent_background clearfix" style="border-radius: 10px;">
                <div class="content_list_title">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span><strong>搜索"<span
                        style="color: red">${queryString}</span>"的结果(共${pageBean.totalCount}个相关资源)</strong>
                </div>


                <c:forEach items="${pageBean.list}" var="blog" varStatus="i">

                    <hr class="content_seperator">
                    <div class="content_container">
                        <div class="content_title">
                            <a href="${pageContext.request.contextPath}/resource/${blog.id}.html">${blog.title}</a>
                        </div>
                        <div class="content_summary">
                                ${blog.summary}.......
                        </div>
                        <div class="content_info clearfix">
                            <div class="content_release_time">
                                发布时间: <fmt:formatDate value="${blog.releaseDate}" type="date"
                                                      pattern="yyyy-MM-dd HH:mm"/>
                            </div>
                        </div>
                    </div>
                </c:forEach>


            </div>


            <div class="text-center">
                <nav aria-label="Page navigation!=0">
                    <ul class="pagination  ">

                        <li <c:if test="${pageBean.prePage==0}"> class="disabled" </c:if>>
                            <a <c:if
                                    test="${pageBean.prePage!=0}"> href="${pageContext.request.contextPath}/blog/search.html?pageNum=${pageBean.prePage}&queryString=${queryString}" </c:if> aria-label="Previous" class="transparent_background">
                                <span aria-hidden="true">上一页</span>
                            </a>
                        </li>
                        <c:forEach items="${pageBean.paginationList}" var="page">
                            <li>
                                <a href="${pageContext.request.contextPath}/resource/search.html?pageNum=${page}&queryString=${queryString}"
                                   class="transparent_background">${page}</a>
                            </li>
                        </c:forEach>
                        <li <c:if test="${pageBean.nextPage==0}"> class="disabled" </c:if>>
                            <a <c:if
                                    test="${pageBean.nextPage!=0}"> href="${pageContext.request.contextPath}/blog/search.html?pageNum=${pageBean.nextPage}&queryString=${queryString}" </c:if>aria-label="Next"
                                    class="transparent_background">
                                <span aria-hidden="true">下一页</span>
                            </a>
                        </li>

                    </ul>
                </nav>
            </div>
        </div>
        <!--end 内容列表-->
        <div class="col-lg-3">
            <jsp:include page="common/type.jsp"/>
        </div>
    </div>
    <!-- end row -->

</div>

<jsp:include page="common/foot.jsp"/>
</body>
</html>
