package com.muzicoding.user.v1;

import com.muzicoding.model.common.dtos.ResponseResult;
import com.muzicoding.model.user.dtos.LoginDto;
import com.muzicoding.user.service.ApUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
@Api(value = "app端用户登入", tags = "app端用户登入")
public class ApUserLoginController {

    @Autowired
    private ApUserService apUserService;

    @ApiOperation("用户登录")
    @PostMapping("/login_auth")
    public ResponseResult login(@RequestBody LoginDto dto) {

        return apUserService.login(dto);
    }
}
