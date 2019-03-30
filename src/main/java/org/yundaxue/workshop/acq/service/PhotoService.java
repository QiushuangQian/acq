package org.yundaxue.workshop.acq.service;

import org.yundaxue.workshop.acq.model.Photo;

public interface PhotoService {
    //上传照片（向photo表插入记录）
    public void uploadPhoto(Photo photo) throws Exception;

}
