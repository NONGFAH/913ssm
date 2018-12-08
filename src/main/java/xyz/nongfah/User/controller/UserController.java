package xyz.nongfah.User.controller;

import base.utils.MD5;
import base.utils.RandomValidateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.nongfah.User.model.User;
import xyz.nongfah.User.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 跳转到登陆
     *
     * @return  登录页面
     */
    @RequestMapping(value = "login")
    public String forwardLogin() {
        return "login";
    }

    /**
     * 跳转到注册
     *
     * @return  注册页面
     */
    @RequestMapping(value = "logon")
    public String forwardLogon() {
        return "logon";
    }

    /**
     * 注册
     *
     * @param response 请求头操作cookie
     * @param session  session用于验证码验证
     * @param user     用户信息
     * @param code     验证码
     * @return  注册结果
     * @throws Exception    异常
     */
    @RequestMapping(value = "doLogon", method = RequestMethod.POST)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public Map doLogon(HttpServletResponse response, HttpSession session, User user, String code) throws Exception {
        Map model = new HashMap();
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        try {
            if (userService.isNameExist(user.getUserName())) {
                //用户名可用
                if (random.equals(code)) {
                    //验证码正确
                    user.setPassword(MD5.getMD5(user.getPassword()));//密码加密
                    userService.logon(user);
                    logger.info("用户{}注册成功", user.getUserName());
                    model.put("respCode", "0000");//注册成功
                    userService.loginCookie(response, user);//存入cookies操作
                    return model;
                } else {
                    //验证码错误
                    model.put("respCode", "1002");
                    return model;
                }
            } else {
                //用户名不可用
                model.put("respCode", "1000");//注册失败
                return model;
            }
        } catch (Exception e) {
            logger.error("UserController logon",e);
            throw new Exception("UserController logon", e);
        }
    }

    /**
     * 登陆
     *
     * @param response
     * @param session
     * @param user
     * @param code
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public Map doLogin(HttpServletResponse response, HttpSession session, User user, String code) throws Exception {
        Map model = new HashMap();
        String userName = user.getUserName();
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        try {
            // 如果用户名不存在
            if (userService.isNameExist(userName)) {
                model.put("respCode", "1000");
                return model;
            }
            //验证码不正确
            if (!random.equals(code)) {
                model.put("respCode", "1002");
                return model;
            }
            // 如果用户状态为0
//            if (!userService.isState(user.getUsername())) {
//                model.put("respCode","1001");
//                return model;
//            }

            // 账号和密码正确
            if (userService.unableLogin(user)) {
                model.put("respCode", "0000");
                //存入cookies操作
                userService.loginCookie(response, user);
                return model;
            }
            // 前端显示密码不正确
            model.put("respCode", "1001");
            return model;
        } catch (Exception e) {
            throw new Exception("UserController login", e);
        }
    }


    /**
     * 刷新二维码
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注销
     *
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "doLogout")
    public String doLogout(HttpServletResponse response) throws Exception {
        try {
            userService.logout(response);
            return "login";
        } catch (Exception e) {
            throw new Exception("UserController logout", e);
        }
    }

}
