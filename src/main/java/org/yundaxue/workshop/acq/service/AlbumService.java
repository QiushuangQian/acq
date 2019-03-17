package org.yundaxue.workshop.acq.service;

import org.yundaxue.workshop.acq.dao.AlbumDao;

import java.util.List;

    public interface AlbumService {
         AlbumDao getAlbum(int albumId) throws Exception;
        List<AlbumDao> listAlbum(String albumName) throws Exception;
        void insertAlbum(AlbumDao albumDao) throws Exception;
    }


