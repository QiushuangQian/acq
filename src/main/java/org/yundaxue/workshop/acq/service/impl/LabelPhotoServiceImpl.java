package org.yundaxue.workshop.acq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yundaxue.workshop.acq.dao.LabelDao;
import org.yundaxue.workshop.acq.dao.LabelPhotoDao;
import org.yundaxue.workshop.acq.dao.Mapper.LabelPhotoMapper;
import org.yundaxue.workshop.acq.dao.PhotoDao;
import org.yundaxue.workshop.acq.service.LabelPhotoService;

@Service("LabelPhotoService")
public class LabelPhotoServiceImpl implements LabelPhotoService {
    @Autowired
    LabelPhotoMapper labelPhotoMapper;

    @Override
    public PhotoDao getPhoto(int labelId) {
        return labelPhotoMapper.getPhoto(labelId);
    }

    @Override
    public LabelDao getLabel(int photoId) {
        return labelPhotoMapper.getLabel(photoId);
    }

    @Override
    public LabelPhotoDao insertLabelPhoto(int labelId, int photoId) {
        return labelPhotoMapper.insertLabelPhoto(labelId,photoId);
    }

    @Override
    public void deleteLabelPhoto(int labelPhotoId) {

    }
}
