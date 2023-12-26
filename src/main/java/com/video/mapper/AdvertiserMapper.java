package com.video.mapper;

import com.video.entity.Advertiser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdvertiserMapper {
    Advertiser getAdvertiser();

    int updateAdvertiser(@Param("advertisement_id") int advertisement_id, @Param("advertiser_id")int advertiser_id, @Param("money")int money,@Param("userId") int userId);
}
