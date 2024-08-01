package com.muzicoding.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muzicoding.model.common.dtos.ResponseResult;
import com.muzicoding.model.wemedia.dtos.SensitiveDto;
import com.muzicoding.model.wemedia.pojos.WmSensitive;

public interface WmSensitiveService extends IService<WmSensitive> {
    /**
     * 查询
     * @param dto
     * @return
     */
    ResponseResult list(SensitiveDto dto);

    /**
     * 新增
     * @param wmSensitive
     * @return
     */
    ResponseResult insert(WmSensitive wmSensitive);

    /**
     * 修改
     * @param wmSensitive
     * @return
     */
    ResponseResult update(WmSensitive wmSensitive);

    /**
     * 删除
     * @param id
     * @return
     */
    ResponseResult delete(Integer id);
}
