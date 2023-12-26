package com.video.service;

import com.video.entity.TFocus;

import java.util.List;

public interface IFocusService {
    String focusVerify(Long userId, Long focusedIdLong);

    String addFocused(Long userId, Long focusedIdLong);

    List<Long> getUserFocusList(Long userId);

    List<Long> getUserFansList(Long userId);

    Boolean isFocused(Long userId,Long focusedId);

}
