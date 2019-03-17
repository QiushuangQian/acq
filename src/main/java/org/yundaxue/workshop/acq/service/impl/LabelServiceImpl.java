package org.yundaxue.workshop.acq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yundaxue.workshop.acq.model.Label;
import org.yundaxue.workshop.acq.model.Mapper.LabelMapper;
import org.yundaxue.workshop.acq.service.LabelService;

/**
 * Created by lenovo on 2019/3/17.
 */
@Service
public class LabelServiceImpl implements LabelService {
    @Autowired
    LabelMapper labelMapper;

    @Override
    public void insertLabel(String labelName) throws Exception {
        labelMapper.insertLabel(labelName);
    }

    @Override
    public void deleteLabel(int labelId) throws Exception {
        labelMapper.deleteLabel(labelId);
    }

    @Override
    public void updateLabel(int labelId, String labelName) throws Exception {
        labelMapper.updateLabel(labelId,labelName);
    }

    @Override
    public Label getLabel(int labelId) throws Exception {
        return labelMapper.getLabel(labelId);
    }
}
