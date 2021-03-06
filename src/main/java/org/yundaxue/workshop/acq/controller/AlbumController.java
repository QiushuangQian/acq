package org.yundaxue.workshop.acq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yundaxue.workshop.acq.model.Album;
import org.yundaxue.workshop.acq.model.User;
import org.yundaxue.workshop.acq.service.AlbumPhotoService;
import org.yundaxue.workshop.acq.service.AlbumService;
import org.yundaxue.workshop.acq.service.PhotoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AlbumController {
    private int userId;
    @Autowired
    AlbumService albumService;
    AlbumPhotoService albumPhotoService;
    PhotoService photoService;

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
        userId = ((User) request.getSession().getAttribute("USER")).getUserId();
        album.setUserId(userId);//session获取
        if (albumService.insertAlbum(album))
        {
            r.put(album.getAlbumName(),album);
        }
        return r;
    }

    //用于打开setAlbum界面
    @RequestMapping(value = "/album/setAlbum")
    public String setAlbum(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {

        userId = ((User) request.getSession().getAttribute("USER")).getUserId();
        //通过userId得到相册列表
        List<Album> AlbumList = albumService.albumList(userId);
        //将指定用户的相册列表通过model传递给jsp页面
        model.addAttribute("albumList",AlbumList);
        return "/setAlbum";
    }



    //删除数据
    @RequestMapping(value = "/album/doDeleteAlbum",method=RequestMethod.POST)
    @ResponseBody
    public boolean doDeleteAlbum(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //接收前端选中的相册id
        int selectedId = Integer.parseInt(request.getParameter("selected"));
        return albumService.deleteAlbum(selectedId);
    }

    //修改相册名
    @RequestMapping(value = "/album/changeAlbumName",method=RequestMethod.POST)
    @ResponseBody
    public boolean changeAlbumName(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //接收前端选中的相册id
        int selectedId = Integer.parseInt(request.getParameter("selected"));
        //接收前端输入的新相册名
        String newName = request.getParameter("newAlbumName");
        return albumService.updateAlbum(newName,selectedId);
    }

    @RequestMapping(value = "/album/albumPhotoShow")
    public String albumPhotoShow(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {

        userId = ((User) request.getSession().getAttribute("USER")).getUserId();
        //通过userId得到相册列表
        List<Album> AlbumList = albumService.albumList(userId);
        //将指定用户的相册列表通过model传递给jsp页面
        model.addAttribute("albumList",AlbumList);
        return "albumPhotoShow";
    }

    //显示该相册照片
    @RequestMapping(value = "/album/doAlbumPhotoShow")
    public String doAlbumPhotoShow(ModelMap model, HttpServletRequest request, HttpServletResponse response,RedirectAttributes attr) throws Exception {

        String selectedId=request.getParameter("selected");
        request.getSession().setAttribute("albumId",selectedId);
        return "redirect:/photo";
    }


}
