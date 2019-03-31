package org.yundaxue.workshop.acq.service;

import org.yundaxue.workshop.acq.model.Album;

import java.util.List;

public interface AlbumService {
    //插入相册
    public boolean insertAlbum(Album album) throws Exception;

    //删除相册
    public boolean deleteAlbum(int albumId) throws Exception;

    //通过id修改相册名
    public boolean updateAlbum(String albumName,int albumId) throws Exception;

    //查找相册
    public Album getAlbum(int albumId) throws Exception;
    //通过userId查找相册
    //public void showAlbum(int userId,String albumName) throws Exception;

    //根据userId得到相册列表——峰
    public List<Album> albumList(int userId) throws Exception;
}

