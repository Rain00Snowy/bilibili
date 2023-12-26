package com.video.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.video.entity.TAdvertisementtype;
import com.video.entity.TState;
import com.video.entity.TUser;
import lombok.Data;

import java.util.Date;

@Data
public class AdDTO {
    private TState tState;
    private TUser user;
    private TAdvertisementtype advertisementtype;

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
