package org.yundaxue.workshop.acq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yundaxue.workshop.acq.model.Photo;
import org.yundaxue.workshop.acq.service.PhotoService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lenovo on 2019/3/30.
 */
@Controller
public class PhotoViewController {

    @Autowired
    PhotoService photoService;

    @RequestMapping(value = "/photo/view")
    public String photoView(Model model, HttpServletRequest request)throws Exception{
        // 得到userId     *********************************************
//        int uid = Integer.parseInt(request.getCookies()[0].getValue());
        int uid = 1;

        //通过userId得到相册列表
        List<Photo> photoList = photoService.photoList(uid);
        //将指定用户的相册列表通过model传递给jsp页面
        model.addAttribute("photoList",photoList);
        return "photo";
    }

}
