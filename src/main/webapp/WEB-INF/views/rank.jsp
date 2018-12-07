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
    <script src="../../static/js/echarts.common.min.js"></script>
    <script src="../../static/js/active.js"></script>
    <title>排行榜</title>
<script>
    $(document).ready(function () {
        $("#rank").addClass("active");
        $("#week").hide();
        $("#month").hide();

    });
</script>

</head>

<body>
<div class="wrap">
    <!--页眉-->
    <ul class="bang_tab">
        <li id="a" ><a href="javascript:;">周榜</a></li>
        <li id = "b" class="active"><a href="javascript:;">日榜</a></li>
        <li id="c" ><a href="javascript:;">月榜</a></li>
    </ul>
<div style="height:44px" ></div>
    <span id="day">

    <ul class="mycenter_list" >
        <c:forEach items="${orderWeightDay}" var="data" varStatus="status">
            <li id="vx">
                <a >
                    <div>
                        <i class="layui-icon">&#xe66f;</i>
                    </div>
                    <p>第&nbsp;&nbsp;&nbsp;<span style="color: #c52600; font-size: large">${status.count}</span>&nbsp;&nbsp;&nbsp;名
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span style="color: #01AAED; font-size: large">${data.todayName}</span>
                        &nbsp;&nbsp;&nbsp;&nbsp;   &nbsp;&nbsp;&nbsp;&nbsp;
                        <span style="color: #c52600; font-size: large">${data.data}</span>
                     </p>
                </a>
            </li>
        </c:forEach>
        <li id="vx"><a ></a></li>
    </ul>


    </span>
    <span id="week">
    <ul class="mycenter_list" >
        <c:forEach items="${orderWeightWeek}" var="data" varStatus="status">
            <li id="vx">
                <a >
                    <div>
                        <i class="layui-icon">&#xe66f;</i>
                    </div>
                    <p>第&nbsp;&nbsp;&nbsp;<span style="color: #c52600; font-size: large">${status.count}</span>&nbsp;&nbsp;&nbsp;名
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span style="color: #01AAED; font-size: large">${data.todayName}</span>
                        &nbsp;&nbsp;&nbsp;&nbsp;   &nbsp;&nbsp;&nbsp;&nbsp;
                        <span style="color: #c52600; font-size: large">${data.data}</span>
                     </p>
                </a>
            </li>
        </c:forEach>
        <li id="vx"><a ></a></li>
    </ul>
    </span>
    <span id="month">
    <ul class="mycenter_list" >
        <c:forEach items="${orderWeightMonth}" var="data" varStatus="status">
            <li id="vx">
                <a >
                    <div>
                        <i class="layui-icon">&#xe66f;</i>
                    </div>
                    <p>第&nbsp;&nbsp;&nbsp;<span style="color: #c52600; font-size: large">${status.count}</span>&nbsp;&nbsp;&nbsp;名
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span style="color: #01AAED; font-size: large">${data.todayName}</span>
                        &nbsp;&nbsp;&nbsp;&nbsp;   &nbsp;&nbsp;&nbsp;&nbsp;
                        <span style="color: #c52600; font-size: large">${data.data}</span>
                     </p>
                </a>
            </li>
        </c:forEach>
        <li id="vx"><a ></a></li>
    </ul>
    </span>


    <!--导航栏-->
    <%@include file="/WEB-INF/views/include/footer.jsp" %>
</div>

</body>
<script>
    $("#a").click(function () {
        $("#day").hide();
        $("#month").hide();
        $("#week").show();
    });
    $("#b").click(function () {
        $("#week").hide();
        $("#month").hide();
        $("#day").show();
    });
    $("#c").click(function () {
        $("#day").hide();
        $("#week").hide();
        $("#month").show();
    });
</script>

</html>