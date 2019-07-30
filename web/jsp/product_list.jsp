<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2019/7/26
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <%--和服务器获取商品数据展示--%>
        <table id="product_list"></table>
        <!-- 商品dialog -->
        <div id="product_dialog"></div>
        <script>
            $('#product_list').datagrid(
        {
            url:'${pageContext.request.contextPath}/product?method=findProduct',   //servlet的地址，返回值是json数据
            columns:[[
                {field:'pimage',title:'商品图片',width:100,formatter: function(value,row,index)
                                        {
                                            //field=value (products/1/c_0001.jpg) /stores_ht/products/1/c_0001.jpg
                                            // row:每行的数据
                                            return "<img width='50' src=${pageContext.request.contextPath}/"+value+">";
                                        }
                },
                {field:'pid',title:'商品编号',width:100},
                {field:'pname',title:'商品名称',width:100},
                {field:'shop_price',title:'商品价格',width:100},
                {field:'pdate',title:'商品日期',width:100},
                {field:'is_hot',title:'是否热门',width:100,formatter: function(value,row,index)
                                        {
                                            return value==0?"热门":"不热门";
                                        }
                },
                {field:'category',title:'所属分类',width:100,formatter: function(value,row,index)
                                        {
                                            // 1.4.2有问题 会多给一行数据 一行的数据都是null
                                            // 把第一行过滤掉
                                            if(value!=null)
                                            {
                                                return value.cname;
                                            }
                                        }
                },
            ]],
            pagination:true,
            pageList:[5,10,15,20,25,30],
            pageSize:5,
            fit:true,
            /* 工具栏 */
            toolbar: [{
                iconCls: 'icon-add',
                handler: function()
                {
                    // 只要点击,就将普通的html标签渲染成dialog组件
                    $("#product_dialog").dialog
                    (
                        {
                            width:400,
                            height:400,
                            title:"商品添加",
                            href:"product_add.jsp"
                        }
                    );
                }
            },'-',{
                iconCls: 'icon-help',
                handler: function(){alert('帮助按钮')}
            }]
        });
        </script>
</body>
</html>
