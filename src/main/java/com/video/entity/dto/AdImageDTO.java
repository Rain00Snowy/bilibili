package com.video.entity.dto;

import com.video.entity.TAdvertisement;
import lombok.Data;

@Data
public class AdImageDTO {
    private TAdvertisement advertisement;
    private String image;
}
