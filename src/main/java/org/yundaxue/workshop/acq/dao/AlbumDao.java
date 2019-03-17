package org.yundaxue.workshop.acq.dao;

import lombok.Data;
import java.util.Date;

@Data
public class AlbumDao {
    private int albumId;
    private String albumName;
    private int userId;
    private Date createTime;
    private int albumState;
    private String albumType;
}
