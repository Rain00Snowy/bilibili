package com.video.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
@Data
public class Comment {
    private Long id;

    private Long userId;

    private Long videoId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentDate;

    private String commentInfo;

    private Long parentId;

    private String userName;

    private String userUrl;

    private List<Comment> replies;

}