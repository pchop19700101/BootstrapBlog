<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>猪扒的博客~</title>
    <meta name="keywords" content="博客,java,spring,数据库"/>
    <meta name="description" content="猪扒的博客,分享我的生活与学习~"  />
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
    }

    @media (min-width: 1200px) {
        .content_list_container .content_list_title {
            font-size: 16px;
            margin: 8px 15px;
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
                    <span class="glyphicon glyphicon-list" aria-hidden="true"></span> <strong>最新文章</strong>
                </div>


                <c:forEach items="${pageInfo.list}" var="blog" varStatus="i">

                    <div class="content_container">
                        <div class="content_title">
                            <a href="${pageContext.request.contextPath}/blog/${blog.id}.html">${blog.title}</a>
                        </div>
                        <div class="content_summary">
                                ${blog.summary}.........
                        </div>
                        <div>
                            <a href="${pageContext.request.contextPath}/blog/${blog.id}.html">
                                <c:forEach items="${blog.imageList}" var="img">
                                    ${img}
                                </c:forEach>
                            </a>
                        </div>
                        <div class="content_info clearfix">
                            <div class="content_comment_count">
                                评论(${blog.commentCount})
                            </div>
                            <div class="content_read_count">
                                阅读(${blog.clickCount})
                            </div>
                            <div class="content_release_time">
                                <fmt:formatDate value="${blog.releaseDate}" type="date" pattern="yyyy-MM-dd HH:mm"/>
                            </div>
                        </div>
                    </div>
                    <c:if test="${i.index>0}">
                        <hr class="content_seperator"/>
                    </c:if>
                </c:forEach>


                <%--                <div class="content_container">
                                    <div class="content_title">
                                        <a href="#">redis整合spring的实用工具类</a>
                                    </div>
                                    <div class="content_summary">
                                        摘要: Cookie二级域名跨域需要设置:1.setDomain，设置一级域名： .porkchop.cn .baidu.com2.setPath。设置为“/”获得域名的方法/** *
                                        获得当前网站的域名,不包含端口 */private String getDomain(HttpServletReques...
                                    </div>
                                    <div class="content_info clearfix">
                                        <div class="content_comment_count">
                                            评论(0)
                                        </div>
                                        <div class="content_read_count">
                                            阅读(35)
                                        </div>
                                        <div class="content_release_time">
                                            2018-02-01 23:15
                                        </div>
                                    </div>
                                </div>
                                <hr class="content_seperator"/>--%>
            </div>


            <div class="text-center">
                <nav aria-label="Page navigation!=0">
                    <ul class="pagination  ">

                        <c:if test="${pageInfo.prePage!=0}">
                            <li>
                                <a href="${pageContext.request.contextPath}/index.html?pageNum=${pageInfo.prePage}${params}"
                                   aria-label="Previous" class="transparent_background">
                                    <span aria-hidden="true">上一页</span>
                                </a>
                            </li>

                        </c:if>

                        <c:forEach items="${pageInfo.navigatepageNums}" var="pageNum">
                            <li>
                                <a href="${pageContext.request.contextPath}/index.html?pageNum=${pageNum}${params}"
                                   class="transparent_background">${pageNum}</a>
                            </li>
                        </c:forEach>


                        <%--<li><a href="#" class="transparent_background">5</a></li>--%>
                        <c:if test="${pageInfo.nextPage!=0}">

                            <li>
                                <a href="${pageContext.request.contextPath}/index.html?pageNum=${pageInfo.nextPage}${params}"
                                   aria-label="Next" class="transparent_background">
                                    <span aria-hidden="true">下一页</span>
                                </a>
                            </li>
                        </c:if>

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
