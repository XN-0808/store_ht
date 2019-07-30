<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2019/7/26
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form id="category_update"  action="${pageContext.request.contextPath}/category" method="post" style="padding:25px">
        <input type="hidden" name="method" value="updateCategory">
        <input type="hidden" name="cid" value="${param.cid}">
        <table cellpadding="5">
            <tr>
                <td>Name:</td>
                <td><input class="easyui-textbox" type="text" name="cname" value="${param.cname}" data-options="required:true"></input></td>
            </tr>
        </table>
    </form>
    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
    </div>

    <script>
        function submitForm(){
            $('#category_update').form('submit',
                {
                    success:function(d)
                    {
                        if("ok"==d)
                        {
                            // 隐藏dialog
                            $("#category_dialog").dialog("close",true);
                            // 右下角做提示
                            $.messager.show({
                                title:'修改消息',
                                msg:'恭喜你,分类修改成功',
                                timeout:5000,
                                showType:'slide'
                            });

                            // 刷新表格数据
                            $("#category_list").datagrid("reload",true);
                        }
                    }

                });
        }
        function clearForm(){
            $('#category_update').form('clear');
        }
    </script>
</body>
</html>
