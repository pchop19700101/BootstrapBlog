<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${blog.title}-云盘精灵</title>
    <meta name="keywords" content="<c:forEach items="${blog.keywordList}" var="keyword" varStatus="i"><c:if test="${i.index!=0}">,</c:if> ${keyword}</c:forEach>"/>
    <meta name="description" content="云盘精灵"  />
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/icon.png" type="image/x-icon">
</head>


<body>


<jsp:include page="common/header.jsp"/>


<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css"/>
<script type="text/javascript">

    $(function () {
        SyntaxHighlighter.highlight();

        //把显示的介绍图片变为响应式
        $("p img").addClass("img-responsive");
    });


    function submitComment() {
        $.post(
            "${pageContext.request.contextPath}/comment/submitComment",
            {
                "captcha": $("#captcha").val(),
                "content": $("#commentArea").val(),
                "blogId":${blog.id},
            },
            function (data) {
                alert(data.message);
            },
            "json"
        );

    }
</script>


<style>

    .syntaxhighlighter {
        background-color: rgba(255, 255, 255, 0.45) !important;
    }

    .syntaxhighlighter .gutter {
        background-color: rgba(255, 255, 255, 0.45) !important;
    }

    .blog_container {
        border-radius: 8px;
    }

    .blog_content .blog_title {
        font-size: 20px;
    }

    .blog_content div {
        margin-top: 10px;
    }

    .blog_content .blog_share {
        width: 151px;
        height: 28px;
        margin: 8px auto;
    }

    .blog_content .blog_keyword {
        font-size: 17px;
        margin: 100px 0px 30px 20px;
    }

    .other_blog {
        margin-bottom: 15px;
        margin-left: 20px;
        font-size: 15px;
    }

    .comment_list_container, .comment_form_container, .blog_container {
        border-radius: 8px;
        margin-bottom: 12px;
        padding: 20px;
    }

    .comment_list_container .comment_container, .comment_list_container .title {
        margin-bottom: 15px;
    }

    .comment_container .comment_content {
        margin-left: 30px;
    }

    .comment_list_container .title {
        font-size: 18px;
    }


</style>


<div class="container">
    <div class="row">
        <!--资源列表-->
        <div class="col-lg-9" style="margin-bottom: 10px">


            <div class="transparent_background blog_container clearfix">
                <div class="blog_content">
                    <div class="blog_title text-center">
                        <strong>${blog.title}</strong>
                    </div>
                    <div class="blog_share">
                        <div class="bshare-custom"><a title="分享到QQ空间" class="bshare-qzone"></a><a title="分享到新浪微博"
                                                                                                  class="bshare-sinaminiblog"></a><a
                                title="分享到Twitter" class="bshare-twitter"></a><a title="分享到Facebook"
                                                                                 class="bshare-facebook"></a><a
                                title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a><span
                                class="BSHARE_COUNT bshare-share-count">0</span></div>
                        <script type="text/javascript" charset="utf-8"
                                src="https://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=2&amp;lang=zh"></script>
                        <script type="text/javascript" charset="utf-8"
                                src="https://static.bshare.cn/b/bshareC0.js"></script>
                    </div>
                    <div class="blog_info text-center">
                        发布时间: <fmt:formatDate value="${blog.releaseDate}" type="date" pattern="yyyy-MM-dd HH:mm"/>
                        &nbsp;&nbsp;类别: ${blog.blogTypeName} &nbsp;&nbsp;阅读数:${blog.clickCount}
                    </div>
                    <div class="blog_article">
                        ${blog.content}
                    </div>
                    <div class="blog_keyword">
                        关键字:
                        <c:forEach items="${blog.keywordList}" var="keyword">
                            <strong>${keyword}</strong>
                        </c:forEach>
                    </div>
                    <hr>
                    <div class="other_blog">
                        上一篇:

                        <c:choose>
                            <c:when test="${preBlog!=null}">
                                <a href="${pageContext.request.contextPath}/resource/${preBlog.id}">${preBlog.title}</a>
                            </c:when>
                            <c:otherwise>
                                无
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="other_blog">
                        下一篇:
                        <c:choose>
                            <c:when test="${nextBlog!=null}">
                                <a href="${pageContext.request.contextPath}/resource/${nextBlog.id}">${nextBlog.title}</a>
                            </c:when>
                            <c:otherwise>
                                无
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <!--添加评论-->
            <div class="comment_form_container transparent_background clearfix">
                <form action="">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-group">
                                <label for="commentArea" style="font-size: 16px;"><span
                                        class="glyphicon glyphicon-pencil" aria-hidden="true"></span>发表评论</label>
                                <textarea class="form-control transparent_background_text" id="commentArea"
                                          placeholder="来说几句吧~"
                                          rows="4"></textarea>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label for="captcha">验证码:</label>
                                <input type="text" class="form-control transparent_background_text" id="captcha"
                                       style="display: inline-block;width: 100px">
                                <img src="${pageContext.request.contextPath}/validatecode.jsp" style=""
                                     onclick="this.src='${pageContext.request.contextPath }/validatecode.jsp?'+Math.random();"/>
                            </div>
                        </div>
                        <div class="col-lg-2 col-lg-offset-6">
                            <a href="javascript:void(0);" class="btn btn-default btn-block"
                               style="opacity: 0.6 ;font-weight: bold"
                               onclick="submitComment()">提交
                            </a>
                        </div>
                    </div>
                </form>
            </div>
            <!--评论列表-->
            <div class="comment_list_container transparent_background">
                <div class="title">
                    <span class="glyphicon glyphicon-comment" aria-hidden="true"></span>最新评论
                </div>
                <c:if test="${commentList.size()==0}">
                    暂无评论
                </c:if>
                <c:forEach items="${commentList}" var="comment">
                    <div class="comment_container">
                        <div class="comment_info">
                            来自${comment.userIp}的网友在
                            <fmt:formatDate value="${comment.commentDate}" pattern="yyyy-MM-dd HH:mm"/>时说:
                        </div>
                        <div class="comment_content">
                                ${comment.content}
                        </div>
                    </div>
                </c:forEach>
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
