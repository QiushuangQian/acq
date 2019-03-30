package org.yundaxue.workshop.acq.controller;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.yundaxue.workshop.acq.model.Album;
import org.yundaxue.workshop.acq.model.AlbumPhoto;
import org.yundaxue.workshop.acq.model.Photo;
import org.yundaxue.workshop.acq.service.AlbumPhotoService;
import org.yundaxue.workshop.acq.service.AlbumService;
import org.yundaxue.workshop.acq.service.PhotoService;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2019/3/24.
 */
@Controller
public class UploaderController {

    private static Logger logger = Logger.getLogger(UserController.class);
    private static final String excelPath = "E:\\";

    @Autowired
    PhotoService photoService;
    @Autowired
    AlbumService albumService;
    @Autowired
    AlbumPhotoService albumPhotoService;


    @RequestMapping(value = "/upload")
    public String upload(Model model, HttpServletRequest request)throws Exception{
        // 得到userId     *********************************************
//        int uid = Integer.parseInt(request.getCookies()[0].getValue());
        int uid = 1;

        //通过userId得到相册列表
        List<Album> AblumList = albumService.albumList(uid);
        //将指定用户的相册列表通过model传递给jsp页面
        model.addAttribute("albumList",AblumList);
        return "upload";
    }


    @RequestMapping(value = "/doUpload")
    @ResponseBody
    public String doUpload(@RequestParam("selectedAlbumId") String selectedAlbumId,@RequestParam("file") MultipartFile file, HttpServletRequest request)throws Exception {



        //保存到数据库的路径
        String photoPath=null;
        String thumbnailPath=null;
        //图片初始名，例：xxx.jpg
        String originalFilename = file.getOriginalFilename();
        //原图上传到服务器的文件路径
        String photoFilePath = excelPath + File.separator +"test"+ File.separator +"img"+ File.separator +  originalFilename;

        //得到缩略图的文件路径，例：xxx_small.jpg
        String thumbnailFilename=originalFilename.split("\\.",2)[0]+"_small."
                                                    +originalFilename.split("\\.",2)[1];
        //缩略图上传到服务器的文件路径
        String thumbnailFilePath= excelPath+File.separator+"test"+ File.separator +"thumbnail"+File.separator +thumbnailFilename;

        //新建原图文件
        File photoFile = new File(photoFilePath);
        //新建缩略图文件
        File thumbnailFile=new File(thumbnailFilePath);
        if (!photoFile.exists()) {
            //原图文件不存在就创建
            photoFile.getParentFile().mkdirs();
        }
        if(!thumbnailFile.exists()){
            //缩略图文件不存在就创建
            thumbnailFile.getParentFile().mkdirs();
        }
        //创建文件是否成功标志
        Date createFile = createFile(file,photoFile);
        if (createFile!=null) {   //创建文件成功

            //保存缩略图
            Thumbnails.of(photoFilePath)
                    //设置图片大小
                    .size(300,200)
                    //输出到文件
                    .toFile(thumbnailFile);

            //定义photo对象
            Photo photo=new Photo();

            //设置图片上传时间

            photo.setUploadTime(createFile);


            //设置图片类型
            String typeName = originalFilename.split("\\.",2)[1];
            int type;
            switch (typeName){
                case "gif":type=0;break;
                case "jpg":
                case "jpeg":type=1;break;
                case "bmp":type=2;break;
                case "png":type=3;break;
                default:type=-1;break;
            }
            photo.setType(type);
            //设置图片大小
            photo.setSize(file.getSize());

            //设置图片所有者
//            photo.setUserId(Integer.valueOf(request.getCookies().toString()));    *******************************
            photo.setUserId(1);

            //设置图片原图和缩略图的相对路径
            photo.setPhotoPath("/photo/"+photoFilePath);
            photo.setThumbnailPath("/photo/"+thumbnailFilePath);

            //新建albumPhoto对象
            AlbumPhoto albumPhoto = new AlbumPhoto();
            //将图片信息保存到数据库photo表,同时返回photoId
            photoService.uploadPhoto(photo);
            albumPhoto.setPhotoId(photo.getPhotoId());
            //得到选中的相册的Id    *********************************************************
            albumPhoto.setAlbumId(Integer.parseInt(selectedAlbumId));
            //将得到的albumPhoto对象插入到album_photo表中
            albumPhotoService.insertAlbumPhoto(albumPhoto);

            return "上传成功！";
        }else {
            return "上传失败！";
        }

    }

    //创建文件，写文件
    private Date createFile(MultipartFile file,File desFile) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(desFile));
            bos.write(file.getBytes());
            bos.flush();
            bos.close();
//            Date date = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
//            String a = sdf.format(date);
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
//            Date createTime = df.parse(a);
//            return createTime;
            return new Date();
        } catch (Exception e) {
            logger.error("【文件上传异常】：",e);
        }
        return null;
    }


}
