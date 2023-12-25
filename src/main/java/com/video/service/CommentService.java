package com.video.service;

import com.video.entity.Comment;

import java.util.List;

public interface CommentService {
    int deleteComment(Long id);

    int insertComment(Comment comment);

    List<Comment> getList(Long videoId);

    List<Comment> selectByParentId(Long parentId);
}
