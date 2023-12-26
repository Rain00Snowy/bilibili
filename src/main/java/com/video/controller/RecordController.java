package com.video.controller;

import com.video.entity.TUser;
import com.video.entity.TVideo;
import com.video.service.IRecordService;
import com.video.util.MsgResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("record")
@RestController
public class RecordController {

    @Resource
    private IRecordService recordService;

    @RequestMapping("removeRecord")
    public MsgResponse removeRecord(@RequestParam Long userId,@RequestParam Long videoId) {
        if (userId != null) {
            return MsgResponse.success(recordService.deleteRecordById(userId,videoId), null);
        }
        return MsgResponse.fail("删除失败");
    }

    @RequestMapping("getAllRecordByUserId")
    public MsgResponse getAllRecordByUserId(@RequestParam Long userId) {

        if(userId != null) {
            List<TVideo> records = recordService.getAllRecordById(userId);
            if(records != null) {
                return MsgResponse.success("获取记录列表成功", records);
            }
            else {
                return MsgResponse.fail("获取记录列表失败");
            }
        }else {
            return MsgResponse.fail("当前用户不存在，请登录！");
        }
    }
}
