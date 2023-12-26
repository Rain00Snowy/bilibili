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
    @Resource
    private MessageController messageController;
    @Resource
    private VideoController videoController;

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
            //评论的同时向被评论用户发送信息
            messageController.addLetter(userId,
                    "系统消息",
                    "你的视频《"+videoController.getVideoById(videoId).getVideoTitle()+"》收到了一条评论:"+commentInfo,
                    videoController.getVideoById(videoId).getUserId(),
                    2l);
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
