package org.yundaxue.workshop.acq.util;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;


/**
 * @author 耿志强
 * 2018/10/20
 * 16:24
 */
@Component
public class Util {

    //将数字中的逗号去掉并转化为数字
    public static float change(String s){
        String str = s.replaceAll("(,|，)","");
        float  digital = Integer.parseInt(str);
        return digital;
    }

    //生成随机字符串
    public static String getUUID(){
        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++)
        {
            result+=random.nextInt(10);
        }
        return result;

//        return UUID.randomUUID().toString().replace("-","");
    }

    //获取当前date  sql
    public static Date getDate(){
        java.util.Date now = new java.util.Date();

        // 将java.util.sql 转为 java.sql.date
        Date sqlNow = new Date(now.getTime());
        return sqlNow;
    }

    //获取前一天的date
    public static Date getBeforeDate(int day){
        java.util.Date now = new java.util.Date();

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.add(calendar.DATE,day);//把日期往后增加一天.整数往后推,负数往前移动
        java.util.Date date = calendar.getTime(); //这个时间就是日期往后推一天的结果

        // 将java.util.sql 转为 java.sql.date
        Date beforeDate = new Date(date.getTime());
        return beforeDate;
    }
}
