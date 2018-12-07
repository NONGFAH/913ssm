<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="../../static/layui-v2.4.5/css/layui.css">

<div class="footer">
    <ul>
        <%--class="active"将当前选中的图标加红--%>

        <%--todo 字符串处理打卡页面--%>
        <li id="index"><a href="/index">
            <p><i class="icons">&#xe6aa;</i></p>
            <h4>打卡导入</h4></a>
        </li>

        <%--todo 微信设计思路，人员增删查，及该人员的当日信息更改统一做在这个页面，添加人员的点击事件
        todo 人员列表按照字母表顺序排行+添加侧边栏26个字母跳转
        --%>
        <li id="admin"><a href="/admin">
            <p><i class="layui-icon">&#xe770;</i></p>
            <h4>人员管理</h4></a>
        </li>

        <%--todo 下级所有人员排行--%>
        <li id="rank"><a href="/rank">
            <p><i class="icons">&#xe68f;</i></p>
            <h4>排行榜</h4></a>
        </li>

        <%--todo 登陆注册，上级人员信息，联系我 （账号到期时间）--%>
        <li id="home"><a href="/home">
            <p><i class="layui-icon">&#xe66f;</i></p>
            <h4>我</h4></a>
        </li>

    </ul>
</div>