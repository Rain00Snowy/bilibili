package com.video.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.entity.TVideo;
import com.video.entity.TVideotype;
import com.video.entity.dto.VideoInfoDTO;
import com.video.service.IAdminVideoService;
import com.video.service.IStateService;
import com.video.service.IVideoService;
import com.video.service.IVideoTypeService;
import com.video.util.Base64Util;
import com.video.util.MsgResponse;
import com.video.util.VueUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminVideoController {
    @Resource
    private IAdminVideoService adminVideoService;
    @Resource
    private IStateService stateService;
    @Resource
    private IVideoService videoService;
    @Resource
    private IVideoTypeService videoTypeService;
    private static String aVideoTitle;
    //用来发消息的
    @Resource
    private MessageController messageController;
    @Resource
    private VideoController videoController;

    @RequestMapping("editVideo")
    public String editVideo(@RequestBody TVideo video) {
        adminVideoService.editVideo(video);
        return "success";
    }
    @RequestMapping("getVideoById")
    public VideoInfoDTO getVideoById(Long id) {
        return adminVideoService.getVideoById(id);
    }


    @RequestMapping("getVideoTypeList")
    public List<TVideotype> getVideoTypeList() {
        return adminVideoService.getVideoTypeList();
    }



    @RequestMapping("rdeleteVideo")
    public String rdeleteVideo(Long id) {
        adminVideoService.rdeleteVideo(id);
        return "success";
    }

    @GetMapping("upVideo")
    public String upVideo(Long id) {
        adminVideoService.upVideo(id);
        //给用户发个视频审核通知
        messageController.addLetter(videoController.getVideoById(id).getUserId(),
                "系统消息",
                "你的视频《"+videoController.getVideoById(id).getVideoTitle()+"》已通过审核。",
                videoController.getVideoById(id).getUserId(),
                2l);
        return "success";
    }


    @GetMapping(value = "searchVideo")
    public PageInfo<VideoInfoDTO> searchVideo(int pageNum, int pageSize, String videoName) {
        //调用一个pageHelper的一个静态方法
        PageHelper.startPage(pageNum, pageSize);
        //视频记录
        List<VideoInfoDTO> videoInfoDTOs = adminVideoService.getiVideoByTitle(videoName);
        //获得 视频分页
        PageInfo<VideoInfoDTO> vidoePageInfo = new PageInfo<VideoInfoDTO>(videoInfoDTOs);
        return vidoePageInfo;
    }


    /**
     * 获得分页对象, pageNum是当前页数, pageSize是分页大小
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "videoPageInfo")
    public PageInfo<VideoInfoDTO> getPageInfo(int pageNum, int pageSize) {
        //调用一个pageHelper的一个静态方法
        PageHelper.startPage(pageNum, pageSize);
        //视频记录
        List<VideoInfoDTO> videoInfoDTOs = adminVideoService.videoList();
        //获得 视频分页
        PageInfo<VideoInfoDTO> vidoePageInfo = new PageInfo<VideoInfoDTO>(videoInfoDTOs);
        return vidoePageInfo;
    }

    @GetMapping(value = "underVideoPageInfo")
    public PageInfo<VideoInfoDTO> underVideoPageInfo(int pageNum, int pageSize) {
        //调用一个pageHelper的一个静态方法
        PageHelper.startPage(pageNum, pageSize);
        //视频记录
        List<VideoInfoDTO> videoInfoDTOs = adminVideoService.underVideoList();
        //获得 视频分页
        PageInfo<VideoInfoDTO> vidoePageInfo = new PageInfo<VideoInfoDTO>(videoInfoDTOs);
        return vidoePageInfo;
    }


    @RequestMapping("updateVLevel")
    public String updateLevel(@RequestBody TVideo tVideo) {
        videoService.updateLevel(tVideo.getLevel(),tVideo.getVideoId());
        return "200";
    }
    @RequestMapping("getVIcon")
    public MsgResponse getVideoImage(Long id) {
        try{
            System.out.println(id);
            String imgUrl=videoService.getVideoByVideoId(id).getThunmbnailUrl();
            return MsgResponse.success("获取成功", Base64Util.imageToBase64(imgUrl));
        }catch (Exception e)
        {
            e.printStackTrace();
            return MsgResponse.fail("获取失败");
        }
    }


}
