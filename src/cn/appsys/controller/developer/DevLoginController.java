package cn.appsys.controller.developer;

import cn.appsys.pojo.DevUser;
import cn.appsys.service.developer.DevUserService;
import cn.appsys.tools.Constants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: bee
 * @Date: 2018/11/26 23:12
 * @Description:
 */
@Controller
@RequestMapping(value = "/dev")
public class DevLoginController {
    private Logger logger = Logger.getLogger(DevLoginController.class);
    @Resource
    private DevUserService devUserService;

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public String login() {
        logger.info("LoginController welcome AppInfoSystem developer==============");
        return "devlogin";
    }

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping(value = "/dologin",method = RequestMethod.POST)
    public String dologin(@RequestParam String devCode,
                          @RequestParam String devPassword,
                          HttpSession session,
                          HttpServletRequest request) {
        logger.info("dologin=======================");
        //调用service的方法验证用户
        DevUser user = null;
        user = devUserService.login(devCode, devPassword);
        System.out.println("============="+user);
        if (null != user) {
            System.out.println("============"+user.getDevCode()+"----"+user.getDevPassword());
            //放入session
            session.setAttribute(Constants.DEV_USER_SESSION, user);
            //页面跳转到主页面(main.jsp)
            return "redirect:/dev/flatform/main";

        } else {
            //登录失败，停留在devlogin.jsp页面，带出一些信息
            request.setAttribute("error", "用户名或密码不正确");
            return "devlogin";
        }
    }

    @RequestMapping(value = "/flatform/main")
    public String main(HttpSession session) {
        if (session.getAttribute(Constants.DEV_USER_SESSION) == null) {
            return "redirect:/dev/login";
        }
        return "developer/main";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        //清除session
        session.removeAttribute(Constants.DEV_USER_SESSION);
        return "devlogin";
    }


}
