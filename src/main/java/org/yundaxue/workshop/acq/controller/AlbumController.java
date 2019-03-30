package org.yundaxue.workshop.acq.controller;

//import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.yundaxue.workshop.acq.config.Mytoken;
import org.yundaxue.workshop.acq.model.Album;
import org.yundaxue.workshop.acq.service.AlbumService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AlbumController {
    @Autowired
    AlbumService albumService;
    @Autowired
    HttpSession session;

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
        session.setAttribute("userId",album.getUserId());
        int userId = (int)request.getSession().getAttribute("userId");
        album.setUserId(userId);//session获取
        if (albumService.insertAlbum(album))
        {
            r.put(album.getAlbumName(),album);
        }
        return r;
    }

    //用于打开deleteAlbum界面
    @RequestMapping(value = "/album/deleteAlbum")
    public String deleteAlbum(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        int uid = 1;

        //通过userId得到相册列表
        List<Album> AlbumList = albumService.albumList(uid);
        //将指定用户的相册列表通过model传递给jsp页面
        model.addAttribute("albumList",AlbumList);
        return "/deleteAlbum";
    }



    //删除数据
    @RequestMapping(value = "/album/doDeleteAlbum",method= RequestMethod.POST)
    @ResponseBody
    public boolean doDeleteAlbum(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //接收前端选中的相册id
        int selectedId = Integer.parseInt(request.getParameter("selected"));
        return albumService.deleteAlbum(selectedId);
    }
}
