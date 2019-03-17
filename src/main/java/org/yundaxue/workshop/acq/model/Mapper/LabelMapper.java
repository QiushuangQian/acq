package org.yundaxue.workshop.acq.model.Mapper;

import org.apache.ibatis.annotations.*;
import org.yundaxue.workshop.acq.model.Label;

/**
 * Created by lenovo on 2019/3/17.
 */
@Mapper
public interface LabelMapper {

    //插入标签
    @Insert("insert into label (label_name) values(#{labelName})")
    public void insertLabel(String labelName);

    //根据labelId删除标签
    @Delete("delete from label where label_id=#{labelId}")
    public void deleteLabel(int labelId);

    //根据labelId修改标签的名称
    @Update("update label set label_name = #{labelName} where label_id = #{labelId}")
    public void updateLabel(@Param("labelId") int labelId,@Param("labelName") String labelName);

    //根据labelId得到标签
    @Select("select * from label where label_id=#{labelId}")
    public Label getLabel(int labelId);
}
