package org.yundaxue.workshop.acq.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yundaxue.workshop.acq.config.Mytoken;
import org.yundaxue.workshop.acq.exception.CatException;
import org.yundaxue.workshop.acq.model.Album;
import org.yundaxue.workshop.acq.model.AlbumPhoto;
import org.yundaxue.workshop.acq.model.User;
import org.yundaxue.workshop.acq.service.AlbumPhotoService;
import org.yundaxue.workshop.acq.service.AlbumService;
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
@RequestMapping(value = "/user/login")
	public String show(ModelMap model,HttpServletResponse request,HttpServletResponse response)throws Exception{
	return "/login";
}
@RequestMapping(value = "/user/doLogin")
	@ResponseBody
	public Map<String,Object> doLogin(User user,ModelMap model,HttpServletResponse request,HttpServletResponse response)throws Exception{
	//int c
	Map<String, Object> result = new HashMap<String, Object>();

	int code =0;
	String msg = null;
	boolean success = false;

	try {
		/*success = userService.login(user);*/

		Mytoken token= new Mytoken(user.getEmail(), user.getPassword(), "user");
		SecurityUtils.getSubject().login(token);
		/*if (success) {
			code  = 0;
			msg = "登录成功";
		}*/
	} /*catch (CatException e) {
		code = e.getCode();
		msg= e.getMsg();

	}*/ catch (Exception e) {
		e.printStackTrace();
	}


	result.put("code", code);
	result.put("msg", msg);
	return result ;
}
}
