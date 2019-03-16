package org.yundaxue.workshop.acq.label.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.yundaxue.workshop.acq.label.bo.Label;

/**
 * Created by lenovo on 2019/3/16.
 */
@Mapper
public interface LabelDao {
    Label insertLabel(@Param("label") Label label) throws Exception;

    void deleteLabel(@Param("labelId") int labelId) throws Exception;

    Label getLabel(@Param("labelId") int labelId) throws Exception;

    void updateLabel(@Param("labelId") int labelId,@Param("labelName") String labelName) throws Exception;

}
