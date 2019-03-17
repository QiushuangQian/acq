package org.yundaxue.workshop.acq.dao;

import lombok.Data;

import java.util.Date;


@Data
public class PhotoDao {
    private int photoId;
    private int type;
    private long size;
    private Date uploadTime;
    private int userId;
    private String photoPath;
    private String thumbnailPath;
    private int delState;
    private String description;
    private int thumb;
    private String MD5;
    private Date delTime;
    private int BanState;
}
