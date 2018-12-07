<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <link rel="stylesheet" href="../../static/style/base.css">
    <link rel="stylesheet" href="../../static/style/style.css">
    <script src="../../static/js/jquery-3.3.1.min.js"></script>

    <title>人员管理</title>


    <script>
        $(document).ready(function () {
            $("#admin").addClass("active")
        });

    </script>
</head>
<body>
<div class="wrap" >

        <ul class="mycenter_list" >

    <c:forEach items="${nameListYes}" var="data" varStatus="status">
        <li id="vx">
            <a >
                <div>
                    <i class="layui-icon">&#xe67a;</i>
                </div>
                <p>${data.name}</p>
            </a>
        </li>
    </c:forEach>
        </ul>

    <ul class="mycenter_list" >

        <c:forEach items="${nameListNo}" var="data" varStatus="status">
            <li id="vx">
                <a >
                    <div>
                        <i class="layui-icon">&#xe67b;</i>
                    </div>
                    <p style="color: red">${data.name}</p>
                </a>
            </li>
        </c:forEach>
        <li id="vx"><a ></a></li>
    </ul>





    <!--导航栏-->
    <%@include file="/WEB-INF/views/include/footer.jsp" %>
</div>

</body>

</html>