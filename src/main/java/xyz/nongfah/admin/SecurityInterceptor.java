package xyz.nongfah.admin;

import base.obiects.WebUser;
import base.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.nongfah.User.dao.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class SecurityInterceptor implements HandlerInterceptor {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //这里可以根据cookies的用户来判断角色的权限，根据权限来转发不同的页面
        if (null != request) {
            WebUser webUser = CookieUtil.getLoginUser(request);
            if (userMapper.isNameExist(webUser.getUsername()) == 0) {//cookies中存储的用户名在系统中不存在
                request.getRequestDispatcher("/login").forward(request, response);//返回到提醒未登陆页面
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)  {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e)  {

    }
}