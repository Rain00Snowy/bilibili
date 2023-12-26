package com.video.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_advertisement")
public class TAdvertisement {
    @TableId("advertisement_id")
    private int advertisementId;
    private String name;
    private int click;
    private int typeId;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;
    private int advertiserId;
    private int stateId;
    private String url;
}