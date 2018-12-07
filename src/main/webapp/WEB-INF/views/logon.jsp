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
    <script src="../../static/js/active.js"></script>

    <title>注册</title>

    <script>
        $(document).ready(function () {
            $("#imgVerify").attr("src", "/getVerify?" + Math.random());

            function newCode() {
                var src1 = document.getElementById('imgVerify');
                src1.src = "/getVerify?" + Math.random();
            }

            //获取验证码
            $("#newCode").click(function () {
                $("#home").removeClass("hqyzm");
                $("#home").addClass("hqyzming");
                newCode();
                $("#home").addClass("hqyzm");

            });

            $("#submit").click(function () {
                var nameTest = /^[\u4e00-\u9fa5]{0,5}$/;
                var passTest = /^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?![,\.#%'\+\*\-:;^_`]+$)[,\.#%'\+\*\-:;^_`0-9A-Za-z]{8,20}$/;
                var codeTest = /^[A-Za-z]{0,4}$/;
                var userName = $("#userName").val();
                var password = $("#password").val();
                var code = $("#code").val();
                if(userName === ""){alert("请输入用户名");return;}
                if(password === ""){alert("请输入密码");return;}
                if(code === ""){alert("请输入验证码");return;}
                if (nameTest.test(userName)) {
                    if (passTest.test(password)) {
                        if (codeTest.test(code)) {
                            code = code.toUpperCase();
                            $.ajax({
                                type: "POST",
                                url: "/doLogon",
                                dataType: "json",
                                data: {
                                    userName: userName,
                                    password: password,
                                    code: code,
                                },
                                success: function (data) {
                                    if (data.respCode === "0000") {
                                        alert("注册成功");
                                        window.location.replace('/');
                                    }
                                    if (data.respCode === "1000") {
                                        alert("用户名已存在");
                                    }
                                    if (data.respCode === "1002") {
                                        newCode();
                                        alert("验证码错误");
                                    }

                                }
                            });
                        }
                        else {
                            alert("验证码格式错误");
                        }
                    } else {
                        alert("密码不符合要求 " + '\n' +
                            "必须包含数字字母字符任意两项" + '\n' + "长度大于8");
                    }
                } else {
                    alert("用户名中含有非法字符");
                }
            });
        })
    </script>
</head>
<body>
<div class="wrap">
    <form href="doLogn" method="post">
        <div class="login-form">
            <ul>
                <li id="a">
                    <label>用户名</label>
                    <div>
                        <input type="text" name="password" id="userName" placeholder="只允许中文,最多输入5个字符">
                    </div>
                </li>
                <li id="b">
                    <label>密码</label>
                    <div>
                        <input type="password" name="password" id="password" placeholder="最少8位,且必须有数字、字母、符号">
                    </div>
                </li>
                <li id="c">
                    <label>验证码</label>
                    <span > <img id="imgVerify" src="" alt="获取验证码失败" width="95" height="25"/>
					</span>
                    <div>
                        <input type="text" name="code" id="code">
                        <a class="hqyzm" id="newCode">获取验证码</a>
                    </div>
                </li>
            </ul>
        </div>
    </form>
    <div class="log">
        <a id="submit">注册</a>
    </div>
    <div class="log-more">
        <a class="btn-reg" href="/login">马上登陆&nbsp;&nbsp;</a>|
        <a class="btn-psw" href="">&nbsp;&nbsp;忘记密码</a>
    </div>
</div>
</body>
</html>
