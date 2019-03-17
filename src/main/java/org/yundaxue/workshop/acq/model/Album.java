package org.yundaxue.workshop.acq.model;

import lombok.Data;
import java.util.Date;

@Data
public class Album {
    private int albumId;
    private String albumName;
    private int userId;
    private Date createTime;
    private int albumState;
    private String albumType;
}
