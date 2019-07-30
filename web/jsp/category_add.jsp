<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2019/7/26
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form id="category_form"  action="${pageContext.request.contextPath}/category" method="post" style="padding:25px">
        <input type="hidden" name="method" value="saveCategory">
        <table cellpadding="5">
            <tr>
                <td>Name:</td>
                <td><input class="easyui-textbox" type="text" name="cname" data-options="required:true"></input></td>
            </tr>
        </table>
    </form>
    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
    </div>

    <script>
        function submitForm(){
            $('#category_form').form('submit',
                {
                    success:function(d)
                    {
                        // 判断
                        if("ok"==d)
                        {
                            // 隐藏对话框
                            $("#category_dialog").dialog("close",true);
                            // 右下角做提示
                            $.messager.show({
                                title:'添加分类消息',
                                msg:'恭喜你,添加成功',
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
            $('#category_form').form('clear');
        }
    </script>
</body>
</html>
