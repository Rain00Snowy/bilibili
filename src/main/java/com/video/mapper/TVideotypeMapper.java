package com.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.video.entity.TVideotype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TVideotypeMapper extends BaseMapper<TVideotype> {
    int deleteByPrimaryKey(Long videotypeId);

    int insert(TVideotype record);

    int insertSelective(TVideotype record);

    TVideotype selectByPrimaryKey(Long videotypeId);

    int updateByPrimaryKeySelective(TVideotype record);

    int updateByPrimaryKey(TVideotype record);

    List<TVideotype> getVideoTypeAll();

    TVideotype getVideoTypeByTypeName(@Param("typeName") String typeName);

    List<TVideotype> getVideoTypeByName(String typeName);

}
