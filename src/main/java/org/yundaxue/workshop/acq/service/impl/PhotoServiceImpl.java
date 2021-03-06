package org.yundaxue.workshop.acq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yundaxue.workshop.acq.model.Mapper.PhotoMapper;
import org.yundaxue.workshop.acq.model.Photo;
import org.yundaxue.workshop.acq.service.PhotoService;

import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2019/3/27.
 */
@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoMapper photoMapper;

    //插入photo表——峰
    @Override
    public void uploadPhoto(Photo photo) {
        photoMapper.insertPhoto(photo);
    }

    @Override
    public List<String> showPhoto(int userId) throws Exception {
        return photoMapper.showPhoto(userId);
    }

    @Override
    public List<String> getPhoto(int photoId) throws Exception {
        return photoMapper.getPhoto(photoId);
    }


    @Override
    public List<Photo> photoList(int albumId) throws Exception {
        return photoMapper.listPhoto(albumId);
    }

    @Override
    public List<Photo> getPhotoById(int photoId, int userId) throws Exception {
        return photoMapper.getPhotoById(photoId,userId);
    }

    @Override
    public boolean deletePhoto(int photoId,int userId,Date delTime) throws Exception {
        return photoMapper.deletePhoto(photoId,userId,delTime);
    }

    @Override
    public boolean completeDeletePhoto(int photoId, int userId) throws Exception {
        return photoMapper.completeDeletePhoto(photoId,userId);
    }

    @Override
    public List<Photo> photoList(int pagenum, int maxnum,int userId,int isDel) throws Exception {
        int index = (pagenum-1)*maxnum;
        return photoMapper.photoList(index, maxnum, userId,isDel);
    }

    @Override
    public List<Photo> ablumPhotoList(int pagenum, int maxnum, int userId, int isDel, int albumId) throws Exception {
        int index = (pagenum-1)*maxnum;
        return photoMapper.ablumPhotoList(index,maxnum,userId,isDel,albumId);
    }
    @Override
    public boolean restorePhoto(int restorePhotoId,int userId) throws Exception{

        return  photoMapper.restorePhoto(restorePhotoId,userId);
    }

    @Override
    public int getMaxPageNum(int maxnum, int userId, int isDel) throws Exception {
        int count = photoMapper.getCount(userId, isDel);
        double result = ((double)count)/((double)maxnum);
        int maxPageNum = (int)Math.ceil(result);//向上取整
        return maxPageNum;
    }

    @Override
    public int getMaxPageNum(int maxnum, int userId, int isDel, int albumId) throws Exception {
        int count = photoMapper.getAlbumCount(userId, isDel,albumId);
        double result = ((double)count)/((double)maxnum);
        int maxPageNum = (int)Math.ceil(result);//向上取整
        return maxPageNum;
    }
}
