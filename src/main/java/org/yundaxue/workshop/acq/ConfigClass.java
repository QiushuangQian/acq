package org.yundaxue.workshop.acq;

/**
 * Created by lenovo on 2019/3/31.
 */
public class ConfigClass {
    public static final String ImgsSavePath = getPath();
    public static String getPath(){
        String classPath=ConfigClass.class.getResource("/").getPath();
        String path = classPath.replaceAll("%20"," ");
        return path+"static/";
    }
}
