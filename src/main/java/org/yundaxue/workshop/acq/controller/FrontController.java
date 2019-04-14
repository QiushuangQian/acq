package org.yundaxue.workshop.acq.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yundaxue.workshop.acq.ConfigClass;
import org.yundaxue.workshop.acq.exception.CatException;
import org.yundaxue.workshop.acq.model.Photo;
import org.yundaxue.workshop.acq.model.User;
import org.yundaxue.workshop.acq.service.PhotoService;
import org.yundaxue.workshop.acq.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;

/**
 * Created by 耿志强 on 2019/3/17.
 */
@Controller
public class FrontController {
	private int maxnum = 10;
	@Autowired
	PhotoService photoService;
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
	@RequestMapping(value = "/homepage/recycleBin")
	public String recycleBins(HttpServletRequest request)throws Exception{
		return "redirect:/recycleBin";
	}
		//打开上传页面——峰
	@RequestMapping(value = "/homepage/upload")
	public String upload(HttpServletRequest request)throws Exception{
		return "redirect:/upload";
	}
	//打开相册页面——峰
	@RequestMapping(value = "/homepage/albumPhotoShow")
	public String albumPhotoShow(HttpServletRequest request)throws Exception{
		return "redirect:/album/albumPhotoShow";
	}
	//打开默认照片列表页
	@RequestMapping(value = "/homepage/photo")
	public String photo()throws Exception{
		return "redirect:/photoDisplay";
	}

	@RequestMapping(value = "/homepage/createAlbum")
	public String createAlbum()throws Exception{
		return "redirect:/album/createAlbum";
	}

	@RequestMapping(value = "/homepage/setAlbum")
	public String setAlbum()throws Exception{
		return "redirect:/album/setAlbum";
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
			request.getSession().setAttribute("USER", loginedUser);
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
