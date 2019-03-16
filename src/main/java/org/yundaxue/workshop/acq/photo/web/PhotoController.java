package org.yundaxue.workshop.acq.photo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yundaxue.workshop.acq.photo.bo.Photo;
import org.yundaxue.workshop.acq.photo.service.PhotoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PhotoController {
    @Autowired
    PhotoService photoService;

    //
    @RequestMapping(value = "/photo/{photoId}")
    public String photoDetail(@PathVariable int photoId, ModelMap model,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
        Photo photo = photoService.getPhoto(photoId);
        model.put("p",photo);
        return "photo";
    }
}
