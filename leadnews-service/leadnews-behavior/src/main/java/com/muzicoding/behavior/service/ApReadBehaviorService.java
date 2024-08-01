package com.muzicoding.behavior.service;

import com.muzicoding.model.behavior.dtos.ReadBehaviorDto;
import com.muzicoding.model.common.dtos.ResponseResult;

public interface ApReadBehaviorService {

    /**
     * 保存阅读行为
     * @param dto
     * @return
     */
    public ResponseResult readBehavior(ReadBehaviorDto dto);
}
