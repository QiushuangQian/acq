package org.yundaxue.workshop.acq.photo.service;

import org.apache.ibatis.annotations.Param;
import org.yundaxue.workshop.acq.photo.bo.Photo;

import java.util.List;

public interface PhotoService {
    Photo getPhoto(int photoId) throws Exception;
    List<Photo> listPhoto(int userId) throws Exception;
    void insertPhoto(Photo photo) throws Exception;
}
