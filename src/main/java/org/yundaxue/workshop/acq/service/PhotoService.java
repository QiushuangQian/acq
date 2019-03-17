package org.yundaxue.workshop.acq.service;

import org.yundaxue.workshop.acq.model.Photo;

import java.util.List;


public interface PhotoService {
    Photo getPhoto(int photoId) throws Exception;
    List<Photo> listPhoto(int userId) throws Exception;
    int uploadPhoto(Photo photo) throws Exception;
    int deletePhoto(int photoId,int userId) throws Exception;
    int banPhoto(int photoId,int userId) throws Exception;
}
