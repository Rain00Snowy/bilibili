package com.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.video.entity.TState;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TStateMapper extends BaseMapper<TState> {
    int deleteByPrimaryKey(Long stateId);

    int insert(TState record);

    int insertSelective(TState record);

    TState selectByPrimaryKey(Long stateId);

    int updateByPrimaryKeySelective(TState record);

    int updateByPrimaryKey(TState record);
}
