package org.yundaxue.workshop.acq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yundaxue.workshop.acq.dao.AlbumDao;
import org.yundaxue.workshop.acq.service.AlbumService;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService{


    @Override
    public AlbumDao getAlbum(int albumId) throws Exception {
//        return albumDao.getAlbum(albumId);
        return null;
    }

    @Override
    public List<AlbumDao> listAlbum(String albumName) throws Exception {
//        return albumDao.listAlbum(albumName);
        return null;
    }

    @Override
    public void insertAlbum(AlbumDao albumDao) throws Exception {

    }



}