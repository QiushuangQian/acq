package org.yundaxue.workshop.acq.controller;

//import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.yundaxue.workshop.acq.config.Mytoken;
import org.yundaxue.workshop.acq.model.Album;
import org.yundaxue.workshop.acq.service.AlbumService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AlbumController {
    @Autowired
    AlbumService albumService;

    //用于打开createAlbum界面
    @RequestMapping(value = "/album/createAlbum")
    public String createAlbum(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        return "/createAlbum";
    }

    //创建时，进行代码插入
    @RequestMapping(value = "/album/doCreateAlbum")
    @ResponseBody
    public Map<String,Object> doCreateAlbum(Album album, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String,Object> r = new HashMap<>();
        album.setUserId(1);//session获取
        if (albumService.insertAlbum(album))
        {
            r.put(album.getAlbumName(),album);
        }
        return r;
    }
}
