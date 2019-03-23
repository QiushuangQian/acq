package org.yundaxue.workshop.acq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yundaxue.workshop.acq.model.Album;
import org.yundaxue.workshop.acq.model.AlbumPhoto;
import org.yundaxue.workshop.acq.model.User;
import org.yundaxue.workshop.acq.service.AlbumPhotoService;
import org.yundaxue.workshop.acq.service.AlbumService;
import org.yundaxue.workshop.acq.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 耿志强 on 2019/3/17.
 */

@RestController
public class DataController {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private UserService userService;

    //用户激活
    @RequestMapping(value = "/activeuser",method = RequestMethod.GET)
    public @ResponseBody String sendMail(String code){
        boolean b = userService.activeUser(code);
        if (b == true){
            return "激活成功,点击登录: http://localhost:8080/login";
        }else {
            return "激活失败,重新注册: http://localhost:8080/register";
        }
    }

    @PostMapping(value = "/registerpost")
    public boolean registerpost(User user){
        return userService.register(user);
    }




    //通过ID返回相册名
    @RequestMapping(value = "/album/{albumId}")
    public String photoDetail(@PathVariable int albumId,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        Album albumDao = albumService.getAlbum(albumId);
        return null;//albumDao.getAlbumName();
    }
    AlbumPhotoService albumPhotoService;



    @RequestMapping(value = "/albumPhoto/{albumPhotoId}/{photoId}/{albumId}")
    public int photoDetail(@PathVariable int albumPhotoId,@PathVariable int photoId,@PathVariable int albumId
            , HttpServletRequest request, HttpServletResponse response) throws Exception {
        AlbumPhoto albumPhotoDao = albumPhotoService.getAlbumPhoto(albumPhotoId,photoId,albumId);
        return 0;//albumPhotoDao.getAlbumPhotoId();
    }

}
