package org.yundaxue.workshop.acq.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by lenovo on 2019/3/24.
 */
@Controller
public class UploaderController {

    private static Logger logger = Logger.getLogger(UserController.class);
    private static final String excelPath = "E:\\";

    @RequestMapping(value = "/upload")
    public String upload(ModelMap model, HttpServletRequest request, HttpServletResponse response)throws Exception{
        return "/upload";
    }

    @RequestMapping(value = "/doUpload")
    @ResponseBody
    public String doUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        //保存到数据库的路径
        String sqlPath=null;
        //图片初始名
        String originalFilename = file.getOriginalFilename();
        //上传到的文件名
        String fileName = excelPath + File.separator + "test"+ File.separator +  originalFilename;

        //新建文件
        File desFile = new File(fileName);
        if (!desFile.exists()) {
            //文件不存在就创建
            desFile.getParentFile().mkdirs();
        }
        logger.info( "上传文件："+originalFilename);
        //创建文件是否成功标志
        boolean createFileFlag = createFile(file,desFile);
        if (createFileFlag) {   //创建文件成功
            //把图片的相对路径保存至数据库
            sqlPath = "/images/"+fileName;
            return "{\"state\":\"succ\",\"msg\":\"succ\"}";
        }else {
            return "{\"state\":\"fail\",\"msg\":\"fail\"}";
        }

    }

    private boolean createFile(MultipartFile file,File desFile) {
        boolean flag = false;
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(desFile));
            bos.write(file.getBytes());
            bos.flush();
            bos.close();
            flag = true;
        } catch (Exception e) {
            logger.error("【文件上传异常】：",e);
        }
        return flag;
    }



//    public Map<String, String> uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        Map<String, String> sMap = new HashMap<String, String>();
//        try {
//            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//            if (isMultipart) {
//                FileItemFactory factory = new DiskFileItemFactory();
//                ServletFileUpload upload = new ServletFileUpload(factory);
//
//                // 得到所有的提交的表单域，也就是formData
//                List<FileItem> fileItems = upload.parseRequest(request);
//
//                String id = "";
//                String fileName = "";
//                // 如果大于1说明是分片处理
//                int chunks = 1;
//                int chunk = 0;
//                long fileSize = 0;
//                long lastModiDate = 0;
//                String tableName = "";
//                FileItem tempFileItem = null;
//
//                for (FileItem fileItem : fileItems) {
//                    if (fileItem.getFieldName().equals("id")) {
//                        id = fileItem.getString();
//                    } else if (fileItem.getFieldName().equals("name")) {
//                        fileName = new String(fileItem.getString().getBytes("ISO-8859-1"), "UTF-8");
//                    } else if (fileItem.getFieldName().equals("chunks")) {
//                        chunks = NumberUtils.toInt(fileItem.getString());
//                    } else if (fileItem.getFieldName().equals("chunk")) {
//                        chunk = NumberUtils.toInt(fileItem.getString());
//                    } else if (fileItem.getFieldName().equals("multiFile")) {
//                        tempFileItem = fileItem;
//                    } else if (fileItem.getFieldName().equals("fileSize")) {
//                        fileSize = NumberUtils.toLong(fileItem.getString());
//                    } else if (fileItem.getFieldName().equals("lastModiDate")) {
//                        lastModiDate = NumberUtils.toLong(fileItem.getString());
//                    } else if (fileItem.getFieldName().equals("tableName")) {
//                        tableName = fileItem.getString();
//                    }
//                }
//
//                System.out.println(tableName);
//                String fileSysName = tempFileItem.getName();
//                String realname = lastModiDate+fileSysName.substring(fileSysName.lastIndexOf("."));//转化后的文件名
//                sMap.put("fileSysName", fileSysName);
//                sMap.put("realname", realname);
//                //真实上传的地址，是我底层的地址，这里就不多说了
//                String realPath = Constants.ROOT_FILE_PATH;
//                realPath = realPath + File.separator + tableName.toUpperCase() + File.separator;
//                String filePath = realPath;//文件上传路径
//
//                // 临时目录用来存放所有分片文件
//                String tempFileDir = filePath + id + "_" + fileSize;
//                File parentFileDir = new File(tempFileDir);
//                if (!parentFileDir.exists()) {
//                    parentFileDir.mkdirs();
//                }
//                // 分片处理时，前台会多次调用上传接口，每次都会上传文件的一部分到后台
//                File tempPartFile = new File(parentFileDir, realname + "_" + chunk + ".part");
//                FileUtils.copyInputStreamToFile(tempFileItem.getInputStream(), tempPartFile);
//
//                // 是否全部上传完成
//                // 所有分片都存在才说明整个文件上传完成
//                boolean uploadDone = true;
//                for (int i = 0; i < chunks; i++) {
//                    File partFile = new File(parentFileDir, realname + "_" + i + ".part");
//                    if (!partFile.exists()) {
//                        uploadDone = false;
//                    }
//                }
//                // 所有分片文件都上传完成
//                // 将所有分片文件合并到一个文件中
//                if (uploadDone) {
//                    // 得到 destTempFile 就是最终的文件
//                    File destTempFile = new File(filePath, realname);
//                    for (int i = 0; i < chunks; i++) {
//                        File partFile = new File(parentFileDir, realname + "_" + i + ".part");
//                        FileOutputStream destTempfos = new FileOutputStream(destTempFile, true);
//                        //遍历"所有分片文件"到"最终文件"中
//                        FileUtils.copyFile(partFile, destTempfos);
//                        destTempfos.close();
//                    }
//                    // 删除临时目录中的分片文件
//                    FileUtils.deleteDirectory(parentFileDir);
//                }
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return sMap;
//    }


//
//    public void upload(HttpServletRequest request, HttpServletResponse response){
//
//        System.out.println("收到图片!");
//        MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest)request;
//        Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象
//        String upaloadUrl = request.getSession().getServletContext().getRealPath("/")+"upload/";//得到当前工程路径拼接上文件名
//        File dir = new File(upaloadUrl);
//        System.out.println(upaloadUrl);
//        if(!dir.exists())//目录不存在则创建
//            dir.mkdirs();
//        for(MultipartFile file :files.values()){
//            counter++;
//            fileName=file.getOriginalFilename();
//            tagetFile = new File(upaloadUrl+fileName);//创建文件对象
//            if(!tagetFile.exists()){//文件名不存在 则新建文件，并将文件复制到新建文件中
//                try {
//                    tagetFile.createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    file.transferTo(tagetFile);
//                } catch (IllegalStateException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//        System.out.println("接收完毕");
//    }

}
