package org.yundaxue.workshop.acq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yundaxue.workshop.acq.dao.AlbumPhotoDao;
import org.yundaxue.workshop.acq.service.AlbumPhotoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
public class AlbumPhotoController {

    AlbumPhotoService albumPhotoService;


    @RequestMapping(value = "/albumPhoto/{albumPhotoId}")
    @ResponseBody
    public int photoDetail(@PathVariable int albumPhotoId,
                           HttpServletRequest request, HttpServletResponse response) throws Exception {
        AlbumPhotoDao albumPhotoDao = albumPhotoService.getAlbumPhoto(albumPhotoId);
        return albumPhotoDao.getAlbumPhotoId();
    }
}
