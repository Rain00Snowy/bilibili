package com.video.mapper;

import com.video.entity.TVote;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VoteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TVote vote);

    List<TVote> selectList(String voteName);

    TVote selectById(Long id);
}
