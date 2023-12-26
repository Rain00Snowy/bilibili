package com.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.video.entity.TAdvertisement;
import com.video.entity.dto.AdDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TAdvertisementMapper extends BaseMapper<TAdvertisementMapper> {
    List<AdDTO> adList();
    List<AdDTO> getAdByName(@Param("name") String name);
    AdDTO getAdById(int id);
    int updateByPrimaryKeySelective(TAdvertisement advertisement);
    int addAd(TAdvertisement advertisement);
    TAdvertisement getAdByuserIdStateId(int userId);
    int updateStateIdByUserId(int id);
    int deleteAdByAdvertisementId(int id);
    int restoreAdByAdvertisementId(int id);
    int deleteByPrimaryKey(Long userId);

}
