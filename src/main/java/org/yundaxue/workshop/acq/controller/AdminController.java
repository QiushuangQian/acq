package org.yundaxue.workshop.acq.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yundaxue.workshop.acq.config.Mytoken;
import org.yundaxue.workshop.acq.model.User;
import org.yundaxue.workshop.acq.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 耿志强 on 2019/3/17.
 */

@Controller
public class AdminController {

    @GetMapping("/admin/login")
    public String loginGet(Model model) {
        return "login";
    }

    /*
      *@Description: 登录
      *@param: user
      *@param: model
      *@param: httpSession
      *@return: java.lang.String
      *@Author: GengZhiQiang
      *@date: 2019/1/22
     * */
    @PostMapping("/admin/login")
    public String loginPost(HttpServletRequest request, User user, Model model, HttpSession httpSession, final RedirectAttributes redirectAttrs) {
        Mytoken token = new Mytoken(user.getEmail(), user.getPassword(),"normal");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        }catch (Exception e){
            model.addAttribute("error", "用户名或密码错误，请重新登录！");
            return "login";
        }
        return "redirect:index";
    }

    @GetMapping(value = "/admin/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return "redirect:login";
    }

    /**
     * 注册
     */
    //用户注册
    @GetMapping(value = "/register")
    public String register(){
        return "register";
    }


}
