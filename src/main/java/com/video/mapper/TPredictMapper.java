package com.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.video.entity.TPredict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TPredictMapper extends BaseMapper<TPredict> {
    int deleteByPrimaryKey(Long preId);

    int insert(TPredict record);

    int insertSelective(TPredict record);

    TPredict selectByPrimaryKey(Long preId);

    int updateByPrimaryKeySelective(TPredict record);

    int updateByPrimaryKey(TPredict record);
}
