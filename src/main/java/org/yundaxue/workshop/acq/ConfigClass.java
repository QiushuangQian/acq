package org.yundaxue.workshop.acq;

import java.io.File;

/**
 * Created by lenovo on 2019/3/31.
 */
public class ConfigClass {
    public static final String ImgsSavePath = getPath();
    public static String getPath(){
        String classPath=ConfigClass.class.getResource("/").getPath();
        return classPath+ File.separator+"static"+File.separator;
    }
}
