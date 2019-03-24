package org.yundaxue.workshop.acq.service;

import org.yundaxue.workshop.acq.model.Album;

import java.util.List;

public interface AlbumService {
    //插入相册
    boolean insertAlbum(Album album) throws Exception;

    //删除相册
    void deleteAlbum(int albumId) throws Exception;

    //通过id修改相册名
    void updateAlbum(int albumId, String albumName) throws Exception;

    //查找相册
    Album getAlbum(int albumId) throws Exception;
    //通过userId查找相册
    void searchAlbum(int userId,String albumName) throws Exception;
}

