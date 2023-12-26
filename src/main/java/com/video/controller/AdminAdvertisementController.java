package com.video.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.entity.Advertiser;
import com.video.entity.TAdvertisement;
import com.video.entity.dto.AdDTO;
import com.video.entity.dto.AdImageDTO;
import com.video.service.IAdminAdService;
import com.video.util.Base64Util;
import com.video.util.MsgResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static com.video.util.Base64ImageDecoder.saveImage;


@RestController
@RequestMapping("advertisement")
public class AdminAdvertisementController {
    StringBuffer imageUrl=new StringBuffer();
    StringBuffer videoUrl=new StringBuffer();
    @Resource
    private IAdminAdService adminAdService;

    //管理端部分
    @GetMapping(value = "pageInfo")
    public PageInfo<AdDTO> getPageInfo(int pageNum, int pageSize) {
        //调用一个pageHelper的一个静态方法
        PageHelper.startPage(pageNum, pageSize);
        //用户记录
        List<AdDTO> adDTOS = adminAdService.adList();
        //获得 用户分页
        PageInfo<AdDTO> pageInfo = new PageInfo<AdDTO>(adDTOS);

        return pageInfo;
    }
    @GetMapping(value = "searchAd")
    public PageInfo<AdDTO> searchUser(int pageNum, int pageSize, String name) {
        //调用一个pageHelper的一个静态方法
        PageHelper.startPage(pageNum, pageSize);
        //用户记录
        List<AdDTO> adDTOS = adminAdService.getAdByName(name);
        //获得 用户分页
        return new PageInfo<AdDTO>(adDTOS);
    }

    @RequestMapping("editAd")
    public String editAd(@RequestBody TAdvertisement advertisement) {
        adminAdService.editAd(advertisement);
        return "success";
    }
    @RequestMapping("getAdById")
    public AdDTO getAdById(int id) {
        return adminAdService.getAdById(id);
    }
//获取广告图片
    @RequestMapping("getAdIcon")
    public MsgResponse getAdIcon(int id) throws IOException {
        try{
            System.out.println(id);
            String imgUrl=adminAdService.getAdById(id).getUrl();
            return MsgResponse.success("获取成功", Base64Util.imageToBase64(imgUrl));
        }catch (Exception e)
        {
            e.printStackTrace();
            return MsgResponse.fail("获取失败");
        }
    }

    //用户部分
    @RequestMapping("getAd")
    public MsgResponse getAd() {
        //获得广告位信息
        Advertiser advertiser;
        advertiser=adminAdService.getAdvertiser();
        System.out.println(advertiser);
        int adId=advertiser.getAdvertisement_id();
        System.out.println(adId);
        String imgUrl=adminAdService.getAdById(adId).getUrl();
         if(imgUrl!=null){
             return MsgResponse.success("获取成功", Base64Util.imageToBase64(imgUrl));
         }else {
             return MsgResponse.fail("获取失败");
         }
    }
    @RequestMapping("buyIdByUserId")
    public MsgResponse updateStateIdByUserId(int buyerId,int money){
            //获取现在广告主信息
            Advertiser advertiser=adminAdService.getAdvertiser();
            System.out.println(advertiser);
            TAdvertisement advertisement=adminAdService.getAdByuserIdStateId(advertiser.getAdvertiser_id());
            int AdId=advertisement.getAdvertisementId();
            //因为广告表添加后主键自动加1，为了匹配，这里得先加1
            AdId=AdId+1;
            //价高者得广告位
            if(advertiser.getMoney()<money){
                System.out.println(AdId);
                adminAdService.updateAdvertiser(AdId,buyerId,money,advertiser.getAdvertiser_id());
                advertiser=adminAdService.getAdvertiser();
                System.out.println(advertiser);
                return MsgResponse.success("购买成功",advertiser);
            }else {
                return MsgResponse.fail("钱不够，得加钱");
            }
    }
    @RequestMapping("uploadeAdImage")
    public String uploadeAdImage(@RequestBody AdImageDTO dto) throws IOException {
        System.out.println(dto.getImage());
        String path = "src/main/resources/static/advertisement/";
        String filename = saveImage(dto.getImage(), path);
        System.out.println(filename);
        dto.getAdvertisement().setUrl("/static/advertisement/" + filename);
        adminAdService.addAd(dto.getAdvertisement());
        //获取广告主表中的广告id
        int AdId=dto.getAdvertisement().getAdvertisementId();
        //按道理正在上架的广告id应该是广告表中的最新一个，所有为了将原本的广告下架，需要先将id-1,
        adminAdService.deleteAdByAdId(AdId-1);
        //将新的广告设为上架
        adminAdService.restoreAdByAdvertisementId(AdId);
        return "success";
    }
}
