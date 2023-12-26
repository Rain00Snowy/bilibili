package com.video.controller;

import com.video.entity.Comment;
import com.video.entity.TUser;
import com.video.service.CommentService;
import com.video.util.MsgResponse;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RequestMapping("comment")
@RestController
public class CommentController {

    @Resource
    private CommentService commentService;

    //评论功能
    @RequestMapping(value = "sendComment")
    @ResponseBody
    public MsgResponse comment(@RequestParam Long userId,@RequestParam Long parentId,
                                @RequestParam Long videoId,@RequestParam String commentInfo){
//        TUser user = (TUser)session.getAttribute("user");
//        TUser user = new TUser();
//        user.setUserId(15L);
        try{
            Comment comment = new Comment();
            comment.setUserId(userId);
            comment.setVideoId(videoId);
            if(parentId!=null)
                comment.setParentId(parentId);
            comment.setCommentInfo(commentInfo);
            comment.setCommentDate(new Date());
            commentService.insertComment(comment);
            return MsgResponse.success("评论成功", null);

        }catch (Exception e){
            e.printStackTrace();
            return MsgResponse.fail("评论失败,未登录");
        }
    }

    @RequestMapping(value = "/listCommentByVideoId/{videoId}")
    public List<Comment> getList(@PathVariable Long videoId){
        List<Comment> list = commentService.getList(videoId);
        for (Comment comment : list) {
            List<Comment> child = commentService.selectByParentId(comment.getId());
            comment.setReplies(child);
        }
        return list;
    }
}
