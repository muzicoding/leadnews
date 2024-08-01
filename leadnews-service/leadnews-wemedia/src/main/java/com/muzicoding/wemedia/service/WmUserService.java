package com.muzicoding.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muzicoding.model.common.dtos.ResponseResult;
import com.muzicoding.model.wemedia.dtos.WmLoginDto;
import com.muzicoding.model.wemedia.pojos.WmUser;

public interface WmUserService extends IService<WmUser> {

    /**
     * 自媒体端登录
     * @param dto
     * @return
     */
    public ResponseResult login(WmLoginDto dto);

}