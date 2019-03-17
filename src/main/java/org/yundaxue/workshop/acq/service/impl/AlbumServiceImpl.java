package org.yundaxue.workshop.acq.service.impl;

import org.springframework.stereotype.Service;
import org.yundaxue.workshop.acq.model.Album;
import org.yundaxue.workshop.acq.service.AlbumService;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService{


    @Override
    public Album getAlbum(int albumId) throws Exception {
//        return albumDao.getAlbum(albumId);
        return null;
    }

    @Override
    public List<Album> listAlbum(String albumName) throws Exception {
//        return albumDao.listAlbum(albumName);
        return null;
    }

    @Override
    public void insertAlbum(Album album) throws Exception {

    }



}