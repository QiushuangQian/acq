package org.yundaxue.workshop.acq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yundaxue.workshop.acq.model.User;
import org.yundaxue.workshop.acq.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2019/3/23.
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    //用于打开register页面
    @RequestMapping(value = "/register")
    public String register(ModelMap model, HttpServletRequest request, HttpServletResponse response)throws Exception{
        return "/register";
    }
    //用于打开changePsw页面
    @RequestMapping(value = "/changePsw")
    public String changePsw(ModelMap model, HttpServletRequest request, HttpServletResponse response)throws Exception{
        return "/changePsw";
    }

    //注册时发送验证码，进行数据插入
    @RequestMapping(value = "/register/sendMail")
    @ResponseBody
    public boolean sendMail(User user, ModelMap model, HttpServletRequest request, HttpServletResponse response)throws Exception{
        return userService.register(user);
    }
    //修改密码时发送验证码，进行数据的更新
    @RequestMapping(value = "/changePsw/sendMailChangePsw")
    @ResponseBody
    public boolean sendMailChangePsw(User user, ModelMap model, HttpServletRequest request, HttpServletResponse response)throws Exception{
        return userService.sendMailChangePsw(user);
    }
    //注册时，激活用户，检验验证码
    @RequestMapping(value = "/activation")
    @ResponseBody
    public Map<String,Object> activation(String idCode, ModelMap model, HttpServletRequest request, HttpServletResponse response)throws Exception{
        boolean b = userService.activeUser(idCode);
        Map<String,Object> result = new HashMap<>();
        if (b){
            result.put("idCode",1);
        }else {
            result.put("idCode",0);
            result.put("msg","激活失败");
        }
        return result;
    }
}
