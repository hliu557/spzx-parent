package com.lhy.spzx.manager.controller;


import com.lhy.spzx.manager.service.SysUserService;
import com.lhy.spzx.manager.service.validateCodeService;
import com.lhy.spzx.model.dto.system.LoginDto;
import com.lhy.spzx.model.entity.system.SysUser;
import com.lhy.spzx.model.entity.user.UserInfo;
import com.lhy.spzx.model.vo.common.Result;
import com.lhy.spzx.model.vo.common.ResultCodeEnum;
import com.lhy.spzx.model.vo.system.LoginVo;
import com.lhy.spzx.model.vo.system.ValidateCodeVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户接口")
@RestController
@RequestMapping(value="/admin/system/index")
public class IndexController {

    private SysUserService sysUserService;
    private validateCodeService validateCodeService;

    @PostMapping("login")
    public Result login(@RequestBody LoginDto loginDto){

        validateCodeService.checkValidateCode(loginDto);

        LoginVo loginVo = sysUserService.login(loginDto);

        return Result.build(loginVo, ResultCodeEnum.SUCCESS);

    }
    @GetMapping(value = "/getUserInfo")
    public Result<UserInfo> getUserInfo(@RequestHeader(name = "token") String token){

        SysUser userInfoByToken = sysUserService.getUserInfoByToken(token);

        return Result.build(userInfoByToken,ResultCodeEnum.SUCCESS);
    }

    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode(){

        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();

        return Result.build(validateCodeVo,ResultCodeEnum.SUCCESS);
    }
    @GetMapping(value = "/menus")
    public Result ge(){

        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();

        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(name = "token") String token){

        sysUserService.logout(token);

        return Result.build(null,ResultCodeEnum.SUCCESS);

    }
    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }
    @Autowired
    public void setValidateCodeService(com.lhy.spzx.manager.service.validateCodeService validateCodeService) {
        this.validateCodeService = validateCodeService;
    }
}
