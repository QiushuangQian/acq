package org.yundaxue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2019/3/30.
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String a = sdf.format(date);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date createTime = df.parse(a);
        System.exit(0);
    }
}
