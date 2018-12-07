package base.utils;

import base.obiects.WebUser;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Objects;


/**
 * Cookie工具类
 */
public class CookieUtil {
    /**
     * 默认Cookie过期时间（单位：秒）
     */
    public static final int MAX_AGE = -1;

    /**
     * 用户登陆信息Cookie名字
     */
    public static final String USER_INFO = "ui";

    private CookieUtil() {        throw new AssertionError("不要实例化工具类哦");    }

    /**
     * 向Cookie中写入用户信息
     * @param response
     * @param user
     */
    public static void setLoginUser(HttpServletResponse response, WebUser user) {
        if (null == response || null == user) {
            return;
        }
        String username = user.getUsername();
        try {
            username = URLEncoder.encode(user.getUsername(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder cookieValue = new StringBuilder();

        cookieValue.append(username).append("|").append(user.getLoginStatus());
        addCookie(response, USER_INFO, cookieValue.toString());

    }

    public static void addCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(MAX_AGE);
        response.addCookie(cookie);
    }

    public static WebUser getLoginUser(HttpServletRequest request) {
        WebUser webUser = new WebUser();
        if (null == request) {
            return webUser;
        }

        //从cookie里取出用户信息(两个字段)
        String value = getCookieValue(USER_INFO, request);
        if (StringUtils.isEmpty(value)) {
            return webUser;
        }

        String[] array = value.split("\\|");
        try {
            webUser.setUsername(URLDecoder.decode(array[0], "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            webUser.setUsername(array[0]);
        }
        webUser.setLoginStatus(Integer.parseInt(array[1]));
        return webUser;
    }

    public static String getCookieValue(String name, HttpServletRequest request) {
        if (null == request || StringUtils.isEmpty(name)) {
            return null;
        }

        Cookie[] cookies = request.getCookies();
        if (null == cookies || 0 == cookies.length) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (Objects.equals(cookie.getName(), name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 删除Cookie
     * @param response HttpServletResponse
     */
    public static void removeCookie(HttpServletResponse response) {
        if (null == response) {
            return;
        }
        Cookie cookie = new Cookie(USER_INFO, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
    public static String getName(HttpServletRequest request) throws Exception {
        try {
            if (null != request) {
                return getLoginUser(request).getUsername();
            }
            return null;
        } catch (Exception e) {
            throw new Exception("CookieUtil getName",e);
        }
    }

}