package com.lhy.spzx.manager.controller;


import com.lhy.spzx.manager.service.SysUserService;
import com.lhy.spzx.model.dto.system.LoginDto;
import com.lhy.spzx.model.vo.common.Result;
import com.lhy.spzx.model.vo.common.ResultCodeEnum;
import com.lhy.spzx.model.vo.system.LoginVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户接口")
@RestController
@RequestMapping(value="/admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;

    @PostMapping("login")
    public Result login(@RequestBody LoginDto loginDto){
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }
}
