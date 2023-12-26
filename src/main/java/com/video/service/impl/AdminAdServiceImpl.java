package com.video.service.impl;


import com.video.entity.Advertiser;
import com.video.entity.TAdvertisement;
import com.video.entity.dto.AdDTO;
import com.video.mapper.AdvertiserMapper;
import com.video.mapper.TAdvertisementMapper;
import com.video.service.IAdminAdService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminAdServiceImpl implements IAdminAdService {
    @Resource
    AdvertiserMapper advertiserMapper;
    @Resource
    private TAdvertisementMapper advertisementMapper;
    @Override
    public List<AdDTO> adList(){
        return advertisementMapper.adList();
    }
    @Override
    public List<AdDTO> getAdByName(String adname){
        return advertisementMapper.getAdByName(adname);
    }
    @Override
    public int editAd(TAdvertisement advertisement){
        return advertisementMapper.updateByPrimaryKeySelective(advertisement);
    }
    @Override
    public AdDTO getAdById(int id){
        return advertisementMapper.getAdById(id);
    }
    @Override
    public int addAd(TAdvertisement advertisement){
        return advertisementMapper.addAd(advertisement);
    }
    @Override
    public TAdvertisement getAdByuserIdStateId(int userId){
        return advertisementMapper.getAdByuserIdStateId(userId);
    }
    @Override
    public int deleteAdByAdId(int AdId){
        return advertisementMapper.deleteAdByAdvertisementId(AdId);
    }
    @Override
    public int restoreAdByAdvertisementId(int AdId){
        return advertisementMapper.restoreAdByAdvertisementId(AdId);
    }
    @Override
    public Advertiser getAdvertiser(){
        return advertiserMapper.getAdvertiser();
    }
    @Override
    public int updateAdvertiser( int advertisement_id,int advertiser_id, int money,int userId){
        return advertiserMapper.updateAdvertiser(advertisement_id,advertiser_id,money,userId);
    }
    @Override
    public int deleteByPrimaryKey(Long userId){
        return advertisementMapper.deleteByPrimaryKey(userId);
    }
}

