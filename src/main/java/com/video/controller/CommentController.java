package com.video.controller;

import com.video.entity.Comment;
import com.video.entity.TUser;
import com.video.service.CommentService;
import com.video.util.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RequestMapping("comment")
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    //评论功能
    @RequestMapping(value = "sendComment")
    @ResponseBody
    public MsgResponse comment(HttpSession session, Comment comment){
        TUser user = (TUser)session.getAttribute("user");
//        TUser user = new TUser();
//        user.setUserId(15L);
        if(user!= null ){
            comment.setUserId(user.getUserId());
            comment.setCommentDate(new Date());
            commentService.insertComment(comment);
            return MsgResponse.success("评论成功", null);

        }
        return MsgResponse.fail("评论失败,未登录");

    }

    @RequestMapping(value = "/listCommentByVideoId/{videoId}")
    public List getList(@PathVariable Long videoId){
        List<Comment> list = commentService.getList(videoId);
        for (Comment comment : list) {
            List<Comment> child = commentService.selectByParentId(comment.getId());
            comment.setReplies(child);
        }
        return list;
    }
}
