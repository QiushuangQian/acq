package org.yundaxue.workshop.acq.dao.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.yundaxue.workshop.acq.dao.LabelPhotoDao;
import org.yundaxue.workshop.acq.dao.PhotoDao;
import org.yundaxue.workshop.acq.dao.LabelDao;

@Mapper
public interface LabelPhotoMapper {
    @Select("select * from photo where (select photo_id from label-photo where label_id=#{labelId})")
    public PhotoDao getPhoto(int labelId);

    @Select("select * from label where (select label_id from label_photo where photo_id=#{photoId})")
    public LabelDao getLabel(int photoId);

    @Insert("insert into label_photo (label_id,photo_id) values(#{labelId},#{photoId})")
    public LabelPhotoDao insertLabelPhoto(int labelId, int photoId);

    @Delete("delete from label_photo where label_photo_id=#{labelPhotoId}")
    public void deleteLabelPhoto(int labelPhotoId);
}
