<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script type="text/javascript" charset="gbk"
            src="${pageContext.request.contextPath}/static/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="gbk"
            src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.min.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="gbk"
            src="${pageContext.request.contextPath}/static/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script>
        $(function () {
            var ue = UE.getEditor('editor');


        });

        function submitUpdate() {
            $.post("${pageContext.request.contextPath}/admin/user/update",{
                "id":${tUser.id},
                "username":$("#title").val(),
                "profile":UE.getEditor('editor').getContent()
            },function(data){
                alert("修改成功!");
            },"json");
        }
    </script>

</head>
<body>
<form id="form">
<table cellspacing="20px">
    <tr>
        <td width="80px">用户名:</td>
        <td><input id="title" style="width:400px" value="${tUser.username}" data-options="required:true" class="easyui-validatebox"/></td>
    </tr>
    <tr>
        <td>内容:</td>
        <td>
            <script id="editor" type="text/plain" style="height:700px;width: 100%">${tUser.profile}</script>
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
            <a href="javascript:submitUpdate()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">修改个人信息</a>
        </td>
    </tr>
</table>
</form>
</body>
</html>
