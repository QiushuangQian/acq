package org.yundaxue.workshop.acq.model.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.yundaxue.workshop.acq.model.Photo;

import java.util.List;

@Mapper
public interface PhotoMapper {

    //得到所有的照片
    @Select("select * from photo where user_id=#{userId}")
    public List<Photo> listPhoto(int userId);

    @Select("select * from photo where photo_id=#{photoId}")
    public Photo getPhoto(int photoId);

    @Insert("insert into photo(type,size,upload_time,user_id,photo_path,thumbnail_path,thumb,MD5,description) values(#{type},#{size},#{uploadTime},#{userId},#{photoPath},#{thumbnailPath},#{thumb},#{MD5},#{description})")
    public int uploadPhoto(Photo photo);

    @Update("update photo set del_state=0 where photo_id=#{photoId},user_id=#{userId}")
    public int deletePhoto(int photoId,int userId);

    @Update("update photo set ban_state=0 where photo_id=#{photoId},user_id=#{userId}")
    public int banPhoto(int photoId,int userId);


}
