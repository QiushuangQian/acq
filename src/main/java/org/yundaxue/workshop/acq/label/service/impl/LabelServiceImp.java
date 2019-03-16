package org.yundaxue.workshop.acq.label.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yundaxue.workshop.acq.label.bo.Label;
import org.yundaxue.workshop.acq.label.dao.LabelDao;
import org.yundaxue.workshop.acq.label.service.LabelService;

/**
 * Created by lenovo on 2019/3/16.
 */
@Service("labelService")
public class LabelServiceImp implements LabelService{
    @Autowired
    LabelDao labelDao;


    @Override
    public void insertLabel(Label label) throws Exception {

    }

    @Override
    public void deleteLabel(int labelId) throws Exception {

    }

    @Override
    public Label getLabel(int labelId) throws Exception {
        return null;
    }

    @Override
    public void updateLabel(int labelId, String labelName) throws Exception {

    }
}
