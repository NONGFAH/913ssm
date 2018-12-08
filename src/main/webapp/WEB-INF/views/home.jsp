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
    <link rel="stylesheet" href="../../static/layui-v2.4.5/css/layui.css">
    <script src="../../static/js/jquery-3.3.1.min.js"></script>

    <title>我</title>
    <script>
        $(document).ready(function () {


            $("#home").addClass("active");
                $("#vx").click(function () {
                    $(".zzc").fadeIn()
                });
                $(".ewm i").click(function(){
                    $(".zzc").fadeOut()
                })

            })
    </script>
</head>

<body>

<div class="wrap">
    <!--页眉-->
    <div class="mycenter_top">
        <div class="baseinfo">
            <div class="myimg">
                <a href="javascript:;">
                    <img src=".././../static/image/38c3e86452ac1471.jpg"/>
                    <h3><span class="nc">用户名：${userName}</span></h3>
                    <div class="dj">一级</div>
                </a>
            </div>
            <div class="qd"><i class="icons">&#xe60e;</i>今日排名:1</div>
        </div>
    </div>

    <ul class="mycenter_list" >
        <li id="vx"><a ><div><i class="layui-icon"> &#xe677;</i></div><p>联系我</p></a></li>
    </ul>
    <ul class="mycenter_list" >
        <li><a href="doLogout"><div><i class="layui-icon">&#x1007;</i></div><p>注销</p></a></li>
    </ul>
    <!--导航栏-->
    <%@include file="/WEB-INF/views/include/footer.jsp" %>
</div>
<div class="zzc">
    <div class="ewm">
        <i class="icons">&#xe611;</i>
        <img src="../../static/image/vx.png"/>
    </div>
</div>

</body>


</html>