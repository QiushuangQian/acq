package org.yundaxue.workshop.acq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.yundaxue.workshop.acq.model.Album;
import org.yundaxue.workshop.acq.model.Mapper.AlbumMapper;
import org.yundaxue.workshop.acq.service.AlbumService;

import java.util.Date;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    AlbumMapper albumMapper;

    @Override
    public boolean insertAlbum(Album album) throws Exception {
        album.setCreateTime(new Date());
        return albumMapper.insertAlbum(album.getAlbumName(),album.getUserId(),album.getCreateTime());

    }

    @Override
    public boolean deleteAlbum(int albumId) throws Exception {
        return albumMapper.deleteAlbum(albumId);
    }

    @Override
    public boolean updateAlbum(String albumName,int albumId) throws Exception {
        return albumMapper.updateAlbum(albumName,albumId);
    }


    @Override
    public Album getAlbum(int albumId) throws Exception {
        return albumMapper.getAlbum(albumId);
    }

    /*@Override
    public void showAlbum(int userId,String albumName) throws Exception{
        albumMapper.showAlbum(userId,albumName);
    }*/

    //——峰
    @Override
    public List<Album> albumList(int userId) throws Exception {
        return albumMapper.albumList(userId);
    }
}