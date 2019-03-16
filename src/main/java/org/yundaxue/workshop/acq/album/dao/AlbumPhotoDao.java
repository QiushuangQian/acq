package org.yundaxue.workshop.acq.album.dao;

import org.apache.ibatis.annotations.Param;
import org.yundaxue.workshop.acq.album.bo.AlbumPhoto;

import java.util.List;

public interface AlbumPhotoDao {
    abstract AlbumPhoto getAlbumPhoto(@Param("albumPhotoId") int albumPhotoId) throws Exception;
    List<AlbumPhoto> listAlbumPhoto(@Param("photoId") int photoId) throws Exception;
    void insertAlbumPhoto(@Param("albumPhoto") AlbumPhoto albumPhoto) throws Exception;
}
