package com.video.service;



import com.video.entity.Advertiser;
import com.video.entity.TAdvertisement;
import com.video.entity.dto.AdDTO;

import java.util.List;

public interface IAdminAdService {
    List<AdDTO> adList();
    List<AdDTO> getAdByName(String adname);
    int editAd(TAdvertisement advertisement);
    AdDTO getAdById(int id);
    int addAd(TAdvertisement advertisement);
    TAdvertisement getAdByuserIdStateId(int userId);
    int deleteAdByAdId(int AdId);
    int restoreAdByAdvertisementId(int AdId);
    Advertiser getAdvertiser();
    int updateAdvertiser( int advertisement_id,int advertiser_id, int money,int userId);
    int deleteByPrimaryKey(Long userId);
}
