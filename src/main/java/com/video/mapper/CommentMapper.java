package com.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.video.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    int deleteComment(Long id);

    int insertComment(Comment comment);

    List<Comment> selectByVideoId(Long videoId);

    List<Comment> selectByParentId(Long parentId);
}
