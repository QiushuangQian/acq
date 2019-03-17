package org.yundaxue.workshop.acq.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yundaxue.workshop.acq.dao.Mapper.PhotoMapper;
import org.yundaxue.workshop.acq.dao.PhotoDao;
import org.yundaxue.workshop.acq.service.PhotoService;

import java.util.List;
@Service("PhotoService")
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoMapper photoMapper;

    @Override
    public PhotoDao getPhoto(int photoId) throws Exception {
        return photoMapper.getPhoto(photoId);
    }

    @Override
    public List<PhotoDao> listPhoto(int userId) throws Exception {
        return photoMapper.listPhoto(userId);
    }

    @Override
    public int uploadPhoto(PhotoDao photoDao) throws Exception {
        return photoMapper.uploadPhoto(photoDao);
    }

    @Override
    public int deletePhoto(int photoId, int userId) throws Exception {
        return photoMapper.deletePhoto(photoId,userId);
    }

    @Override
    public int banPhoto(int photoId, int userId) throws Exception {
        return photoMapper.banPhoto(photoId,userId);
    }
}
