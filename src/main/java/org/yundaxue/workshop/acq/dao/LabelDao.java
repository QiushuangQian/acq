package org.yundaxue.workshop.acq.dao;

/**
 * Created by lenovo on 2019/3/17.
 */
public class LabelDao {
    private int labelId;
    private String labelName;//标签名

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }
}
