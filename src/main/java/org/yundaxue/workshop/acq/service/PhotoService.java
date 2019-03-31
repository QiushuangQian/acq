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

    //删除照片
    public boolean deletePhoto(int photoId,int userId) throws Exception;

}
