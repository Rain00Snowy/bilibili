package com.video.service;

import com.video.entity.TState;
import com.video.entity.TVideo;
import com.video.entity.TVideotype;
import com.video.entity.dto.VideoInfoDTO;

import java.util.List;

public interface IAdminVideoService {


    List<VideoInfoDTO> videoList();

    int rdeleteVideo(Long id);

    int upVideo(Long id);

    List<VideoInfoDTO> underVideoList();

    int deleteVideo(Long id);

    int restoreVideo(Long id);

    VideoInfoDTO getVideoById(Long id);

    List<TState> getStateList();

    List<TVideotype> getVideoTypeList();

    int editVideo(TVideo video);

    List<VideoInfoDTO> getiVideoByTitle(String videoTitle);
}
