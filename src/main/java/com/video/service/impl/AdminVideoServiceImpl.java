package com.video.service.impl;

import com.video.entity.*;
import com.video.entity.dto.VideoInfoDTO;
import com.video.mapper.TMessageMapper;
import com.video.mapper.TUserMapper;
import com.video.mapper.TVideoMapper;
import com.video.service.IAdminVideoService;
import com.video.service.IMsgTypeService;
import com.video.service.IStateService;
import com.video.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminVideoServiceImpl implements IAdminVideoService {

    @Autowired
    private TVideoMapper videoMapper;

    @Autowired
    private IMsgTypeService msgTypeMService;

    @Autowired
    private IStateService stateService;

    @Autowired
    private TMessageMapper msgMapper;

    @Autowired
    private TUserMapper userMapper;

    @Override
    public List<VideoInfoDTO> underVideoList() {
        return videoMapper.underVideoList();
    }

    @Override
    public List<TVideotype> getVideoTypeList() {
        return videoMapper.getVideoTypeList();
    }

    @Override
    public List<VideoInfoDTO> getiVideoByTitle(String videoTitle) {
        List<VideoInfoDTO> videoInfoDTOS = videoMapper.getiVideoByTitle(videoTitle);
        for (VideoInfoDTO videoInfoDTO : videoInfoDTOS) {
            TUser tUser = userMapper.selectById(videoInfoDTO.getUserId());
            if(null != tUser){
                videoInfoDTO.setUserName(tUser.getUserName());
            }
        }
        return videoInfoDTOS;
    }

    @Override
    public int editVideo(TVideo video) {
        return videoMapper.updateByPrimaryKeySelective(video);
    }

    @Override
    public List<TState> getStateList() {
        return videoMapper.getStateList();
    }

    @Override
    public VideoInfoDTO getVideoById(Long id) {
        return videoMapper.getVideoById(id);
    }

    @Override
    public int restoreVideo(Long id) {
        TMessage msg = new TMessage();
        TVideo video = videoMapper.selectOneByVideoId(id);
        msg.setMsgTitle("系统提醒");
        msg.setMsgContext("你好！你的主题为《" + video.getVideoTitle() + "》的视频审核通过");
        msg.setMsgType(msgTypeMService.findByTypeName("SystemMsg"));
        msg.setMsgState(stateService.getStateByStateId(6L));
        msg.setReceiveUser(video.getUser());
        msgMapper.insertMsg(msg);
        return videoMapper.restoreVideo(id);
    }

    @Override
    public int deleteVideo(Long id) {
        TMessage msg = new TMessage();
        TVideo video = videoMapper.selectOneByVideoId(id);
        msg.setMsgTitle("系统提醒");
        msg.setMsgContext("你好！你的主题为《" + video.getVideoTitle() + "》的视频审核失败");
        msg.setMsgType(msgTypeMService.findByTypeName("SystemMsg"));
        msg.setMsgState(stateService.getStateByStateId(6L));
        msg.setReceiveUser(video.getUser());
        msgMapper.insertMsg(msg);
        String videoPath = null;
        if(video.getVideoUrl() != null) {
            videoPath = "F:\\code\\design\\postDesign\\src\\main\\resources" + video.getVideoUrl();
        }
        String suffix = null;
        String imagePath = "";
        if(video.getThunmbnailUrl() != null) {
            suffix = video.getThunmbnailUrl().substring(21);
            imagePath = "F:\\code\\design\\postDesign\\src\\main\\resources\\static\\vimages\\" + suffix;
        }
        // 删除文件
        try {
            if(suffix != null && video.getVideoUrl() != null) {
                FileUtil fileUtil = new FileUtil();
                fileUtil.deleteAllFilesOfDir(fileUtil.getFile(videoPath));
                fileUtil.deleteAllFilesOfDir(fileUtil.getFile(imagePath));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return videoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int rdeleteVideo(Long id) {
        TMessage msg = new TMessage();
        TVideo video = videoMapper.selectOneByVideoId(id);
        msg.setMsgTitle("系统提醒");
        msg.setMsgContext("你好！你的主题为《" + video.getVideoTitle() + "》的视频因违反规定已被下架");
        msg.setMsgType(msgTypeMService.findByTypeName("SystemMsg"));
        msg.setMsgState(stateService.getStateByStateId(6L));
        msg.setReceiveUser(video.getUser());
        msgMapper.insertMsg(msg);
        return videoMapper.rdeleteVideo(id);
    }

    @Override
    public int upVideo(Long id) {
        TMessage msg = new TMessage();
        TVideo video = videoMapper.selectOneByVideoId(id);
        msg.setMsgTitle("系统提醒");
        msg.setMsgContext("你好！你的主题为《" + video.getVideoTitle() + "》的视频已上架");
        msg.setMsgType(msgTypeMService.findByTypeName("SystemMsg"));
        msg.setMsgState(stateService.getStateByStateId(6L));
        msg.setReceiveUser(video.getUser());
        msgMapper.insertMsg(msg);
        return videoMapper.upVideo(id);
    }

    @Override
    public List<VideoInfoDTO> videoList() {
        List<VideoInfoDTO> videoInfoDTOS = videoMapper.videoList();
        for (VideoInfoDTO videoInfoDTO : videoInfoDTOS) {
            TUser tUser = userMapper.selectById(videoInfoDTO.getUserId());
            if(null != tUser){
                videoInfoDTO.setUserName(tUser.getUserName());
            }
        }
        return videoInfoDTOS;
    }
}
