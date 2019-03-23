package org.yundaxue.workshop.acq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.yundaxue.workshop.acq.model.AlbumPhoto;
import org.yundaxue.workshop.acq.model.Mapper.AlbumPhotoMapper;
import org.yundaxue.workshop.acq.service.AlbumPhotoService;

@Service
public class AlbumPhotoServiceImpl implements AlbumPhotoService {
	@Autowired
	AlbumPhotoMapper albumPhotoMapper;


	@Override
	public void insertAlbumPhoto( int photoId, int albumId) throws Exception {
		albumPhotoMapper.insertAlbumPhoto(photoId, albumId);
	}

	@Override
	public void deleteAlbumPhoto(int albumPhotoId, int photoId, int albumId) throws Exception {
		albumPhotoMapper.deleteAlbum(albumPhotoId, photoId, albumId);
	}
	@Override
	public AlbumPhoto getAlbumPhoto(int albumPhotoId, int photoId, int albumId) throws Exception {
		return null;
	}
}
