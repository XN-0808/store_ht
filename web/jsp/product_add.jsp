<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2019/7/27
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form id="product_add" method="post" action="${pageContext.request.contextPath}/addServlet" style="padding: 10px" enctype="multipart/form-data">
        <table cellpadding="5">

            <tr>
                <td>商品名称:</td>
                <td><input class="easyui-textbox" type="text" name="pname" data-options="required:true"></input></td>
            </tr>
            <tr>
                <td>市场价格:</td>

                <td><input class="easyui-textbox" type="text" name="market_price" data-options="required:true"></input></td>
            </tr>
            <tr>
                <td>商城价格:</td>
                <td><input class="easyui-textbox" type="text" name="shop_price" data-options="required:true"></input></td>
            </tr>
            <tr>
                <td>上传:</td>
                <td><input class="easyui-filebox" name="upload" style="width:220px" data-options="buttonIcon:'icon-add',buttonText:'上传'"></input></td>
            </tr>
            <tr>
                <td>是否热门:</td>
                <td>
                    <select class="easyui-combobox" name="is_hot" data-options="panelHeight:'auto'">
                        <option value="0">热门</option>
                        <option value="1">不热门</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>所属分类:</td>
                <td><input id="ccccc" name="cid" value="---请选择---"></td>
                <script>
                    $('#ccccc').combobox(
                        {
                            url:'${pageContext.request.contextPath}/product?method=findCategory',   //servlet的地址  返回的是json数据  		{cid:1,cname:手机数码}
                            valueField:'cid',    //  根据返回json的key  取value值 放在<option value=?></option>
                            textField:'cname',    //   根据返回json的key  取value值 放在<option>?<option>
                            panelHeight:'auto'	// <option value="1">手机数码<option>
                        });
                </script>
            </tr>
            <tr>
                <td>商品描述:</td>
                <td><input class="easyui-textbox" name="pdesc" data-options="multiline:true" style="height:60px"></input></td>
            </tr>

        </table>
    </form>
    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
    </div>
    <script>
        function submitForm(){
            $('#product_add').form('submit',{
                success:function (d) {
                    {
                        if ("ok" == d){
                            // 隐藏对话框
                            $("#product_dialog").dialog("close",true);
                            // 右下角做提示
                            $.messager.show({
                                title:'添加商品消息',
                                msg:'恭喜你,添加成功',
                                timeout:5000,
                                showType:'slide'
                            });
                            // 刷新表格数据
                            $("#product_list").datagrid("reload",true);
                        }
                    }
                }
            });
        }
        function clearForm(){
            $('#product_add').form('clear');
        }
    </script>
</body>
</html>
