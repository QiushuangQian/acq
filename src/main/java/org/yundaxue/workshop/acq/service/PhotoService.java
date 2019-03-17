package org.yundaxue.workshop.acq.service;

import org.yundaxue.workshop.acq.dao.PhotoDao;

import java.util.List;

public interface PhotoService {
    PhotoDao getPhoto(int photoId) throws Exception;
    List<PhotoDao> listPhoto(int userId) throws Exception;
    PhotoDao uploadPhoto(PhotoDao photoDao) throws Exception;
    int deletePhoto(int photoId) throws Exception;
    int banPhoto(int photoId) throws Exception;
}
