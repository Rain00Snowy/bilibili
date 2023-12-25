package com.video.mapper;

import com.video.entity.TVoteLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VoteLogMapper {
    int insert(TVoteLog voteLog);

    List<TVoteLog> selectList(TVoteLog voteLog);

    int getCountByVoteId(Long voteId);
}
