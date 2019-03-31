package org.yundaxue.workshop.acq.model.Mapper;

import org.apache.ibatis.annotations.*;
import org.yundaxue.workshop.acq.model.Photo;

import java.util.List;

@Mapper
public interface PhotoMapper {

    //得到用户所有照片
    @Select("select thumbnail_path from photo where user_id=#{userId}")
    public String showPhoto(@Param("userId") int userId);

    @Select("select * from photo where user_id=#{userId}")
    public List<Photo> listPhoto(@Param("userId") int userId);

    //显示照片
    @Select("select photo_path from photo where photo_id=#{photoId}")
    public String getPhoto(@Param("photoId") int photoId);

    //插入照片，返回photoId——峰
    @Insert("insert into photo(user_id,photo_type,size,upload_time,photo_path) values(#{photo.userId},#{photo.type},#{photo.size},#{photo.uploadTime,jdbcType=TIMESTAMP},#{photo.photoPath})")
    @Options(useGeneratedKeys=true,keyProperty="photoId")
    public void insertPhoto(@Param("photo") Photo photo);

    //删除照片
    @Update("update photo set del_state=0 where photo_id=#{photoId},user_id=#{userId}")
    public boolean deletePhoto(@Param("photoId") int photoId,@Param("userId") int userId);

    //封禁照片
    @Update("update photo set ban_state=0 where photo_id=#{photoId},user_id=#{userId}")
    public int banPhoto(@Param("photoId") int photoId,@Param("userId") int userId);


}
