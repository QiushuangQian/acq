package org.yundaxue.workshop.acq.service.impl;

import org.springframework.stereotype.Service;
import org.yundaxue.workshop.acq.dao.PhotoDao;
import org.yundaxue.workshop.acq.service.PhotoService;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by 耿志强 on 2019/3/17.
 */
@Service
public class PhotoServiceImpl implements PhotoService{

    @Override
    public PhotoDao getPhoto(int photoId) throws Exception {
        return null;
    }

    @Override
    public List<PhotoDao> listPhoto(int userId) throws Exception {
        return null;
    }

    @Override
    public PhotoDao uploadPhoto(PhotoDao photoDao) throws Exception {
        return null;
    }

    @Override
    public int deletePhoto(int photoId) throws Exception {
        return 0;
    }

    @Override
    public int banPhoto(int photoId) throws Exception {
        return 0;
    }
}
