package com.muzicoding.user.service;


import com.muzicoding.model.common.dtos.ResponseResult;
import com.muzicoding.model.user.dtos.UserRelationDto;


public interface ApUserRelationService {
    /**
     * 用户关注/取消关注
     * @param dto
     * @return
     */
    public ResponseResult follow(UserRelationDto dto);
}