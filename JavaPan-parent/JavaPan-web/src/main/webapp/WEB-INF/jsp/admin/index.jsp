<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台管理</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">

        var url;

        function openTab(text, url, iconCls) {
            if ($("#tabs").tabs("exists", text)) {
                $("#tabs").tabs("select", text);
            } else {
                $("#tabs").tabs("add", {
                    title: text,
                    iconCls: iconCls,
                    closable: true,
                    content: "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>"
                });
            }
        }

        function openPasswordModifyDialog() {
            $("#dlg").dialog("open").dialog("setTitle", "修改密码");
            url = "${pageContext.request.contextPath}/admin/user/changePassword";
        }

        function modifyPassword() {
            $("#fm").form("submit", {
                url: url,
                onSubmit: function () {
                    var password = $("#password").val();
                    var password2 = $("#password2").val();
                    if (!$(this).form("validate")) {
                        return false;
                    }
                    if (password != password2) {
                        $.messager.alert("系统提示", "确认密码输入错误！");
                        return false;
                    }
                    return true;
                },
                success: function (result) {
                    var result = eval('(' + result + ')');
                    if (result.message == 'ok') {
                        $.messager.alert("系统提示", "密码修改成功,下一次登录失效！");
                        resetValue();
                        $("#dlg").dialog("close");
                    } else {
                        $.messager.alert("系统提示", "密码修改失败！");

                    }
                }
            });
        }

        function closePasswordModifyDialog() {
            resetValue();
            $("#dlg").dialog("close");
        }

        function resetValue() {
            $("#password").val("");
            $("#password2").val("");
        }

        function refreshSystem() {
            $.post("${pageContext.request.contextPath}/refresh", {}, function (result) {
                if (result.message == 'ok') {
                    $.messager.alert("系统提示", "已成功刷新系统缓存！");
                } else {
                    $.messager.alert("系统提示", "刷新系统缓存失败！");
                }
            }, "json");
        }

        function logout() {
            $.messager.confirm("系统提示", "您确定要退出系统吗?", function (r) {
                if (r) {
                    window.location.href = "${pageContext.request.contextPath}/admin/user/logout";
                }
            });
        }
        function deleteAllAndReimportIndex() {
            $.messager.confirm("系统提示", "您确定要重新导入资源介绍到索引库吗?(时间比较长,请耐心等待)", function (r) {
                $.ajax({
                    "url": "${pageContext.request.contextPath}/admin/blog/deleteAllAndReimportIndex",
                    "data": {},
                    "type": "POST",
                    "dataType": "json",
                    "success": function (data) {
                        if (data.message == 'success') {
                            $.messager.alert("系统提示", "重新导入资源介绍到索引库成功！");
                        } else {
                            $.messager.alert("系统提示", "重新导入资源介绍到索引库失败！");
                        }
                    },
                    "error": function () {
                        $.messager.alert("系统提示", "重新导入资源介绍到索引库失败！");
                    }
                });
            });
        }
    </script>
</head>
<body class="easyui-layout">
<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页" data-options="iconCls:'icon-home'">
            <div align="center" style="padding-top: 100px"><font color="red" size="10">欢迎使用</font></div>
        </div>
    </div>
</div>
<div region="west" style="width: 200px" title="导航菜单" split="true">
    <div class="easyui-accordion" data-options="fit:true,border:false">
        <%--<div title="常用操作" data-options="selected:true,iconCls:'icon-item'" style="padding: 10px">
            <a href="javascript:openTab('发布资源','${pageContext.request.contextPath}/admin/page/writeBlog.html','icon-writeblog')"
               class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-writeblog'" style="width: 150px">发布资源</a>
            <a href="javascript:openTab('评论审核','${pageContext.request.contextPath}/admin/page/commentReview.html','icon-review')"
               class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-review'" style="width: 150px">评论审核</a>
        </div>--%>
        <div title="资源管理" data-options="iconCls:'icon-bkgl'" style="padding:10px;">
            <a href="javascript:openTab('发布资源','${pageContext.request.contextPath}/admin/page/writeBlog.html','icon-writeblog')"
               class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-writeblog'" style="width: 150px;">发布资源</a>
            <a href="javascript:openTab('资源信息管理','${pageContext.request.contextPath}/admin/page/blogManage.html','icon-bkgl')"
               class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-bkgl'" style="width: 150px;">资源信息管理</a>
        </div>
        <div title="资源类别管理" data-options="iconCls:'icon-bklb'" style="padding:10px">
            <a href="javascript:openTab('资源类别信息管理','${pageContext.request.contextPath}/admin/page/blogTypeManage.html','icon-bklb')"
               class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-bklb'" style="width: 150px;">资源类别信息管理</a>
        </div>
        <div title="评论管理" data-options="iconCls:'icon-plgl'" style="padding:10px">
            <%--            <a href="javascript:openTab('评论审核','${pageContext.request.contextPath}/admin/page/commentReview.html','icon-review')"
                           class="easyui-linkbutton"
                           data-options="plain:true,iconCls:'icon-review'" style="width: 150px">评论审核</a>--%>
            <a href="javascript:openTab('评论信息管理','${pageContext.request.contextPath}/admin/page/commentManage.html','icon-plgl')"
               class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-plgl'" style="width: 150px;">评论信息管理</a>
        </div>
        <div title="网站信息管理" data-options="iconCls:'icon-grxx'" style="padding:10px">
            <a href="javascript:openTab('修改网站介绍','${pageContext.request.contextPath}/admin/user/showModifyPage?id=1','icon-grxxxg')"
               class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-grxxxg'" style="width: 150px;">修改网站介绍</a>
        </div>
        <div title="系统管理" data-options="iconCls:'icon-system'" style="padding:10px">
            <%--<a href="javascript:openTab('友情链接管理','${pageContext.request.contextPath}/admin/page/linkManage.html','icon-link')"
               class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-link'" style="width: 150px">友情链接管理</a>--%>
            <a href="javascript:openPasswordModifyDialog()" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改密码</a>
            <a href="javascript:refreshSystem()" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-refresh'" style="width: 150px;">刷新系统缓存</a>
            <a href="javascript:deleteAllAndReimportIndex()" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-refresh'" style="width: 150px;">重新导入资源介绍到索引库</a>
            <a href="javascript:logout()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'"
               style="width: 150px;">安全退出</a>
        </div>
    </div>
</div>
<div region="south" style="height: 25px;padding: 5px" align="center">
    Copyright © 2018-2019 沈普铭 版权所有
</div>

<div id="dlg" class="easyui-dialog" style="width: 400px;height: 200px;padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>用户名：</td>
                <td>
                    <input type="hidden" value="${user.id}" name="id"/>
                    <input type="text" id="userName" name="userName" value="${user.username }"
                           readonly="readonly" style="width: 200px"/>
                </td>
            </tr>
            <tr>
                <td>新密码：</td>
                <td>
                    <input type="password" id="password" name="password" class="easyui-validatebox"
                           required="true" style="width: 200px"/>
                </td>
            </tr>
            <tr>
                <td>确认新密码：</td>
                <td>
                    <input type="password" id="password2" name="password2" class="easyui-validatebox"
                           required="true" style="width: 200px"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
</html>