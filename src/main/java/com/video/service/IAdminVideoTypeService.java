package com.video.service;

import com.video.entity.TVideotype;

import java.util.List;

public interface IAdminVideoTypeService {

    List<TVideotype> categoryList();

    List<TVideotype> getVideoTypeByName(String typeName);

    boolean addVideoType(TVideotype videotype);

    TVideotype getCategoryById(Long id);

    boolean deleteCategoryById(Long id);

    boolean updateVideoType(TVideotype videotype);
}
