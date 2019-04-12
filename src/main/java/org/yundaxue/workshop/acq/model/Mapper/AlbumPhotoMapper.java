package org.yundaxue.workshop.acq.model.Mapper;

import org.apache.ibatis.annotations.*;

import org.yundaxue.workshop.acq.model.AlbumPhoto;
import org.yundaxue.workshop.acq.model.Photo;

import java.util.List;


@Mapper
public interface AlbumPhotoMapper {
	//插入记录——峰
	@Insert("insert into album_photo (photo_id,album_id) " +
			"values(#{albumPhoto.photoId},#{albumPhoto.albumId})")
	public void insertAlbumPhoto(@Param("albumPhoto") AlbumPhoto albumPhoto);

	//显示相册内照片
	@Select("select photo_path from photo where photo_id=(select photo_id from album_photo where album_id=#{albumId}) and user_id=#{userId}")
	public List<String> showPhoto(@Param("albumId") int albumId,@Param("userId") int userId);


	@Delete("delete from album_photo where album_photo_id=#{albumPhotoId} and photo_id=#{photoId} and album_id" +
			"=#{albumId}"
	)
	public void deleteAlbum(int albumPhotoId, int photoId, int albumId);

	//获取照片列表
	@Select("select * from photo where photo_id=(select photo_id from album_photo where album_id=#{albumId}) and user_id=#{userId} limit #{index},#{maxnum}")
	public List<Photo> getPhotoList(@Param("index") int index, @Param("maxnum") int maxnum, @Param("albumId") int albumId,@Param("userId") int userId);


}

