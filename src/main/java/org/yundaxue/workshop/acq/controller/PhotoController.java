package org.yundaxue.workshop.acq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yundaxue.workshop.acq.dao.PhotoDao;
import org.yundaxue.workshop.acq.service.PhotoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PhotoController {
    @Autowired
    PhotoService photoService;

    //
    @RequestMapping(value = "/photo/{photoId}")
    @ResponseBody
    public String show(@PathVariable int photoId, ModelMap model,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
        PhotoDao photoDao = photoService.getPhoto(photoId);
        model.put("p",photoDao);
        return "photo/show";
    }

    @RequestMapping(value = "/photo/insert")
    public String insert(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/photo/insert";
    }

    @RequestMapping(value = "/photo/doInsert")
    @ResponseBody
    public Map<String,Object> doInsert(PhotoDao photoDao, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //photo.setPhotoName("清明上河");
        //photo.setUserId(1L);

        int count = photoService.uploadPhoto(photoDao);

        Map<String,Object> result = new HashMap<String,Object>();
        result.put("code", 0);
        result.put("msg", "创建成功！");
        result.put("photoId", photoDao.getPhotoId());
        return result;
    }
}

