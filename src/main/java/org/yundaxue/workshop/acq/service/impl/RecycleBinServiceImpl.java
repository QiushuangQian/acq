package org.yundaxue.workshop.acq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yundaxue.workshop.acq.model.Mapper.RecycleBinMapper;
import org.yundaxue.workshop.acq.model.Photo;
import org.yundaxue.workshop.acq.service.RecycleBinService;

import java.util.List;
@Service
public class RecycleBinServiceImpl implements RecycleBinService {
    @Autowired
    RecycleBinMapper recycleBinMapper;

    @Override
    //返回删除的照片
    public List<Photo> getDel() throws Exception {
        return recycleBinMapper.getDel();
    }
}
