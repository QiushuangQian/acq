package org.yundaxue.workshop.acq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yundaxue.workshop.acq.model.Photo;
import org.yundaxue.workshop.acq.service.PhotoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PhotoController {
    //设置每页最多显示多少张照片
    private int maxnum = 10;

    @Autowired
    PhotoService photoService;

    @RequestMapping(value = "/photo")
    public String photo(Model model, HttpServletRequest request)throws Exception{
        //得到用户Id
//        int userId = request.getSession().getAttribute("userId");
        int userId = 1;
        //按页数得到照片列表的字符串表示
//        List<String> list = getPhotoList(1,maxnum,userId);
        List<Photo> list = photoService.photoList(1, maxnum, userId);

        //将指定用户的相册列表通过model传递给jsp页面
        model.addAttribute("initial",list);
        return "photoDisplay";
    }

    @RequestMapping(value = "/photoList")
    @ResponseBody
    public Map<String,Object> photoList(@RequestParam("pagenum") String pagenum , HttpServletRequest request) throws Exception{
        //得到用户Id
//        int userId = request.getSession().getAttribute("userId");
        int userId = 1;


        Map<String,Object> resultMap = new HashMap<String, Object>();
        if(pagenum == null){
            resultMap.put("result", false);
            return resultMap;
        }
        resultMap.put("result",true);

        //按页数得到照片列表的字符串表示
//        List<String> list1 = getPhotoList(Integer.parseInt(pagenum),maxnum,userId);
//        resultMap.put("photoList",list1);
        resultMap.put("photoList",photoService.photoList(Integer.parseInt(pagenum), maxnum, userId));
        return resultMap;
    }

//    public List<Photo> getPhotoList(int pagenum,int maxnum,int userId)throws Exception{
//        List<String> result = new ArrayList<String>();
//        //得到照片列表
//        List<Photo> list2 = photoService.photoList(pagenum, maxnum, userId);
//        //改写成htm语句
//        for(Photo p:list2){
//            result.add(p.getThumbnailPath());
//        }
//
//        return result;
//    }

    @RequestMapping(value = "/photo/photoDisplay" )
    public String photoDisplay(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int userId=1;
        List<String> paths = photoService.showPhoto(userId);
        /*Map<String, File> fileNameMap = new HashMap<>();
        File[] fileList = new File(photoService.showPhoto(userId)).listFiles();
        for (File file : fileList){
            fileNameMap.put(file.getName(),file);
        }*/
        model.addAttribute("pathList",paths);
        return "photoDisplay";
    }

    /*@RequestMapping(value = "/photo/download")
    public void downloadPhoto(ModelMap model, HttpServletRequest request, HttpServletResponse response){
        String path = request.getParameter("filePath");
        try {
            File file = new File(path);
            if (!file.exists()){
                request.setAttribute("message"," 下载资源已被删除");
                System.out.println("Download file is deleted!");
                return;
            }
            //设置响应头
            response.setHeader("content-disposition","attachmemnt;filename="+ URLEncoder.encode(path,"UTF-8"));
            //读取要下载的图片，放入缓冲区再下载
            FileInputStream in = new FileInputStream(path);
            OutputStream out = response.getOutputStream();
            byte buffer[] = new byte[1024];
            int len = 0;
            while((len = in.read(buffer))>0){
                out.write(buffer,0,len);
            }
            in.close();
            out.close();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
