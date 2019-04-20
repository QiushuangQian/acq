package org.yundaxue.workshop.acq.service;



import org.yundaxue.workshop.acq.model.AlbumPhoto;

import java.util.List;

public interface AlbumPhotoService {
    //增加记录——峰
    public void insertAlbumPhoto(AlbumPhoto albumPhoto) throws Exception;

    //删除记录
    public void deleteAlbumPhoto(int albumPhotoId,int photoId,int albumId) throws Exception;

    //查看记录
    AlbumPhoto getAlbumPhoto(int albumPhotoId, int photoId, int albumId) throws Exception;


    public List<String> showPhoto(int albumId,int userId) throws Exception;
}
