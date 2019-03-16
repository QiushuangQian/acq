package org.yundaxue.workshop.acq.label.service;

import org.yundaxue.workshop.acq.label.bo.Label;

/**
 * Created by lenovo on 2019/3/16.
 */
public interface LabelService {
    //增加标签
    void insertLabel(Label label) throws Exception;

    //删除标签
    void deleteLabel(int labelId) throws Exception;

    //查找标签
    Label getLabel(int labelId) throws Exception;

    //修改标签
    void updateLabel(int labelId,String labelName) throws Exception;

}
