package org.yundaxue.workshop.acq.photo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yundaxue.workshop.acq.album.bo.Album;
import org.yundaxue.workshop.acq.album.service.AlbumService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AlbumController {
    AlbumService albumService;

    @RequestMapping(value = "/album/{albumId}")
    @ResponseBody
    public String photoDetail(@PathVariable int albumId,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        Album album = albumService.getAlbum(albumId);
        return album.getAlbumName();
    }
}
