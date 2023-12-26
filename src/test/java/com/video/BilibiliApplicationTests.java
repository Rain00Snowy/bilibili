package com.video;

import com.video.controller.CommentController;
import com.video.controller.MessageController;
import com.video.controller.VideoController;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BilibiliApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    CommentController commentController;
    @Test
    public void testComment(){
        commentController.comment(15l,0l,32l,"真好听呢");
    }

    @Resource
    VideoController videoController;
    @Resource
    MessageController messageController;
    @Test
    public void TestMessage(){
        messageController.addLetter(15l,
                "系统消息",
                "你的视频《"+videoController.getVideoById(32l).getVideoTitle()+"》收到了一条评论:"+"真好听呢",
                videoController.getVideoById(32l).getUserId(),
                2l);
    }
}
