package org.yundaxue.workshop.acq.service;

import org.yundaxue.workshop.acq.model.Photo;

import java.util.List;

public interface PhotoService {
    //上传照片（向photo表插入记录）
    public void uploadPhoto(Photo photo) throws Exception;

    //显示照片列表
    public List<String> showPhoto(int userId) throws Exception;

    //显示照片
    public List<String> getPhoto(int photoId) throws Exception;

    //根据相册Id得到照片列表
    public List<Photo> photoList(int albumId) throws Exception;
    //根据照片id得到照片对象
    public List<Photo> getPhotoById(int photoId,int userId) throws Exception;

    //删除照片
    public boolean deletePhoto(int photoId,int userId) throws Exception;
    //完全删除照片
    public  boolean completeDeletePhoto(int photoId,int userId) throws Exception;

    //根据页号和每页显示的照片数得到一个照片列表——峰
    public List<Photo> photoList(int pagenum,int maxnum,int userId,int isDel) throws Exception;
    //根据相册Id，页面，每页显示照片书，用户Id得到照片列表
    public List<Photo> ablumPhotoList(int pagenum,int maxnum,int userId,int isDel,int albumId)throws Exception;

    //恢复照片
    public boolean restorePhoto(int restorePhotoId,int userId) throws Exception;
}
