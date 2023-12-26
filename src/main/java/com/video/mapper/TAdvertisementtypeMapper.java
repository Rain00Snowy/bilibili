package com.video.mapper;


import com.video.entity.TAdvertisementtype;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TAdvertisementtypeMapper {
    TAdvertisementtype selectByPrimaryKey(int typeId);
}
