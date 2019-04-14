package org.yundaxue.workshop.acq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2019/4/14.
 */
@Controller
public class RecycleBinController {
    private int maxnum = 10;//分页显示最大数
    private int userId;
    @Autowired
    PhotoService photoService;

    //打开回收站页面
    @RequestMapping(value = "/recycleBin")
    public String recycleBins(ModelMap model, HttpServletRequest request, HttpServletResponse response)throws Exception{
        //得到用户Id
        userId=((User)request.getSession().getAttribute("USER")).getUserId();

        //按页数得到照片列表的字符串表示
        List<Photo> list = photoService.photoList(1, maxnum, userId,0);

        //将指定用户的相册列表通过model传递给jsp页面
        model.addAttribute("initial",list);
        return "/recycleBin";
    }
    //通过页数得到状态为删除的照片
    @RequestMapping(value = "/delPhotoList")
    @ResponseBody
    public Map<String,Object> delPhotoList(@RequestParam("pagenum") String pagenum , HttpServletRequest request) throws Exception{
        //得到用户Id
        userId = ((User) request.getSession().getAttribute("USER")).getUserId();

        Map<String,Object> resultMap = new HashMap<String, Object>();
        if(pagenum == null){
            resultMap.put("result", false);
            return resultMap;
        }
        resultMap.put("result",true);
        //得到状态为删除的照片列表
        resultMap.put("delPhotoList",photoService.photoList(Integer.parseInt(pagenum), maxnum, userId,0));
        return resultMap;
    }

    //删除照片
    @RequestMapping(value = "/doRecycleBin")
    @ResponseBody
    public Map<String,String> doRecycleBin(@RequestParam("selectPhotoList") String delPhotoList , HttpServletRequest request) throws Exception {

        int userId=((User)request.getSession().getAttribute("USER")).getUserId();
        Map<String,String> resultMap = new HashMap<String, String>();

        //得到要删除照片id的列表
        String[] arrayA = delPhotoList.split(",");
        for (int i = 0; i < arrayA.length; i++) {
            int delPhotoId=Integer.parseInt(arrayA[i]);
            //删除服务器中的图片
            //得到删除照片的路径
            List<Photo> photo=photoService.getPhotoById(delPhotoId,userId);
            if(photo.size()>0){
                for (Photo p:photo) {
                    String photoPath=p.getPhotoPath();
                    String thumbnailPath=p.getThumbnailPath();
                    File photoFile=new File(ConfigClass.ImgsSavePath+photoPath);
                    File thumbnailFile=new File(ConfigClass.ImgsSavePath+thumbnailPath);
                    photoFile.delete();
                    thumbnailFile.delete();
                }
                //删除数据库中的记录
                photoService.completeDeletePhoto(delPhotoId,userId);
                resultMap.put("msg","success");
            }else {
                resultMap.put("msg","fail");
            }
        }
        return resultMap;
    }
    //恢复照片
    @RequestMapping(value = "/restorePhoto")
    @ResponseBody
    public Map<String,String> doRecycleBins(@RequestParam("selectPhotoList") String selectPhotoList , HttpServletRequest request) throws Exception {

        int userId = ((User) request.getSession().getAttribute("USER")).getUserId();
        Map<String, String> resultMap = new HashMap<String, String>();

        //得到要恢复照片id的列表
        String[] arrayA = selectPhotoList.split(",");
        for (int i = 0; i < arrayA.length; i++) {
            int restorePhotoId = Integer.parseInt(arrayA[i]);
            //修改照片状态
            boolean restoreResult = photoService.restorePhoto(restorePhotoId,userId);
            if(restoreResult){
                resultMap.put("msg","success");
            }else {
                resultMap.put("msg","fail");
            }
        }
        return resultMap;
    }
}
