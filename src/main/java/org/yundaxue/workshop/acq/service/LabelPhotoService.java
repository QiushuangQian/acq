package org.yundaxue.workshop.acq.service;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.yundaxue.workshop.acq.dao.LabelDao;
import org.yundaxue.workshop.acq.dao.LabelPhotoDao;
import org.yundaxue.workshop.acq.dao.PhotoDao;

public interface LabelPhotoService {
    public PhotoDao getPhoto(int labelId);
    public LabelDao getLabel(int photoId);
    public LabelPhotoDao insertLabelPhoto(int labelId, int photoId);
    public void deleteLabelPhoto(int labelPhotoId);
}
