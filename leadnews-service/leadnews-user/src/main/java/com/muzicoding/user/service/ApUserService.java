package com.muzicoding.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muzicoding.model.common.dtos.ResponseResult;
import com.muzicoding.model.user.dtos.LoginDto;
import com.muzicoding.model.user.pojos.ApUser;

public interface ApUserService extends IService<ApUser> {

    /**
     * app端登入功能
     * @param dto
     * @return
     */
    public ResponseResult login(LoginDto dto);
}
