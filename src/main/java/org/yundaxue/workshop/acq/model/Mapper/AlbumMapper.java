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
	//创建相册
	@Insert("insert into album (album_name,user_id,create_time) " +
			"values(#{albumName},#{userId},#{createTime})")
	public boolean insertAlbum(@Param("albumName") String albumName, @Param("userId") int userId, @Param("createTime") Date createTime);

	//删除相册
	@Delete("delete from album where album_id=#{albumId}")
	public boolean deleteAlbum(@Param("albumId") int albumId);

	//修改相册名
	@Update("update album set album_name = #{albumName} where album_id = #{albumId}")
	public boolean updateAlbum(@Param("albumName") String albumName,@Param("albumId") int albumId);

	//查找相册
	@Select("select * from album where album_id=#{albumId}")
	public Album getAlbum(int albumId);

	//显示用户所有相册
	@Select("select album_name from album where user_id=#{userId}")
	public String  showAlbum(@Param("userId") int userId);

	//根据userId查找得到相册列表——峰
	@Select("select * from album where user_id=#{userId}")
	public List<Album> albumList(@Param("userId") int userId);
}
