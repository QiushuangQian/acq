package org.yundaxue.workshop.acq.service;

import org.yundaxue.workshop.acq.dao.PhotoDao;

import java.util.List;


public interface PhotoService {
    PhotoDao getPhoto(int photoId) throws Exception;
    List<PhotoDao> listPhoto(int userId) throws Exception;
    int uploadPhoto(PhotoDao photoDao) throws Exception;
    int deletePhoto(int photoId,int userId) throws Exception;
    int banPhoto(int photoId,int userId) throws Exception;
}
