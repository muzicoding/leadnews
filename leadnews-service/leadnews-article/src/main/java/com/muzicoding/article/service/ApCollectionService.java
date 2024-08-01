package com.muzicoding.article.service;

import com.muzicoding.model.article.dtos.CollectionBehaviorDto;
import com.muzicoding.model.common.dtos.ResponseResult;

public interface ApCollectionService {

    /**
     * 收藏
     * @param dto
     * @return
     */
    public ResponseResult collection(CollectionBehaviorDto dto);
}
