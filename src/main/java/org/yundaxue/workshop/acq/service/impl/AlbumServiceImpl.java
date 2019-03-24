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
    public void deleteAlbum(int albumId) throws Exception {
        albumMapper.deleteAlbum(albumId);
    }

    @Override
    public void updateAlbum(int albumId, String albumName) throws Exception {
        albumMapper.updateAlbum(albumId,albumName);
    }

    @Override
    public Album getAlbum(int albumId) throws Exception {
        return albumMapper.getAlbum(albumId);
    }

    @Override
    public void searchAlbum(int userId,String albumName) throws Exception{
        albumMapper.searchAlbum(userId,albumName);
    }
}