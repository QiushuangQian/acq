package org.yundaxue.workshop.acq.photo.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yundaxue.workshop.acq.photo.bo.Photo;
import org.yundaxue.workshop.acq.photo.dao.PhotoDAO;
import org.yundaxue.workshop.acq.photo.service.PhotoService;

import java.util.List;
@Service("PhotoService")
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    PhotoDAO photoDAO;

    @Override
    public Photo getPhoto(int photoId) throws Exception {
        return photoDAO.getPhoto(photoId);
    }

    @Override
    public List<Photo> listPhoto(int userId) throws Exception {
        return photoDAO.listPhoto(userId);
    }

    @Override
    public void insertPhoto(Photo photo) throws Exception {

    }
}
