<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2019/7/25
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>黑马商城后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
</head>
<body>
    <div id="cccc" class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',title:'北',split:true" style="height:230px;">
            <%--图片logo--%>
            <img src="${pageContext.request.contextPath}/image/itheima.bmp" style="height:190px;width:100%" />
        </div>
        <div data-options="region:'west',title:'西',split:true" style="width:350px;">
            <%--折叠窗--%>
            <div class="easyui-accordion" data-options="fit:true,border:0">
                <div title="java">

                    <!-- 树 -->
                    <ul id="tttt" class="easyui-tree">
                        <li>
                            <span>商品分类管理</span>
                            <ul>
                                <li>
                                    <span><a href="#" onclick="addTabs('商品类别展示','category_list.jsp')">商品分类列表</a></span>
                                </li>
                            </ul>
                        </li>

                        <li>
                            <span>商品信息管理</span>
                            <ul>
                                <li>
                                    <span><a href="#" onclick="addTabs('商品信息展示','product_list.jsp')">商品信息列表</a></span>
                                </li>
                            </ul>
                        </li>

                    </ul>
                </div>
                <div title="UI">222</div>
                <div title="PHP">333</div>
            </div>
        </div>
        <div data-options="region:'center'">
            <!-- 选项卡 -->
            <div id="tabsss" class="easyui-tabs" data-options="fit:true,border:0">
                <div title="欢迎页面">
                    <h2>在此页面对商品进行后台管理</h2>
                </div>
            </div>
        </div>
    </div>

    <script>
        function addTabs(info,ym)
        {
            // 先判断选项卡中是否有指定的选项卡
            var flg=$("#tabsss").tabs("exists",info);
            if(flg)
            {
                // 有---跳过去
                $("#tabsss").tabs("select",info);
            }else
            {
                // 没有--创建
                $("#tabsss").tabs("add",
                    {
                        title:info,
                        closable:true,
                        /* 引入页面内容 */
                        href:ym
                    })
            }

        }
    </script>


</body>
</html>
