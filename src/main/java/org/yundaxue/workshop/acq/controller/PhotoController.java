package org.yundaxue.workshop.acq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yundaxue.workshop.acq.ConfigClass;
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
    private List<Photo> list;//照片列表
    private int userId;//用户Id


    @Autowired
    PhotoService photoService;

    //获取照片列表
    @RequestMapping(value = "/photo")
    public String photo(Model model, HttpServletRequest request)throws Exception{


        //得到用户Id
        userId = ((User) request.getSession().getAttribute("USER")).getUserId();

        //得到选中的相册Id
        String albumId = (String) request.getSession().getAttribute("albumId");
        if(albumId==null){ //传递的相册Id为空时
            //按页数得到照片列表的字符串表示
            list = photoService.photoList(1, ConfigClass.maxnum, userId,1);
        }else {
            int id = Integer.parseInt(albumId);
            if(id==0){
                list = photoService.photoList(1, ConfigClass.maxnum, userId,1);
            }else {
                list = photoService.ablumPhotoList(1,ConfigClass.maxnum,userId,1,id);
            }
        }

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

        //得到选中的相册Id
        String albumId = (String) request.getSession().getAttribute("albumId");
        if(albumId==null){ //传递的相册Id为空时
            //按页数得到照片列表的字符串表示
            list = photoService.photoList(Integer.parseInt(pagenum), ConfigClass.maxnum, userId,1);
        }else {
            int id = Integer.parseInt(albumId);
            if(id==0){
                list = photoService.photoList(Integer.parseInt(pagenum), ConfigClass.maxnum, userId,1);
            }else {
                list = photoService.ablumPhotoList(Integer.parseInt(pagenum),ConfigClass.maxnum,userId,1,id);
            }
        }
        resultMap.put("photoList",list);
        return resultMap;
    }


    @RequestMapping(value = "/photo/photoDisplay" )
    public String photoDisplay(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        userId = ((User) request.getSession().getAttribute("USER")).getUserId();

        List<String> paths = photoService.showPhoto(userId);

        model.addAttribute("pathList",paths);
        return "photoDisplay";
    }
//删除照片，加入回收站
    @RequestMapping(value = "/deletePhoto")
    @ResponseBody
    public Map<String,String> deletePhoto(@RequestParam("selectPhotoList") String delPhotoList , HttpServletRequest request) throws Exception {

        int userId=((User)request.getSession().getAttribute("USER")).getUserId();
        Map<String,String> resultMap = new HashMap<String, String>();

        //得到要删除照片id的列表
        String[] arrayA = delPhotoList.split(",");
        for (int i = 0; i < arrayA.length; i++) {
            int delPhotoId=Integer.parseInt(arrayA[i]);


            List<Photo> photo=photoService.getPhotoById(delPhotoId,userId);
            if(photo.size()>0){

                //修改照片状态
                photoService.deletePhoto(delPhotoId,userId);
                resultMap.put("msg","success");
            }else {
                resultMap.put("msg","fail");
            }
        }
        return resultMap;
    }
}
