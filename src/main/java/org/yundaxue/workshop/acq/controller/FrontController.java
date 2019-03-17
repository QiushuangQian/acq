package org.yundaxue.workshop.acq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yundaxue.workshop.acq.model.Album;
import org.yundaxue.workshop.acq.model.AlbumPhoto;
import org.yundaxue.workshop.acq.service.AlbumPhotoService;
import org.yundaxue.workshop.acq.service.AlbumService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 耿志强 on 2019/3/17.
 */
@Controller
public class FrontController {
	AlbumService albumService;

	@RequestMapping(value = "/album/{albumId}")
	@ResponseBody
	public String photoDetail(@PathVariable int albumId,
							  HttpServletRequest request, HttpServletResponse response) throws Exception {
		Album albumDao = albumService.getAlbum(albumId);
		return albumDao.getAlbumName();
	}
	AlbumPhotoService albumPhotoService;


	@RequestMapping(value = "/albumPhoto/{albumPhotoId}/{photoId}/{albumId}")
	@ResponseBody
	public int photoDetail(@PathVariable int albumPhotoId,@PathVariable int photoId,@PathVariable int albumId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AlbumPhoto albumPhotoDao = albumPhotoService.getAlbumPhoto(albumPhotoId,photoId,albumId);
		return albumPhotoDao.getAlbumPhotoId();
	}

}
