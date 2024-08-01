package com.mucoding.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muzicoding.model.admin.dtos.AdUserDto;
import com.muzicoding.model.admin.pojos.AdUser;
import com.muzicoding.model.common.dtos.ResponseResult;

public interface AdUserService extends IService<AdUser> {

    /**
     * 登录
     * @param dto
     * @return
     */
    public ResponseResult login(AdUserDto dto);
}
