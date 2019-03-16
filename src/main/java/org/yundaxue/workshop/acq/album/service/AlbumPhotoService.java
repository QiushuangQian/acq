package org.yundaxue.workshop.acq.album.service;

import org.yundaxue.workshop.acq.album.bo.AlbumPhoto;

import java.util.List;

public interface AlbumPhotoService {
    AlbumPhoto getAlbumPhoto(int albumPhotoId) throws Exception;
    List<AlbumPhoto> listAlbumPhoto(int photoId) throws Exception;
    void insertAlbumPhoto(AlbumPhoto albumPhoto) throws Exception;
}
