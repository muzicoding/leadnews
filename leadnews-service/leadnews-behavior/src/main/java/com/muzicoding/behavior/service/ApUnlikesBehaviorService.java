package com.muzicoding.behavior.service;

import com.muzicoding.model.behavior.dtos.UnLikesBehaviorDto;
import com.muzicoding.model.common.dtos.ResponseResult;

/**
 * <p>
 * APP不喜欢行为表 服务类
 * </p>
 *
 * @author itmuzicoding
 */
public interface ApUnlikesBehaviorService {

    /**
     * 不喜欢
     * @param dto
     * @return
     */
    public ResponseResult unLike(UnLikesBehaviorDto dto);

}