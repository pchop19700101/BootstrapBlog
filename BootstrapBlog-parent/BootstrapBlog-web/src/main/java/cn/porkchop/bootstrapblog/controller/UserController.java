package cn.porkchop.bootstrapblog.controller;

import cn.porkchop.bootstrapblog.pojo.TUser;
import cn.porkchop.bootstrapblog.service.UserService;
import cn.porkchop.bootstrapblog.utils.CryptographyUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登陆
     *
     * @date 2018/3/8 20:10
     * @author porkchop
     */
    @RequestMapping("/logins")
    public String login(TUser user, HttpServletRequest request, String captcha) {
        HttpSession session = request.getSession();
        String captchaInSession = (String) session.getAttribute("captcha");
        if (StringUtils.isNotBlank(captcha) && captcha.equals(captchaInSession)) {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), CryptographyUtil.md5(user.getPassword(), "cn.porkchop.bootstrapblog"));
            try {
                // 登录验证
                subject.login(token);
                return "admin/index";
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("user", user);
                request.setAttribute("errorInfo", "用户名或者密码错误！");
                return "login";
            }
        } else {
            //非法验证码
            request.setAttribute("user", user);
            request.setAttribute("errorInfo", "验证码错误！");
            return "login";
        }
    }
}
