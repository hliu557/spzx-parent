package com.lhy.spzx.manager.service;

import com.lhy.spzx.model.dto.system.LoginDto;
import com.lhy.spzx.model.vo.system.LoginVo;

public interface SysUserService {
    public LoginVo login(LoginDto loginDto);
}
