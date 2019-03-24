package org.yundaxue.workshop.acq.model.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import org.yundaxue.workshop.acq.model.AlbumPhoto;


@Mapper
public interface AlbumPhotoMapper {
	@Insert("insert into album_photo (photo_id,album_id) " +
			"values(#{photoId},#{albumId})")
	public void insertAlbumPhoto(int photoId, int albumId);

	@Delete("delete from album_photo where album_photo_id=#{albumPhotoId} and photo_id=#{photoId} and album_id" +
			"=#{albumId}"
	)
	public void deleteAlbum(int albumPhotoId, int photoId, int albumId);

}

