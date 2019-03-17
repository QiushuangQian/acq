package org.yundaxue.workshop.acq.model.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.yundaxue.workshop.acq.model.LabelPhoto;
import org.yundaxue.workshop.acq.model.Photo;
import org.yundaxue.workshop.acq.model.Label;

@Mapper
public interface LabelPhotoMapper {
    @Select("select * from photo where (select photo_id from label-photo where label_id=#{labelId})")
    public Photo getPhoto(int labelId);

    @Select("select * from label where (select label_id from label_photo where photo_id=#{photoId})")
    public Label getLabel(int photoId);

    @Insert("insert into label_photo (label_id,photo_id) values(#{labelId},#{photoId})")
    public LabelPhoto insertLabelPhoto(int labelId, int photoId);

    @Delete("delete from label_photo where label_photo_id=#{labelPhotoId}")
    public void deleteLabelPhoto(int labelPhotoId);
}
