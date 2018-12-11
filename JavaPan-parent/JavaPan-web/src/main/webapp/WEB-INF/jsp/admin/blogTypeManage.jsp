<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>资源管理页面</title>
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

        //定义列
        var columns = [[
            {field: 'id', title: '编号', checkbox: true},
            {
                field: 'typeName', title: '类型名称', width: 40, align: 'center'
            },
            {field: 'orderNum', title: '排序序号', align: 'center', width: 20}
        ]];


        $(function () {
            $('#dg').datagrid({
                url: '${pageContext.request.contextPath}/admin/blogType/findAll',
                fit: true,
                pageList: [10, 20, 30, 40, 50],
                rownumbers: true,
                pagination: true,
                fitColumns: true,
                columns: columns,
                toolbar: "#tb"
            });
        })


        function deleteBlogType() {
            var rows = $("#dg").datagrid("getSelections");
            if (rows.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据！");
                return;
            } else {
                $.messager.confirm("系统提示", "您确定要删除这" + rows.length + "条数据吗？", function (r) {
                    if (r) {
                        var arr = [];
                        for (var i = 0; i < rows.length; i++) {
                            arr.push(rows[i].id);
                        }
                        var params = {"ids": arr.join(",")};
                        $.ajax({
                            "url": '${pageContext.request.contextPath}/admin/blogType/delete',
                            "data": params,
                            "type": "POST",
                            "dataType": "json",
                            "success": function (data) {
                                $.messager.alert("系统提示", "删除成功!");
                                $("#dg").datagrid("reload");
                            },
                            "error": function () {
                                $.messager.alert("系统提示", "有关联的资源,不能删除!");
                            }
                        });
                    }
                })
            }
        }

        function addBlogType() {
            var typeName = $("#addTypeName").val();
            var orderNum = $("#addOrderNum").val();
            if (!$("#addForm").form("validate")) {
                return;
            }
            var params = {"typeName": typeName, "orderNum": orderNum};
            $.ajax({
                "url": '${pageContext.request.contextPath}/admin/blogType/add',
                "data": params,
                "type": "POST",
                "dataType": "json",
                "success": function (data) {
                    $("#add_dlg").dialog("close");
                    $.messager.alert("系统提示", "添加成功!");
                    $("#dg").datagrid("reload");
                    //清空
                    $("#addTypeName").val('');
                    $("#addOrderNum").val('');
                },
                "error": function () {
                    $.messager.alert("系统提示", "请稍后重试");
                }
            });
        }
        function updateBlogType() {
            var typeName = $("#typeName").val();
            var orderNum = $("#orderNum").val();
            var id = $("#id").val();
            if (!$("#updateForm").form("validate")) {
                return;
            }
            var params = {"typeName": typeName, "orderNum": orderNum, "id": id};
            $.ajax({
                "url": '${pageContext.request.contextPath}/admin/blogType/update',
                "data": params,
                "type": "POST",
                "dataType": "json",
                "success": function (data) {
                    $("#update_dlg").dialog("close");
                    $.messager.alert("系统提示", "修改成功!");
                    $("#dg").datagrid("reload");
                },
                "error": function () {
                    $.messager.alert("系统提示", "请稍后重试");
                }
            });
        }
        function openAddTab() {
            $("#add_dlg").dialog("open");
        }
        function openUpdateTab() {
            var rows = $("#dg").datagrid("getSelections");
            if (rows.length != 1) {
                $.messager.alert("系统提示", "请选择一个要修改的数据！");
                return;
            }
            //回显数据
            $("#updateForm").form("load",rows[0]);
            $("#update_dlg").dialog("open");
        }
    </script>
</head>
<body>
<table id="dg"></table>
<div id="tb">
    <div>
        <a href="javascript:openAddTab()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openUpdateTab()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteBlogType()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
</div>
<!--添加窗口-->
<div id="add_dlg" class="easyui-dialog" title="添加" style="width:350px;height:150px;padding:10px"
     data-options="buttons:'#add_tb',closed:true,modal: true">
    <form id="addForm">
        <table>
            <tr>
                <td>类别名称:</td>
                <td><input type="text" id="addTypeName" name="typeName" class="easyui-validatebox"
                           data-options="required:true"/></td>
            </tr>
            <tr>
                <td>排序序号:</td>
                <td><input type="text" id="addOrderNum" name="orderNum" class="easyui-numberbox"
                           data-options="required:true"/>(从小到大排列)
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="add_tb">
    <a href="javascript:addBlogType()" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">确定</a>
</div>
<!--修改窗口-->
<div id="update_dlg" class="easyui-dialog" title="修改" style="width:350px;height:150px;padding:10px"
     data-options="buttons:'#update_tb',closed:true,modal: true">
    <form id="updateForm">
        <table>
            <tr>
                <input type="hidden" id="id" name="id"/>
                <td>类别名称:</td>
                <td><input type="text" id="typeName" name="typeName" class="easyui-validatebox"
                           data-options="required:true"/></td>
            </tr>
            <tr>
                <td>排序序号:</td>
                <td><input type="text" id="orderNum" name="orderNum" class="easyui-numberbox"
                           data-options="required:true"/>(从小到大排列)
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="update_tb">
    <a href="javascript:updateBlogType()" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">确定</a>
</div>
</body>
</html>