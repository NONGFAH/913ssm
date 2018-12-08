<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <link rel="stylesheet" href="static/style/base.css">
    <link rel="stylesheet" href="static/style/style.css">
    <link rel="stylesheet" href="static/layui-v2.4.5/css/layui.css">
    <script src="static/js/jquery-3.3.1.min.js"></script>
    <script src="static/layui-v2.4.5/layui.js"></script>
    <script src="static/js/active.js"></script>
    <title>打卡</title>

    <script>
        $(document).ready(function () {
            function keyLogin() {
                if (event.keyCode == 13) {//回车键的键值为13
                    var toClick = document.getElementById("submit")
                    toClick.click();  //调用登录按钮的登录事件
                }
            }
            $("#index").addClass("active");
            /*start输入栏选中标识*/
            var input = $("input");
            input.focus(function () {
                $("#footer").hide();
            });
            input.blur(function () {
                $("#footer").show();
            });
            /*end输入栏选中标识*/

            // 函数中的参数为 要获取的cookie键的名称。
            function getCookie(c_name){
                if (document.cookie.length>0){
                    c_start=document.cookie.indexOf(c_name + "=");
                    if (c_start!=-1){
                        c_start=c_start + c_name.length+1;
                        c_end=document.cookie.indexOf(";",c_start);
                        if (c_end==-1){
                            c_end=document.cookie.length;
                        }
                        return unescape(document.cookie.substring(c_start,c_end));
                    }
                }
                return "";
            }
            var cookieVal = getCookie("ui");
            if (cookieVal == null || cookieVal == "") {
                window.location.replace('login');
            }
        });
    </script>
</head>

<body>

<input style="height: 80px;width: 100%" id="input">
<div class="log" style="width: 25%">
    <a id="resolve">解析</a>
</div>
<div class="login-form">
    <ul>
        <li id="h" class="touch">
            <label>我的瘦身日记</label>
            <div>
                <input type="text" name="name" id="name">
            </div>
        </li>
        <li id="f" class="touch">
            <label>今日体重</label>
            <div>
                <input type="text" name="weight" id="weight"/>
            </div>
        </li>
        <li id="a" class="touch">
            <label>早餐</label>
            <div>
                <input type="text" name="breakfast" id="breakfast">
            </div>
        </li>
        <li id="b" class="touch">
            <label>午餐</label>
            <div>
                <input type="text" name="lunch" id="lunch">
            </div>
        </li>
        <li id="i" class="touch">
            <label>水果</label>
            <div>
                <input type="text" name="fruits" id="fruits">
            </div>
        </li>
        <li id="c" class="touch">
            <label>晚餐</label>
            <div>
                <input type="text" name="dinner" id="dinner">
            </div>
        </li>
        <li id="d" class="touch">
            <label>排便次数</label>
            <div>
                <input type="text" name="defecation" id="defecation">
            </div>
        </li>
        <li id="e" class="touch">
            <label>饮水量</label>
            <div>
                <input type="text" name="water" id="water">
            </div>
        </li>
        <li id="g" class="touch">
            <label>备注</label>
            <div>
                <input type="text" name="tips" id="tips">
            </div>
        </li>
    </ul>
    <div class="log" style="width: 25%">
        <a id="submit">提交</a>
    </div>
</div>
<div style="height:30px;margin:10px auto;text-align:center;">
    <p style="height:30px;line-height:30px;color:#aaa">点击空白区域显示导航栏</p>
</div>
<!--导航栏-->
<span id="footer">
<%@include file="/WEB-INF/views/include/footer.jsp" %>
</span>
</body>
<script>
    $("#resolve").click(function () {
        var input = $("#input").val();
        var arr = new Array();
        arr = input.split('|');

        var name = arr[1].substring(7).trim();
        $("#name").val(name);
        var weight = arr[4].substring(5).trim();
        $("#weight").val(weight);
        var breakfast = arr[11].substring(3).trim();
        $("#breakfast").val(breakfast);
        var lunch = arr[12].substring(3).trim();
        $("#lunch").val(lunch);
        var fruits = arr[13].substring(3).trim();
        $("#fruits").val(fruits);
        var dinner = arr[14].substring(3).trim();
        $("#dinner").val(dinner);
        var defecation = arr[16].substring(5).trim();
        $("#defecation").val(defecation);
        var water = arr[15].substring(4).trim();
        $("#water").val(water);
        var tips = arr[10].substring(3).trim();
        $("#tips").val(tips);
        layer.msg('解析成功');
    });
    $("#submit").click(function () {
        var name = $("#name").val().trim();
        var weight = $("#weight").val().trim();
        var breakfast = $("#breakfast").val().trim();
        var lunch = $("#lunch").val().trim();
        var fruits = $("#fruits").val().trim();
        var dinner = $("#dinner").val().trim();
        var defecation = $("#defecation").val().trim();
        var water = $("#water").val().trim();
        var tips = $("#tips").val().trim();

        var nameTest = /^[\u4e00-\u9fa5]{0,5}$/;
        var weightTest = /^[0-9]+(.[0-9]{0,3})?$/;
        var Test = /^[0-9]{0,4}$/;

        if (name === "") {
            alert("我的瘦身日记为必填项");
            return;
        }
        if (weight === "") {
            alert("今日体重为必填项");
            return;
        }
        if (!nameTest.test(name)) {
            alert("我的瘦身日记输入格式不合法");
            return;
        }
        if (!weightTest.test(weight)) {
            alert("今日体重输入格式不合法");
            return;
        }
        if (!Test.test(defecation)) {
            alert("排便次数输入格式不合法");
            return;
        }if (!Test.test(water)) {
            alert("饮水量输入格式不合法");
            return;
        }

        $.ajax({
            type: "POST",
            url: "/doFlowChange",
            dataType: "json",
            data: {
                name: name,
                weight: weight,
                breakfast: breakfast,
                lunch: lunch,
                fruits: fruits,
                dinner: dinner,
                defecation: defecation,
                water: water,
                tips: tips
            },
            success: function (data) {
                if (data.respCode === 0) {
                    alert("打卡成功");
                    window.location.href = "/";
                }
                if (data.respCode === 1) {
                    alert("更新成功");
                }
                if (data.respCode === 2) {
                    alert("请先登陆");
                }
            }
        });



    });

</script>
</html>
<%--
2018 年11月9号
|我的瘦身日记:慧儿
|最初体重:79.17
|昨日体重:65.75
|今日体重:65.54
|敷包时间:2018月9月1号
|敷包天数:第三盒1天:
|今天减重数量:0.21
|总共减重数量:13.02公斤
|备注;备注
|昨日
|早餐:3颗蒸枣4个桂圆2个核桃，豆浆，鸡蛋
|午餐:米饭，西红柿炒鸡蛋
|水果:水果
|晚餐:晚安
|饮水量:800
|排便次数:1
--%>