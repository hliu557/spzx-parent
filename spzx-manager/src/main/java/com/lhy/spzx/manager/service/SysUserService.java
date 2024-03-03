package com.lhy.spzx.manager.service;

import com.lhy.spzx.model.dto.system.LoginDto;
import com.lhy.spzx.model.dto.system.SysUserDto;
import com.lhy.spzx.model.entity.system.SysUser;
import com.lhy.spzx.model.vo.system.LoginVo;

public interface SysUserService {
    public LoginVo login(LoginDto loginDto);
    public SysUser getUserInfoByToken(String token);
    public void logout(String token);
}
