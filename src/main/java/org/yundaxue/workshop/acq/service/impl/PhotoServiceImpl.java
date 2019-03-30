package org.yundaxue.workshop.acq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yundaxue.workshop.acq.model.Mapper.PhotoMapper;
import org.yundaxue.workshop.acq.model.Photo;
import org.yundaxue.workshop.acq.service.PhotoService;

/**
 * Created by lenovo on 2019/3/27.
 */
@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoMapper photoMapper;

    //插入photo表——峰
    @Override
    public void uploadPhoto(Photo photo) {
        photoMapper.insertPhoto(photo);
    }
}
