package com.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.video.entity.vo.CommentedStar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentedStarMapper extends BaseMapper<CommentedStar> {

    CommentedStar getCommentedStar(@Param("userId") Long userId, @Param("videoId") Long videoId);

    int insertOne(@Param("userId") Long userId, @Param("videoId") Long videoId, @Param("starNum") int starNum);
}
