package com.muzicoding.wemedia.feign;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.muzicoding.apis.wemedia.IWemediaClient;
import com.muzicoding.model.common.dtos.ResponseResult;
import com.muzicoding.model.common.enums.AppHttpCodeEnum;
import com.muzicoding.model.wemedia.pojos.WmUser;
import com.muzicoding.wemedia.service.WmChannelService;
import com.muzicoding.wemedia.service.WmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WemediaClient implements IWemediaClient {

    @Autowired
    private WmUserService wmUserService;

    @Override
    @GetMapping("/api/v1/user/findByName/{name}")
    public WmUser findWmUserByName(@PathVariable("name") String name) {
        return wmUserService.getOne(Wrappers.<WmUser>lambdaQuery().eq(WmUser::getName, name));
    }

    @Override
    @PostMapping("/api/v1/wm_user/save")
    public ResponseResult saveWmUser(@RequestBody WmUser wmUser) {
        wmUserService.save(wmUser);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Autowired
    private WmChannelService wmChannelService;

    @GetMapping("/api/v1/channel/list")
    @Override
    public ResponseResult getChannels() {
        return wmChannelService.findAll();
    }
}
