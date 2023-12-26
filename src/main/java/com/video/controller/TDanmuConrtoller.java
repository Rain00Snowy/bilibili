package com.video.controller;

import com.video.entity.TDanmu;
import com.video.service.IDanmuService;
import com.video.util.MsgResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("danmu")

public class TDanmuConrtoller {

    @Resource
    IDanmuService iDanmuService;
    @RequestMapping("sendDanmu")
    public MsgResponse sendDanmu(Long videoId,
                                 String userName,
                                 String danmuInfo){
        try {
            TDanmu tDanmu=new TDanmu();
            tDanmu.setDanmuInfo(danmuInfo);
            tDanmu.setVideoId(videoId);
            tDanmu.setUserName(userName);
            return MsgResponse.success("发送成功",iDanmuService.sendDanmu(tDanmu));
        }catch (Exception e){
            e.printStackTrace();
            return MsgResponse.fail("发送失败");
        }
    }
    @RequestMapping("getDanmuByVideoId")
    public MsgResponse getDanmuByVideoId(Long videoId)
    {
        try {
            return MsgResponse.success("获取成功",iDanmuService.getDanmuByVideoId(videoId));
        }catch (Exception e){
            e.printStackTrace();
            return MsgResponse.fail("获取失败");
        }
    }


}
