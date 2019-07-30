<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2019/7/25
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>练习</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
</head>
<body>
    <%--静态渲染--%>
    <%--<div class="easyui-dialog" data-options="width:300,height:200">1111111</div>--%>
    <%--动态渲染--%>
    <div id="d1">2222</div>
    <script>
        $("#d1").dialog({
            width:300,
            height:200
        });
    </script>
</body>
</html>
