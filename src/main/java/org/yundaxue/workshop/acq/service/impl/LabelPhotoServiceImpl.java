package org.yundaxue.workshop.acq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yundaxue.workshop.acq.model.Label;
import org.yundaxue.workshop.acq.model.LabelPhoto;
import org.yundaxue.workshop.acq.model.Mapper.LabelPhotoMapper;
import org.yundaxue.workshop.acq.model.Photo;
import org.yundaxue.workshop.acq.service.LabelPhotoService;

@Service("LabelPhotoService")
public class LabelPhotoServiceImpl implements LabelPhotoService {
    @Autowired
    LabelPhotoMapper labelPhotoMapper;

    @Override
    public Photo getPhoto(int labelId) {
        return labelPhotoMapper.getPhoto(labelId);
    }

    @Override
    public Label getLabel(int photoId) {
        return labelPhotoMapper.getLabel(photoId);
    }

    @Override
    public LabelPhoto insertLabelPhoto(int labelId, int photoId) {
        return labelPhotoMapper.insertLabelPhoto(labelId,photoId);
    }

    @Override
    public void deleteLabelPhoto(int labelPhotoId) {

    }
}
