package com.video.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class TUser {
    private Long userId;

    private String userName;

    private Integer userAge;

    private String userSex;

    private String password;

    @Override
    public String toString() {
        return "TUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", userSex='" + userSex + '\'' +
                ", password='" + password + '\'' +
                ", encryptedProblem='" + encryptedProblem + '\'' +
                ", fanNum=" + fanNum +
                ", userTel='" + userTel + '\'' +
                ", registerDate=" + registerDate +
                ", iconUrl='" + iconUrl + '\'' +
                ", stateId=" + stateId +
                '}';
    }

    private String encryptedProblem;


    private Integer fanNum;

    private String userTel;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerDate;

    private String iconUrl;

    private Integer stateId;

    private Integer level;


}
