package org.yundaxue.workshop.acq.service;



import org.yundaxue.workshop.acq.model.AlbumPhoto;
import org.yundaxue.workshop.acq.model.Photo;

import java.util.List;

public interface AlbumPhotoService {
    //增加记录——峰
    public void insertAlbumPhoto(AlbumPhoto albumPhoto) throws Exception;

    //删除记录
    public void deleteAlbumPhoto(int albumPhotoId,int photoId,int albumId) throws Exception;

    //查看记录
    AlbumPhoto getAlbumPhoto(int albumPhotoId, int photoId, int albumId) throws Exception;

    //获得相册照片列表
    public List<Photo> getPhotoList(int pagenum,int maxnum,int albumId,int userId) throws Exception;

    public List<String> showPhoto(int albumId,int userId) throws Exception;
}
