<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>写博客</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" charset="UTF-8"
            src="${pageContext.request.contextPath}/static/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="UTF-8"
            src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.min.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="UTF-8"
            src="${pageContext.request.contextPath}/static/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script>
        $(function () {
            var ue = UE.getEditor('editor');


            //回显博客类别
            $("#type").combobox("setValue",${tBlog.typeId});
        });

        function submitUpdate() {
            $.post("${pageContext.request.contextPath}/admin/blog/update",{
                "id":${tBlog.id},
                "title":$("#title").val(),
                "content":UE.getEditor('editor').getContent(),
                "typeId":$("#type").combobox("getValue"),
                "keyword":$("#keyword").val(),
                "releaseDate":$("#releaseDate").val(),
                "summary":UE.getEditor('editor').getContentTxt().substr(0,200)
            },function(data){
                alert("博客修改成功!");
            },"json");
        }
    </script>

</head>
<body>
<table cellspacing="20px">
    <input type="hidden" id="releaseDate" name="releaseDate" value="${tBlog.releaseDate}"/>
    <tr>
        <td width="80px">博客标题:</td>
        <td><input id="title" style="width:400px" value="${tBlog.title}" data-options="required:true" class="easyui-validatebox"/></td>
    </tr>
    <tr>
        <td>选择类别:</td>
        <td>
            <select class="easyui-combobox" id="type">
                <c:forEach items="${blogTypes}" var="type">
                    <option value="${type.id}">${type.typeName}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td>内容:</td>
        <td>
            <script id="editor" type="text/plain" style="height:700px;width: 100%">${tBlog.content}</script>
        </td>
    </tr>
    <tr>
        <td>关键字:</td>
        <td>
            <input id="keyword" style="width:400px" value=${tBlog.keyword} data-options="required:true" class="easyui-validatebox"/>&nbsp;&nbsp;(关键字之间用空格隔开)
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
            <a href="javascript:submitUpdate()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">修改博客</a>
        </td>
    </tr>
</table>

</body>
</html>
