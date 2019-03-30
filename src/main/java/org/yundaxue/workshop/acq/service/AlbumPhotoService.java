package org.yundaxue.workshop.acq.service;



import org.yundaxue.workshop.acq.model.AlbumPhoto;

public interface AlbumPhotoService {
    //增加记录——峰
    void insertAlbumPhoto(AlbumPhoto albumPhoto) throws Exception;

    //删除记录
    void deleteAlbumPhoto(int albumPhotoId,int photoId,int albumId) throws Exception;
    //查看记录
    AlbumPhoto getAlbumPhoto(int albumPhotoId, int photoId, int albumId) throws Exception;

}
