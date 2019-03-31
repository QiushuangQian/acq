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
import org.yundaxue.workshop.acq.ConfigClass;
import org.yundaxue.workshop.acq.model.Album;
import org.yundaxue.workshop.acq.model.AlbumPhoto;
import org.yundaxue.workshop.acq.model.Photo;
import org.yundaxue.workshop.acq.service.AlbumPhotoService;
import org.yundaxue.workshop.acq.service.AlbumService;
import org.yundaxue.workshop.acq.service.PhotoService;
import org.yundaxue.workshop.acq.util.FileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2019/3/24.
 */
@Controller
public class UploaderController {

    //        int uid = request.getSession().getAttribute("userId");
    private int uid = 1;
    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    PhotoService photoService;
    @Autowired
    AlbumService albumService;
    @Autowired
    AlbumPhotoService albumPhotoService;


    @RequestMapping(value = "/upload")
    public String upload(Model model, HttpServletRequest request)throws Exception{
        //通过userId得到相册列表
        List<Album> AblumList = albumService.albumList(uid);
        //将指定用户的相册列表通过model传递给jsp页面
        model.addAttribute("albumList",AblumList);
        return "upload";
    }


    @RequestMapping(value = "/doUpload")
    @ResponseBody
    public String doUpload(@RequestParam("selectedAlbumId") String selectedAlbumId,@RequestParam("file") MultipartFile file, HttpServletRequest request)throws Exception {

        //上传后得到的文件名，前0-13位为创建时间
        String photoFileName = FileUpload.writeUploadFile(file,"photo",uid);
        //得到缩略图的文件名（去除拓展名）
        String thumbnailFileName = photoFileName.substring(0,photoFileName.lastIndexOf("."));

        //原图上传到服务器的文件路径
        String photoFilePath = ConfigClass.ImgsSavePath +"photo\\" +  photoFileName;
        //缩略图上传到服务器的文件路径
        String thumbnailFilePath=ConfigClass.ImgsSavePath +"thumbnail\\"  +  thumbnailFileName;

        //新建缩略图文件路径
        File thumbnailFile=new File(thumbnailFilePath);
        if(!thumbnailFile.exists()){
            //缩略图文件不存在就创建
            thumbnailFile.getParentFile().mkdirs();
        }

        if (photoFileName!=null && !photoFileName.equals("格式错误！")) {   //创建文件成功
            //保存缩略图
            Thumbnails.of(photoFilePath)
                    //设置图片大小
                    .size(300,200)
                    //输出到文件
                    .toFile(thumbnailFilePath);

            //定义photo对象
            Photo photo=new Photo();

            //设置图片上传时间
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//            Date createTime = df.parse(photoFileName.substring(13));    //格式化前14位
            photo.setUploadTime(new Date());
//            photo.setUploadTime(createTime);

            //设置图片类型
            String typeName = photoFileName.substring(photoFileName.lastIndexOf("\\.")+1);
            int type = -1;
            if(typeName.equals("gif"))  type=0;
            else if(typeName.equals("jpg") || typeName.equals("jpeg")) type=1;
            else if(typeName.equals("bmp")) type=2;
            else if(typeName.equals("png")) type=3;
            photo.setType(type);

            //设置图片大小
            photo.setSize(file.getSize());

            //设置图片所有者
            photo.setUserId(uid);

            //设置图片原图和缩略图的路径
            photo.setPhotoPath("/images/photo/"+photoFileName);
            photo.setThumbnailPath("/images/thumbnail/"+thumbnailFileName+".JPEG");

            //新建albumPhoto对象
            AlbumPhoto albumPhoto = new AlbumPhoto();
            //将图片信息保存到数据库photo表,同时返回photoId
            photoService.uploadPhoto(photo);
            albumPhoto.setPhotoId(photo.getPhotoId());
            //得到选中的相册的Id
            albumPhoto.setAlbumId(Integer.parseInt(selectedAlbumId));
            //将得到的albumPhoto对象插入到album_photo表中
            albumPhotoService.insertAlbumPhoto(albumPhoto);

            return "上传成功！";
        }else if(photoFileName.equals("格式错误！")) {
            return "格式错误！";
        }else {
            return "上传错误！";
        }

    }



}
