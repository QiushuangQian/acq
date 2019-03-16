package org.yundaxue.workshop.acq.photo.service;

import org.apache.ibatis.annotations.Param;
import org.yundaxue.workshop.acq.photo.bo.Photo;

import java.util.List;

public interface PhotoService {
    Photo getPhoto(int photoId) throws Exception;
    List<Photo> listPhoto(int userId) throws Exception;
    Photo uploadPhoto(Photo photo) throws Exception;
    int deletePhoto(int photoId) throws Exception;
    int banPhoto(int photoId) throws Exception;
}
