<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2019/7/26
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <!-- 要开始展示类别信息了....(和数据库进行一步交互) -->
    <table id="category_list"></table>
    <div id="category_dialog"></div>
    <script>
        $('#category_list').datagrid({
            url:'${pageContext.request.contextPath}/category?method=findCategory',
            columns:    // 根据返回来的json数据进行匹配
            [[
                    /*
			      		title:页面标题
			      		field:会和返回json的key做对比 对比成功 将value值放在分类编号等这一列
			      	*/
                {field:'cid',title:'分类编号',width:300},
                {field:'cname',title:'分类名称',width:100},
                {field:'xxxxx',title:'操作',width:100,formatter: function(value,row,index)

                                            {
                                                /*
												        		field=value
												        		row:一行的数据
												        		index:每行的角标
												        	*/
                                                return "<a href=# onclick=del('"+row.cid+"')>删除</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=# onclick=update('"+row.cid+"','"+row.cname+"')>修改</a>";

                                                /*
                                                 js:    如果传递的是数值型，可加或者可不加单引号
                                                        如果传递的是非数值型，必须加上单引号，如果不加，abc会被当成是一个变量，而非一个值
                                                */
                                            }

                }
            ]],
            /* 占满父容器 */
            fit:true,
            /* 分页工具 */
            pagination:true,
            pageList:[5,10,15,20,25,30],
            pageSize:5,
            /* 工具栏 */
            toolbar:
                [
                    {
                        iconCls: 'icon-add',
                        handler: function()
                        {
                            // 把一个html渲染成dialog
                            $("#category_dialog").dialog(
                                {
                                    width:300,
                                    height:200,
                                    title:"添加",
                                    href:"category_add.jsp"
                                }
                            );
                        }
                    },'-',
                    {
                        iconCls: 'icon-help',
                        handler: function()
                        {
                            alert('帮助按钮')
                        }
                    }]
        });
    </script>

    <script>
        function del(cid)
        {
            // ajax异步交互
            var url="${pageContext.request.contextPath}/category";
            var params="method=deleteBycid&cid="+cid;
            $.post(url,params,function(d)
            {
                if("ok"==d)
                {
                    // 删除成功 右下角做提示
                    $.messager.show({
                        title:'分类删除消息',
                        msg:'恭喜你,删除分类成功!!!',
                        timeout:5000,
                        showType:'fade'
                    });

                    // 刷新表格的最新数据
                    $("#category_list").datagrid("reload",true);
                }
            })
        }

        function update(cid,cname)
        {
            $("#category_dialog").dialog(
                {
                    width:300,
                    height:200,
                    href:"category_update.jsp?cid="+cid+"&cname="+cname
                });
        }


    </script>

</body>
</html>
