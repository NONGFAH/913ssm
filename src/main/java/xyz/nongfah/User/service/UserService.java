package xyz.nongfah.User.service;

import base.obiects.WebUser;
import base.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import xyz.nongfah.User.dao.UserMapper;
import xyz.nongfah.User.model.User;
import base.utils.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static base.utils.CookieUtil.removeCookie;
import static base.utils.CookieUtil.setLoginUser;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    @Transactional//注册
    public void logon(User user) throws Exception {
        try {
            userMapper.logon(user);
        } catch (Exception e) {
            throw new Exception("UserService logon", e);
        }
    }

    @Transactional
    public boolean isNameExist(String name) {//用户名不存在返回true
        return userMapper.isNameExist(name) ==  0 ;
    }


    @Transactional
    public boolean isState(String name) throws Exception {//账户是否可用
        try {
            return !(userMapper.State(name) == 0);
        } catch (Exception e) {
            throw new Exception("UserService isState", e);
        }
    }

    @Transactional
    public boolean unableLogin(User user) throws Exception {//核对账号密码
        try {
            String passMd5 = MD5.getMD5(user.getPassword());
            user.setPassword(passMd5);
            return userMapper.unableLogin(user) !=  0 ;
        } catch (Exception e) {
            throw new Exception("UserService selectByName", e);
        }
    }



    public void loginCookie(HttpServletResponse response, User user) throws Exception {//将登陆信息存入cookies
        try {
            if (user == null || StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
                return;
            }
            WebUser webUser = new WebUser();
            webUser.setUsername(user.getUserName());
            webUser.setLoginStatus(1);
            setLoginUser(response, webUser);
        } catch (Exception e) {
            throw new Exception("UserService loginIn", e);
        }
    }

    public User getLoginUser(HttpServletRequest request) throws Exception {//从cookies中读取用户信息

        try {
            WebUser webUser = CookieUtil.getLoginUser(request);
            User user = new User();
            user.setUserName(webUser.getUsername());
            return user;
        } catch (Exception e) {
            throw new Exception("UserService getLoginUser", e);
        }

    }


    public void logout(HttpServletResponse response) {//删除cookies中用户信息
        removeCookie(response);
    }


}
