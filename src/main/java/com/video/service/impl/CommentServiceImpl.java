package com.video.service.impl;

import com.video.entity.Comment;
import com.video.entity.TUser;
import com.video.mapper.CommentMapper;
import com.video.mapper.TUserMapper;
import com.video.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private TUserMapper userMapper;

    @Override
    public int insertComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }

    @Override
    public int deleteComment(Long id) {
        return 0;
    }

    @Override
    public List<Comment> getList(Long videoId) {
        List<Comment> comments = commentMapper.selectByVideoId(videoId);
        for (Comment comment : comments) {
            TUser tUser = userMapper.selectByPrimaryKey(comment.getUserId());
            if(null != tUser){
                comment.setUserName(tUser.getUserName());
                comment.setUserUrl(tUser.getIconUrl());
            }
        }
        return comments;
    }

    @Override
    public List<Comment> selectByParentId(Long parentId) {
        List<Comment> comments = commentMapper.selectByParentId(parentId);
        for (Comment comment : comments) {
            TUser tUser = userMapper.selectByPrimaryKey(comment.getUserId());
            if(null != tUser){
                comment.setUserName(tUser.getUserName());
                comment.setUserUrl(tUser.getIconUrl());
            }
        }
        return comments;
    }
}
