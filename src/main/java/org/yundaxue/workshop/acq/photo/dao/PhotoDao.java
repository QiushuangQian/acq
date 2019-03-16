package org.yundaxue.workshop.acq.photo.dao;
//
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.yundaxue.workshop.acq.photo.bo.Photo;
import java.util.List;

@Mapper
public interface PhotoDao {
    abstract Photo getPhoto(@Param("photoId") int photoId) throws Exception;
    List<Photo> listPhoto(@Param("userId") int userId) throws Exception;
    void insertPhoto(@Param("photo") Photo photo) throws Exception;
}
