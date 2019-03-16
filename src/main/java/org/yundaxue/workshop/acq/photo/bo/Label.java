package org.yundaxue.workshop.acq.photo.bo;

/**
 * Created by lenovo on 2019/3/16.
 */
public class Label {
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
