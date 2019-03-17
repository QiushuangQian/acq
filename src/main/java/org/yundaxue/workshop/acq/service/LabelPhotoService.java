package org.yundaxue.workshop.acq.service;

import org.yundaxue.workshop.acq.model.Label;
import org.yundaxue.workshop.acq.model.LabelPhoto;
import org.yundaxue.workshop.acq.model.Photo;

public interface LabelPhotoService {
    public Photo getPhoto(int labelId);
    public Label getLabel(int photoId);
    public LabelPhoto insertLabelPhoto(int labelId, int photoId);
    public void deleteLabelPhoto(int labelPhotoId);
}
