package org.yundaxue.workshop.acq.dao.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.yundaxue.workshop.acq.dao.PhotoDao;

import java.util.List;

@Mapper
public interface PhotoMapper {

    //得到所有的照片
    @Select("select * from photo")
    public List<PhotoDao> getPhotos();


}
