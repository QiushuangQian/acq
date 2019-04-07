package org.yundaxue.workshop.acq.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yundaxue.workshop.acq.exception.CatException;
import org.yundaxue.workshop.acq.model.User;
import org.yundaxue.workshop.acq.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 耿志强 on 2019/3/17.
 */
@Controller
public class FrontController {
	@Autowired
	UserService userService;


	//打开login页面
	@RequestMapping(value = "/user/login")
	public String show(ModelMap model,HttpServletRequest request,HttpServletResponse response)throws Exception{
	return "/login";
	}
	//打开homepage页面
	@RequestMapping(value = "/homepage")
	public String homePage(ModelMap model,HttpServletRequest request,HttpServletResponse response)throws Exception{
		return "/homepage";
	}
	//打开回收站页面
	@RequestMapping(value = "/recycleBin")
	public String recycleBins(ModelMap model,HttpServletRequest request,HttpServletResponse response)throws Exception{
		return "/recycleBin";
	}
	//打开上传页面——峰
	@RequestMapping(value = "/homepage/upload")
	public String upload(HttpServletRequest request)throws Exception{
		return "redirect:/upload";
	}
	//打开创建相册页面——峰
	@RequestMapping(value = "/homepage/createAlbum")
	public String createAlbum(HttpServletRequest request)throws Exception{
		return "redirect:/album/createAlbum";
	}



	//返回登录信息
	@RequestMapping(value = "/user/doLogin")
	@ResponseBody
	public Map<String,Object> doLogin(User user,ModelMap model,HttpServletRequest request,HttpServletResponse response)throws Exception{

	Map<String, Object> result = new HashMap<String, Object>();

	int code =0;
	String msg = null;
	User loginedUser = null;

	try {
		loginedUser = userService.login(user);
		if (loginedUser!= null) {
			code  = CatException.SUCCESS;
			msg = "登录成功";

			// 增加session存储
			request.getSession().setAttribute("USER", user);
		} else {
			code = CatException.UNKOWN_ERROR;
			msg = "未知错误";
		}
	} catch (CatException e) {
		code = e.getCode();
		msg= e.getMsg();

	} catch (Exception e) {
		e.printStackTrace();
	}


	result.put("code", code);
	result.put("msg", msg);
	return result ;
}
}
