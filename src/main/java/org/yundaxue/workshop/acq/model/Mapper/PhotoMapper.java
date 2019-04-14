package org.yundaxue.workshop.acq.model.Mapper;

import org.apache.ibatis.annotations.*;
import org.yundaxue.workshop.acq.model.Photo;

import java.util.List;

@Mapper
public interface PhotoMapper {

    //得到用户所有照片
    @Select("select thumbnail_path from photo where user_id=#{userId}")
    public List<String> showPhoto(@Param("userId") int userId);

    @Select("select * from photo where user_id=#{userId}")
    public List<Photo> listPhoto(@Param("userId") int userId);
    //通过照片Id得到照片对象
    @Select("select * from photo where user_id=#{userId} and photo_id=#{photoId}")
    public List<Photo> getPhotoById(@Param("photoId") int photoId,@Param("userId") int userId);

    //分页得到照片
//    @Select("select * from photo where user_id=#{userId} limit (#{pagenum}-1)*#{maxnum},#{maxnum}")
    @Select("select * from photo where user_id=#{userId} limit #{index},#{maxnum}")
    public List<Photo> photoList(@Param("index") int index, @Param("maxnum") int maxnum,@Param("userId") int userId);

    //显示照片
    @Select("select photo_path from photo where photo_id=#{photoId}")
    public List<String> getPhoto(@Param("photoId") int photoId);

    //插入照片，返回photoId——峰
    @Insert("insert into photo(user_id,photo_type,size,upload_time,photo_path,thumbnail_path) values(#{photo.userId},#{photo.type},#{photo.size},#{photo.uploadTime,jdbcType=TIMESTAMP},#{photo.photoPath},#{photo.thumbnailPath})")
    @Options(useGeneratedKeys=true,keyProperty="photoId")
    public void insertPhoto(@Param("photo") Photo photo);

    //照片列表删除照片，即修改照片删除状态
    @Update("update photo set del_state=0 where photo_id=#{photoId} and user_id=#{userId}")
    public boolean deletePhoto(@Param("photoId") int photoId,@Param("userId") int userId);

    //回收站完全删除照片
    @Delete("delete from photo where photo_id=#{photoId} and user_id=#{userId}")
    public boolean completeDeletePhoto(@Param("photoId") int photoId,@Param("userId") int userId);
    //恢复照片
    @Update("update photo set del_state=1 where photo_id=#{photoId} and user_id=#{userId}")
    public boolean restorePhoto(@Param("photoId") int photoId,@Param("userId") int userId);



}
