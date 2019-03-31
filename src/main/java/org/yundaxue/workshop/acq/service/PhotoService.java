package org.yundaxue.workshop.acq.service;

import org.yundaxue.workshop.acq.model.Photo;

import java.util.List;

public interface PhotoService {
    //上传照片（向photo表插入记录）
    public void uploadPhoto(Photo photo) throws Exception;

    //显示照片列表
    public String listPhoto(int userId) throws Exception;

    //显示照片
    public String getPhoto(int photoId) throws Exception;
}
