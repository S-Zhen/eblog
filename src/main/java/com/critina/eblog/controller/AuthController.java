package com.critina.eblog.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.critina.eblog.common.lang.Result;
import com.critina.eblog.entity.User;
import com.critina.eblog.util.ValidationUtil;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @program: eblog
 * @description:
 * @author: sunzhen
 * @create: 2020-12-10 16:33
 **/
@Controller
public class AuthController extends BaseController {

    //存放验证码的session的key的常量名
    private static final String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY";

    @Autowired
    Producer producer;

    /**
     * @Description: 跳转到登录页面
     * @Param: []
     * @return: java.lang.String
     * @Author: sunzhen
     * @Date: 2020/12/11
     */
    @GetMapping("/login")
    public String login() {
        return "/auth/login";
    }

    @ResponseBody
    @PostMapping("/login")
    public Result doLogin(String email, String password) {

        //检验邮箱和密码
        if(StrUtil.isBlank(email) || StrUtil.isBlank(password)) {
            return Result.fail("邮箱或密码不能为空");
        }

        //使用shiro框架获得token(作为参数传入的邮箱和密码,其中密码是被md5加密的)
        UsernamePasswordToken token = new UsernamePasswordToken(email, SecureUtil.md5(password));
        try {
            //传入token,基于shiro框架进行登录(登录细节的代码在AccountRealm.java中)
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            if (e instanceof UnknownAccountException) {
                return Result.fail("用户不存在");
            } else if (e instanceof LockedAccountException) {
                return Result.fail("用户被禁用");
            } else if (e instanceof IncorrectCredentialsException) {
                return Result.fail("密码错误");
            } else {
                return Result.fail("用户认证失败");
            }
        }

        //登录成功并跳转到首页
        return Result.success().action("/");
    }

    /**
     * @Description: 跳转到注册页面
     * @Param: []
     * @return: java.lang.String
     * @Author: sunzhen
     * @Date: 2020/12/11
     */
    @GetMapping("/register")
    public String register() {
        return "/auth/reg";
    }

    @ResponseBody
    @PostMapping("/register")
    public Result doRegister(User user, String repass, String vercode) {

        //使用HibernateValidator进行检验user对象中字段是否合法并返回验证结果
        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(user);

        //检验用户注册的user是否存在错误,通过user中字段上的注解要求进行检验
        if (validResult.hasErrors()) {
            return Result.fail(validResult.getErrors());
        }

        //比对两次密码
        if (!user.getPassword().equals(repass)) {
            return Result.fail("两次输入密码不相同");
        }

        //获得session中的验证码
        String capthca = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        System.out.println(capthca);

        //比对验证码
        if (vercode == null || !vercode.equalsIgnoreCase(capthca)) {
            return Result.fail("验证码输入不正确");
        }

        // 完成注册
        Result result = userService.register(user);
        return result.action("/login");
    }

    @GetMapping("/capthca.jpg")
    public void kaptcha(HttpServletResponse resp) throws IOException {

        // 验证码
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);
        request.getSession().setAttribute(KAPTCHA_SESSION_KEY, text);

        resp.setHeader("Cache-Control", "no-store, no-cache");
        resp.setContentType("image/jpeg");
        ServletOutputStream outputStream = resp.getOutputStream();
        ImageIO.write(image, "jpg", outputStream);
    }


}
