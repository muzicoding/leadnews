package com.muzicoding.behavior.service;

import com.muzicoding.model.behavior.dtos.LikesBehaviorDto;
import com.muzicoding.model.common.dtos.ResponseResult;

public interface ApLikesBehaviorService {

    /**
     * 存储喜欢数据
     * @param dto
     * @return
     */
    public ResponseResult like(LikesBehaviorDto dto);
}
