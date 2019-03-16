package org.yundaxue.workshop.acq.album.dao;


import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;
import org.yundaxue.workshop.acq.album.bo.Album;

import java.util.List;
@Mapper
public interface AlbumDao {
    abstract Album getAlbum(@Param("albumId") int albumId) throws Exception;
    List<Album> listAlbum(@Param("albumName") String albumName) throws Exception;
    void insertAlbum(@Param("album") Album album) throws Exception;
}
