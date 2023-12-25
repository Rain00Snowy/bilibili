package com.video.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class TVideo {
    @Override
    public String toString() {
        return "TVideo{" +
                "videoId=" + videoId +
                ", videoTitle='" + videoTitle + '\'' +
                ", videoInfo='" + videoInfo + '\'' +
                ", editDate=" + editDate +
                ", videoUrl='" + videoUrl + '\'' +
                ", thunmbnailUrl='" + thunmbnailUrl + '\'' +
                ", videoStateId=" + videoStateId +
                ", viewNum=" + viewNum +
                ", ppNum=" + ppNum +
                ", videoTypeId=" + videoTypeId +
                ", userId=" + userId +
                ", videoState=" + videoState +
                ", user=" + user +
                ", videoType=" + videoType +
                '}';
    }
    @TableId("video_id")
    private Long videoId;

    private String videoTitle;

    private String videoInfo;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date editDate;

    private String videoUrl;

    private String thunmbnailUrl;

    private Long videoStateId;

    private Integer viewNum;

    private Integer ppNum;

    private Long videoTypeId;

    private Long userId;

    private Integer level;

    private TState videoState;
    private TUser user;
    private TVideotype videoType;

}
