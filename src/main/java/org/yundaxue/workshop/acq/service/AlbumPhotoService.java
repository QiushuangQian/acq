package org.yundaxue.workshop.acq.service;

import org.yundaxue.workshop.acq.dao.AlbumPhotoDao;

import java.util.List;

public interface AlbumPhotoService {

    AlbumPhotoDao getAlbumPhoto(int albumPhotoId) throws Exception;

    List<AlbumPhotoDao> listAlbumPhoto(int photoId) throws Exception;

    void insertAlbumPhoto(AlbumPhotoDao albumPhotoDao) throws Exception;

}
