package org.yundaxue.workshop.acq.dao.Mapper;

import org.apache.ibatis.annotations.Select;
import org.yundaxue.workshop.acq.dao.LabelDao;

/**
 * Created by lenovo on 2019/3/17.
 */
public interface LabelMapper {
    //根据labelId得到标签
    @Select("select * from label where label_id=${labelId}")
    public LabelDao getLabel(int labelId);
}
