package org.yundaxue.workshop.acq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yundaxue.workshop.acq.dao.PhotoDao;
import org.yundaxue.workshop.acq.service.PhotoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PhotoController {
    @Autowired
    PhotoService photoService;

    //
    @RequestMapping(value = "/photo/{photoId}")
    @ResponseBody
    public String photoDetail(@PathVariable int photoId,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
        PhotoDao photoDao = photoService.getPhoto(photoId);
        return photoDao.getDescription();
    }
}

