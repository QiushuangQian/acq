package org.yundaxue.workshop.acq.album.service;

import org.yundaxue.workshop.acq.album.bo.Album;

import java.util.List;

    public interface AlbumService {
         Album getAlbum(int albumId) throws Exception;
        List<Album> listAlbum(String albumName) throws Exception;
        void insertAlbum(Album album) throws Exception;
    }


