package org.yundaxue.workshop.acq.model.Mapper;

import org.apache.ibatis.annotations.*;
import org.yundaxue.workshop.acq.model.Photo;

import java.util.List;

@Mapper
public interface PhotoMapper {

    //得到所有的照片
    @Select("select * from photo where user_id=#{userId}")
    public List<Photo> listPhoto(int userId);

    @Select("select * from photo where photo_id=#{photoId}")
    public Photo getPhoto(int photoId);

    //插入照片，返回photoId——峰
    @Insert("insert into photo(user_id,photo_type,size,upload_time,photo_path,thumbnail_path) values(#{photo.userId},#{photo.type},#{photo.size},#{photo.uploadTime,jdbcType=TIMESTAMP},#{photo.photoPath},#{photo.thumbnailPath})")
    @Options(useGeneratedKeys=true,keyProperty="photoId")
    public void insertPhoto(@Param("photo") Photo photo);

    @Update("update photo set del_state=0 where photo_id=#{photoId},user_id=#{userId}")
    public int deletePhoto(int photoId,int userId);

    @Update("update photo set ban_state=0 where photo_id=#{photoId},user_id=#{userId}")
    public int banPhoto(int photoId,int userId);


}
