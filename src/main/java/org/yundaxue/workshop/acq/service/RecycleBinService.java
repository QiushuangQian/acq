package org.yundaxue.workshop.acq.service;

import org.yundaxue.workshop.acq.model.Photo;

import java.util.Date;
import java.util.List;

public interface RecycleBinService {
    public List<Photo> getDel() throws Exception;
}
