package org.yundaxue.workshop.acq.album.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yundaxue.workshop.acq.album.bo.Album;
import org.yundaxue.workshop.acq.album.dao.AlbumDao;
import org.yundaxue.workshop.acq.album.service.AlbumService;

import java.util.List;

@Service("AlbumService")
public class AlbumServiceImpl implements AlbumService{

    AlbumDao albumDao;

    @Override
    public Album getAlbum(int albumId) throws Exception {
        return albumDao.getAlbum(albumId);
    }

    @Override
    public List<Album> listAlbum(String albumName) throws Exception {
        return albumDao.listAlbum(albumName);
    }

    @Override
    public void insertAlbum(Album album) throws Exception {

    }



}