package org.yundaxue.workshop.acq.model.Mapper;

import org.apache.ibatis.annotations.*;

import org.yundaxue.workshop.acq.model.AlbumPhoto;

import java.util.List;


@Mapper
public interface AlbumPhotoMapper {
	//插入记录——峰
	@Insert("insert into album_photo (photo_id,album_id) " +
			"values(#{albumPhoto.photoId},#{albumPhoto.albumId})")
	public void insertAlbumPhoto(@Param("albumPhoto") AlbumPhoto albumPhoto);

	@Select("select photo_id from album_photo where album_id=#{albumId}")
	public List<String> showAlbum(@Param("albumId") int albumId);


	@Delete("delete from album_photo where album_photo_id=#{albumPhotoId} and photo_id=#{photoId} and album_id" +
			"=#{albumId}"
	)
	public void deleteAlbum(int albumPhotoId, int photoId, int albumId);

}

