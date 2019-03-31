package org.yundaxue.workshop.acq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yundaxue.workshop.acq.service.PhotoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PhotoController {
    @Autowired
    PhotoService photoService;
    @Autowired
    HttpSession session;

    @RequestMapping(value = "/photo/photoDisplay")
    public String photoDisplay(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int userId=1;
        Map<String, File> fileNameMap = new HashMap<>();
        File[] fileList = new File(photoService.showPhoto(userId)).listFiles();
        for (File file : fileList){
            fileNameMap.put(file.getName(),file);
        }
        model.addAttribute("fileNameMap",fileNameMap);
        return "/photoDisplay";
    }

    @RequestMapping(value = "/photo/download")
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
    }
}
