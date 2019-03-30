package org.yundaxue.workshop.acq.model.Mapper;


import org.apache.ibatis.annotations.*;
import org.yundaxue.workshop.acq.model.Album;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2019/3/17.
 */
@Mapper
public interface AlbumMapper {
	@Insert("insert into album (album_name,user_id,create_time) " +
			"values(#{albumName},#{userId},#{createTime})")
	public boolean insertAlbum(@Param("albumName") String albumName, @Param("userId") int userId, @Param("createTime") Date createTime);


	@Delete("delete from album where album_id=#{albumId}")
	public boolean deleteAlbum(int albumId);


	@Update("update album set album_name = #{albumName} where album_id = #{albumId}")
	public void updateAlbum(@Param("albumId") int albumId, @Param("albumName") String albumName);


	@Select("select * from album where album_id=#{albumId}")
	public Album getAlbum(int albumId);

	@Select("select * from album where user_id=#{userId}")
	public void searchAlbum(@Param("userId") int userId, @Param("albumName") String albumName);


	//根据userId查找得到相册列表——峰
	@Select("select * from album where user_id=#{userId}")
	public List<Album> albumList(@Param("userId") int userId);
}
