package com.lhy.spzx.manager.service;

import com.lhy.spzx.model.dto.system.LoginDto;
import com.lhy.spzx.model.vo.system.ValidateCodeVo;

public interface validateCodeService {
    public ValidateCodeVo generateValidateCode();
    public boolean checkValidateCode(LoginDto logindto);
}
