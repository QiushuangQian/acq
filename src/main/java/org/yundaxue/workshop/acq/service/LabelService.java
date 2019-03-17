package org.yundaxue.workshop.acq.service;

import org.yundaxue.workshop.acq.dao.LabelDao;

/**
 * Created by lenovo on 2019/3/17.
 */
public interface LabelService {
    //插入标签
    void insertLabel(String labelName) throws Exception;

    //删除标签
    void deleteLabel(int labelId) throws Exception;

    //修改标签名
    void updateLabel(int labelId, String labelName) throws Exception;

    //查找标签
    LabelDao getLabel(int labelId) throws Exception;


}
