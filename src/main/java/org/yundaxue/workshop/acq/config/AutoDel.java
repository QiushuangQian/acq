
package org.yundaxue.workshop.acq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yundaxue.workshop.acq.model.Photo;

import org.yundaxue.workshop.acq.service.PhotoService;
import org.yundaxue.workshop.acq.service.RecycleBinService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableScheduling
public class AutoDel {
    @Autowired
    PhotoService photoService;
    @Autowired
    RecycleBinService recycleBinService;


    @Scheduled(cron = "0 0 0 * * ?")
    public void autoDelPhoto() throws Exception{
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");//如2016-08-10 20:40
        //获取当前时间
        Date now = new Date();
        //获取照片删除时间

        for(Photo p : recycleBinService.getDel()) {

            String fromDate = simpleFormat.format(p.getDelTime());
            String toDate = simpleFormat.format(now);

            long from = simpleFormat.parse(fromDate).getTime();
            long to = simpleFormat.parse(toDate).getTime();

            //计算时间差
            int hours = (int) ((to - from) / (1000 * 60 * 60));


            //int minutes = (int) ((to - from) / (1000 * 60));

            //执行彻底删除操作
            if (hours >= 168) {
                photoService.completeDeletePhoto(p.getPhotoId(),p.getUserId());
            }
        }
    }
}

