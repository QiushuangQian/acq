package org.yundaxue.workshop.acq.photo.dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.yundaxue.workshop.acq.photo.bo.Photo;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {

    @Select("select * from photo")
    public Photo getPhoto();
}
