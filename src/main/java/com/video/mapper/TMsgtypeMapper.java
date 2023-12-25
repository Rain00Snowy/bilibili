package com.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.video.entity.TMsgtype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface TMsgtypeMapper extends BaseMapper<TMsgtype> {
    int deleteByPrimaryKey(Long msgtypeId);

    int insert(TMsgtype record);

    int insertSelective(TMsgtype record);

    TMsgtype selectByPrimaryKey(Long msgtypeId);

    int updateByPrimaryKeySelective(TMsgtype record);

    int updateByPrimaryKey(TMsgtype record);

    TMsgtype selectByTypeName(@Param("msgTypeName") String msgTypeName);
}
