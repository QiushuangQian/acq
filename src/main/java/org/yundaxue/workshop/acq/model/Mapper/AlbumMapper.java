package org.yundaxue.workshop.acq.model.Mapper;


import org.apache.ibatis.annotations.*;
import org.yundaxue.workshop.acq.model.Album;

/**
 * Created by admin on 2019/3/17.
 */
@Mapper
public interface AlbumMapper {
	@Insert("insert into album (album_id) " +
			"values(#{albumId})")
	public void insertAlbum(int albumId);


	@Delete("delete from album where album_id=#{album_id}")
	public void deleteAlbum(int album_id);


	@Update("update album set album_name = #{albumName} where album_id = #{albumId}")
	public void updateAlbum(@Param("albumId") int albumId, @Param("albumName") String albumName);


	@Select("select * from album where album_id=#{albumId}")
	public Album getAlbum(int albumId);

	@Select("select * from album where user_id=#{userId}")
	public void searchAlbum(@Param("userId") int userId, @Param("albumName") String albumName);
}
