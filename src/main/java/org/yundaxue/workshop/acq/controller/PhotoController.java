package org.yundaxue.workshop.acq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yundaxue.workshop.acq.model.Photo;
import org.yundaxue.workshop.acq.model.User;
import org.yundaxue.workshop.acq.service.PhotoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PhotoController {
    //设置每页最多显示多少张照片
    private int maxnum = 10;

    private int userId;
//    private int userId=1;


    @Autowired
    PhotoService photoService;

    @RequestMapping(value = "/photo")
    public String photo(Model model, HttpServletRequest request)throws Exception{
        //得到用户Id
        userId = ((User) request.getSession().getAttribute("USER")).getUserId();

        //按页数得到照片列表的字符串表示
        List<Photo> list = photoService.photoList(1, maxnum, userId,1);

        //将指定用户的相册列表通过model传递给jsp页面
        model.addAttribute("initial",list);
        return "photoDisplay";
    }

    @RequestMapping(value = "/photoList")
    @ResponseBody
    public Map<String,Object> photoList(@RequestParam("pagenum") String pagenum , HttpServletRequest request) throws Exception{
        //得到用户Id
        userId = ((User) request.getSession().getAttribute("USER")).getUserId();

        Map<String,Object> resultMap = new HashMap<String, Object>();
        if(pagenum == null){
            resultMap.put("result", false);
            return resultMap;
        }
        resultMap.put("result",true);

        resultMap.put("photoList",photoService.photoList(Integer.parseInt(pagenum), maxnum, userId,1));
        return resultMap;
    }


    @RequestMapping(value = "/photo/photoDisplay" )
    public String photoDisplay(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        userId = ((User) request.getSession().getAttribute("USER")).getUserId();

        List<String> paths = photoService.showPhoto(userId);

        model.addAttribute("pathList",paths);
        return "photoDisplay";
    }


}
