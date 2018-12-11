<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>资源管理</title>
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
        Date.prototype.Format = function (fmt) {
            var o = {
                "M+": this.getMonth() + 1, //月份
                "d+": this.getDate(), //日
                "h+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
            };
            if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        };


        //定义列
        var columns = [[
            {field: 'id', title: '编号', checkbox: true},
            {
                field: 'blogTitle', title: '资源标题', width: 15, align: 'center',
                formatter: function (value, row, index) {
                    return "<a target='_blank' href='${pageContext.request.contextPath}/blog/" + row.id + ".html'>" + value + "</a>"
                }
            },
            {field: 'userIp', title: '用户ip', align: 'center', width: 3},
            {field: 'content', title: '评论内容', align: 'center', width: 40},
            {
                field: 'commentDate',
                title: '评论日期',
                align: 'center',
                width: 8,
                formatter: function (value, row, index) {
                    return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
                }
            }

        ]];


        $(function () {
            $('#dg').datagrid({
                url: '${pageContext.request.contextPath}/admin/comment/findAllWithBlogNameAndBlogId',
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
                            "url": '${pageContext.request.contextPath}/admin/comment/delete',
                            "data": params,
                            "type": "POST",
                            "dataType": "json",
                            "success": function (data) {
                                $.messager.alert("系统提示", "删除成功!");
                                $("#dg").datagrid("reload");
                            },
                            "error": function () {
                                $.messager.alert("系统提示", "删除失败!");
                            }
                        });
                    }
                })
            }
        }



    </script>
</head>
<body>
<table id="dg"></table>
<div id="tb">
    <div>
        <a href="javascript:deleteBlogType()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
</div>
</body>
</html>