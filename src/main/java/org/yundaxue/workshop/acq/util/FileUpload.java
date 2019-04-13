package org.yundaxue.workshop.acq.util;

import org.springframework.web.multipart.MultipartFile;
import org.yundaxue.workshop.acq.ConfigClass;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2019/3/31.
 */
public class FileUpload {
    /**
     * 文件上传处理
     *
     * @param file
     * @return
     */
    public static String writeUploadFile(MultipartFile file, String module,int uid) {
        String filename = file.getOriginalFilename();
        String realpath = ConfigClass.ImgsSavePath  +"images\\"+  module +"\\";

        //创建文件路径
        File fileDir = new File(realpath);
        if (!fileDir.exists())
            fileDir.mkdirs();

        //扩展名
        String extname;
        extname=filename.substring(filename.lastIndexOf(".")+1);
//        int index = filename.lastIndexOf("\\.");
//        if(index==-1){
//            extname = "jpg";
//        }else {
//            extname  = filename.substring(index+1);
//        }
        String allowImgFormat = "gif,jpg,jpeg,png，bmp";
        if (!allowImgFormat.contains(extname.toLowerCase())) {
            return "格式错误！";
        }

        //时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String a = sdf.format(new Date());

        //新文件名:创建时间+uid+初始文件名
        String newFileName = a + uid + Math.abs(file.getOriginalFilename().hashCode()) +  "." + extname;
        FileOutputStream fos = null;
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(realpath + newFileName));
            bos.write(file.getBytes());
            bos.flush();
            bos.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
        }
        return newFileName;
    }
}
