package org.yundaxue.workshop.acq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yundaxue.workshop.acq.ConfigClass;
import org.yundaxue.workshop.acq.model.Photo;
import org.yundaxue.workshop.acq.model.User;
import org.yundaxue.workshop.acq.service.PhotoService;
import org.yundaxue.workshop.acq.service.RecycleBinService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2019/4/14.
 */
@Controller
public class RecycleBinController {
    private int userId;
    private int maxPageNum;
    @Autowired
    PhotoService photoService;
    RecycleBinService recycleBinService;

    //打开回收站页面
    @RequestMapping(value = "/recycleBin")
    public String recycleBins(ModelMap model, HttpServletRequest request, HttpServletResponse response)throws Exception{
        //得到用户Id
        userId=((User)request.getSession().getAttribute("USER")).getUserId();
        //得到最大分页数
        maxPageNum = photoService.getMaxPageNum(ConfigClass.maxnum,userId,0);
        if(maxPageNum==0){
            maxPageNum=1;
        }

        //按页数得到照片列表的字符串表示
        List<Photo> list = photoService.photoList(1, ConfigClass.maxnum, userId,0);

        //将指定用户的相册列表通过model传递给jsp页面
        model.addAttribute("initial",list);
        model.addAttribute("maxPageNum",maxPageNum);
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
        String tmp = pagenum;
        //得到状态为删除的照片列表
        resultMap.put("delPhotoList",photoService.photoList(Integer.parseInt(pagenum), ConfigClass.maxnum, userId,0));
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
                //得到最大分页数
                maxPageNum = photoService.getMaxPageNum(ConfigClass.maxnum,userId,0);
                if(maxPageNum==0){
                    maxPageNum=1;
                }
                resultMap.put("msg","success");
                resultMap.put("maxPageNum",maxPageNum+"");
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
                //得到最大分页数
                maxPageNum = photoService.getMaxPageNum(ConfigClass.maxnum,userId,0);
                resultMap.put("msg","success");
                resultMap.put("maxPageNum",maxPageNum+"");
            }else {
                resultMap.put("msg","fail");
            }
        }
        return resultMap;
    }

//    @Scheduled(cron = "0 * 15 * * ?")
//    public void autoDelete() throws Exception {
//        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");//如2016-08-10 20:40
//        //获取当前时间
//        Date now = new Date();
//        //获取照片删除时间
//
//        for(Photo p : recycleBinService.getDel()) {
//
//            String fromDate = simpleFormat.format(p.getDelTime());
//            String toDate = simpleFormat.format(now);
//
//            long from = simpleFormat.parse(fromDate).getTime();
//            long to = simpleFormat.parse(toDate).getTime();
//
//            //计算时间差
//            int hours = (int) ((to - from) / (1000 * 60 * 60));
//
//
//            int minutes = (int) ((to - from) / (1000 * 60));
//
//            //执行彻底删除操作
//            if (minutes >= 5) {
//                photoService.completeDeletePhoto(p.getPhotoId(),p.getUserId());
//            }
//        }
//    }

}
