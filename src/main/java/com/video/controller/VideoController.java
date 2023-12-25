package com.video.controller;

import com.video.entity.TState;
import com.video.entity.TUser;
import com.video.entity.TVideo;
import com.video.entity.TVideotype;
import com.video.service.*;
import com.video.util.Base64Util;
import com.video.util.DateUtil;
import com.video.util.MsgResponse;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

// url : 127.0.0.1/video?vidoeId=1
@RequestMapping("video")
@RestController
public class VideoController {

    @Resource
    private IVideoService videoService;

    @Resource
    private IRecordService recordService;

    @Resource
    private IUserService userService;

    @Resource
    private IVideoTypeService videoTypeService;

    @Resource
    private IStateService stateService;

    @Resource
    private CommentService commentService;


    @RequestMapping("getVideoByType")
    @ResponseBody
    public List<TVideo> getVideoByType(Long videoTypeId) {
        return videoService.getVideoByVideoTypeId(videoTypeId);
    }

//获得日期最新视频
    @RequestMapping("getIndexLastVideo")
    @ResponseBody
    public List<TVideo> getIndexLastVideo() {
        List<TVideo> indexLastVideo = videoService.getIndexLastVideo();
        return indexLastVideo;
    }

    @RequestMapping("getAllVideo")
    @ResponseBody
    public List<TVideo> getAllVideo() {
        List<TVideo> indexLastVideo = videoService.getAllPassVideo();
        return indexLastVideo;
    }

    @RequestMapping("getVideoListByUserId")
    @ResponseBody
    public MsgResponse getVideoListByUserId(Long userId) {
        List<TVideo> videoList = videoService.getVideoListByUserId(userId);
        if (videoList != null) {
            return MsgResponse.success("success", videoList);
        }
        return MsgResponse.fail("fail");
    }

    @RequestMapping("search")
    public MsgResponse searchVideo(String videoTitle) {
        List<TVideo> videoList = videoService.queryByVideoTitle(videoTitle);
        if (videoList != null) {
            return MsgResponse.success("success", videoList);
        }
        return MsgResponse.fail("fail");
    }

    StringBuffer imageUrl=new StringBuffer();
    StringBuffer videoUrl=new StringBuffer();
            //提交视频
    @RequestMapping( "uAddVideo")
    @ResponseBody
    public String uAddVideo(HttpSession session, @RequestParam String videoTitle,
                            @RequestParam String videoInfo, @RequestParam String typeName) {

        try {
            TUser tUser = (TUser) session.getAttribute("user");
            TVideo video = new TVideo();
            video.setUser(tUser);
            TState state = stateService.getStateByStateId(5L);
            video.setVideoState(state);
            video.setVideoType(videoTypeService.getVideoTypeByTypeName(typeName));
            video.setVideoInfo(videoInfo);
            video.setVideoTitle(videoTitle);

            video.setVideoUrl(videoUrl.toString());
            video.setThunmbnailUrl(imageUrl.toString());
            videoService.addVideo(video);
            return "上传成功";
        } catch (Exception e) {
            System.out.println(e);
            return "上传失败";
        }
    }
    @RequestMapping("getVideoById")
    private MsgResponse getVideoById(Long videoId){
        try {
            TVideo video = videoService.getVideoByVideoId(videoId);

            return MsgResponse.success("获取成功",video);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResponse.fail("获取失败");
        }
    }

    /*---------上传视频--------*/
    @RequestMapping("uploadVideo")
    @ResponseBody
    public MsgResponse uploadVideo(@RequestParam MultipartFile file) {
        String workplace = System.getProperty("user.dir");
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        String path = "/static/video/" + DateUtil.currentDateFormatString() + suffix;
        File newFile = new File(workplace + "/src/main/resources" + path);
        try {
            file.transferTo(newFile);
            videoUrl=new StringBuffer("New Value");
            System.out.println(videoUrl);
            return MsgResponse.success("上传成功",path);
        } catch (IOException e) {
            e.printStackTrace();
            return MsgResponse.fail("上传失败");
        }
    }

    /*---------上传视频封面图片--------*/
    @ResponseBody
    @RequestMapping("uploadImage")
    public MsgResponse uploadImage(@RequestParam MultipartFile file) {
        String workplace = System.getProperty("user.dir");
        String fileName = file.getOriginalFilename();
        File newFile = new File(workplace + "/src/main/resources/static/vimages/" + fileName);
//        videoService.updateVideoImage("/static/vimages/" + fileName, videoId);
        try {
            //保存图片
            file.transferTo(newFile);
            imageUrl=new StringBuffer("/static/vimages/" + fileName);
            System.out.println(imageUrl);
            System.out.println("success");
            return MsgResponse.success("上传成功","/static/vimages/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return MsgResponse.fail("上传失败");
        }
    }

    @RequestMapping("getVideoImage")
    public MsgResponse getVideoImage(Long videoId) throws IOException {
        try{
            String imgUrl=videoService.getVideoByVideoId(videoId).getThunmbnailUrl();
            return MsgResponse.success("获取成功",Base64Util.imageToBase64(imgUrl));
        }catch (Exception e)
        {
            e.printStackTrace();
            return MsgResponse.fail("获取失败");
        }
    }
//获取推荐表中的视频
    @RequestMapping("getRecommendVideo")
    @ResponseBody
    public MsgResponse getRecommendVideo(HttpServletRequest request) {
        TUser user = (TUser) request.getSession().getAttribute("user");
        try{
            return MsgResponse.success("获取成功",videoService.getRecommendVideo(1, 6, user.getUserId()));
        }catch (Exception e)
        {
            e.printStackTrace();
            return MsgResponse.fail("获取失败");
        }
    }
//获取4个推荐视频
    @RequestMapping("getIndexRecommendVideo")
    @ResponseBody
    public MsgResponse getIndexRecommendVideo() {
        try{
            return MsgResponse.success("获取成功",videoService.getIndexRecommendVideo());
        }catch (Exception e)
        {
            e.printStackTrace();
            return MsgResponse.fail("获取失败");
        }
    }

    @RequestMapping("videoPlay")
    public MsgResponse videoPlay(Long videoId,HttpSession session) throws IOException {
//        TUser sUser = (TUser)session.getAttribute("user");
        TUser sUser = new TUser();
        sUser.setUserId(15L);
        if ( videoId != null && !videoId.equals("")) {
            TVideo video = videoService.getVideoByVideoId(videoId);
            if (video != null) {
                TUser user = userService.getUserByUserId(video.getUser().getUserId());
                TUser tmpUser = userService.getUserByUserId(sUser.getUserId());
                video.setUser(user);
                TVideotype videoType = videoTypeService.getVideoTypeByVideoTypeId(video.getVideoType().getVideotypeId());
                video.setVideoType(videoType);
//                session.setAttribute("curVideo", video);
                //判断会员等级和上传人员
                int videoLevel = video.getLevel();
                Long userId = video.getUserId();
                //不是上传人再判断等级限制
                if(!tmpUser.getUserId().equals(userId)){
                    if(tmpUser.getLevel() < videoLevel){
                        //等级限制不能看
                        return MsgResponse.fail("等级不够");

                    }
                }
                // 增加观看次数
                String result = videoService.addViewSum(videoId);
                // 添加观看记录
                recordService.addRecord(sUser.getUserId(), videoId);
                //由video的路径将视频转码成base64返回
                return MsgResponse.success("获取成功",Base64Util.videoToBase64(video.getVideoUrl()));

            }
        }
        return MsgResponse.fail("未找到视频");

    }

    @RequestMapping("thumbsUp")
    @ResponseBody
    public MsgResponse thumbsUp(HttpSession session, @RequestParam String videoId) {
        TUser user = (TUser) session.getAttribute("user");
        //调用点赞接口发送信息给视频上传者
        String msg = videoService.thumbsUp(user, Long.parseLong(videoId));
        if (msg.equals("点赞成功")) {
            return MsgResponse.success(msg, null);
        }
        return MsgResponse.fail(msg);
    }

    @RequestMapping("commented")
    @ResponseBody
    public MsgResponse commented(@RequestParam int starNum, @RequestParam Long userId, @RequestParam Long videoId) {
        return MsgResponse.success(videoService.addComment(starNum, userId, videoId), null);
    }

    @RequestMapping("updateLevel")
    @ResponseBody
    public String updateLevel(@RequestBody  TVideo tVideo) {
        videoService.updateLevel(tVideo.getLevel(),tVideo.getVideoId());
        return "200";
    }
}
