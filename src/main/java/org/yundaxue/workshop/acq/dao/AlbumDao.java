package org.yundaxue.workshop.acq.dao;

import java.util.Date;

public class AlbumDao {
    private int albumId;
    private String albumName;
    private int userId;
    private Date createTime;
    private int albumState;
    private String albumType;

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getAlbumState() {
        return albumState;
    }

    public void setAlbumState(int albumState) {
        this.albumState = albumState;
    }

    public String getAlbumType() {
        return albumType;
    }

    public void setAlbumType(String albumType) {
        this.albumType = albumType;
    }
}
