package org.yundaxue.workshop.acq.model.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.yundaxue.workshop.acq.model.Photo;

import java.util.Date;
import java.util.List;

@Mapper
public interface RecycleBinMapper {

    //获取删除的照片
    @Select("select * from photo where del_state=0")
    public List<Photo> getDel();
}
