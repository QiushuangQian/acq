package org.yundaxue.workshop.acq.service.impl;

import org.springframework.stereotype.Service;
import org.yundaxue.workshop.acq.dao.AlbumDao;
import org.yundaxue.workshop.acq.service.AlbumService;

import java.util.List;

@Service("AlbumService")
public class AlbumServiceImpl implements AlbumService{

    AlbumDao albumDao;

    @Override
    public AlbumDao getAlbum(int albumId) throws Exception {
        return albumDao.getAlbum(albumId);
    }

    @Override
    public List<AlbumDao> listAlbum(String albumName) throws Exception {
        return albumDao.listAlbum(albumName);
    }

    @Override
    public void insertAlbum(AlbumDao albumDao) throws Exception {

    }



}