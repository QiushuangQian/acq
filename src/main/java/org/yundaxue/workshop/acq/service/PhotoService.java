package org.yundaxue.workshop.acq.service;

import org.yundaxue.workshop.acq.model.Photo;

import java.util.List;

public interface PhotoService {
    //上传照片（向photo表插入记录）
    public void uploadPhoto(Photo photo) throws Exception;

    //根据相册Id得到照片列表
    public List<Photo> photoList(int albumId) throws Exception;

}
