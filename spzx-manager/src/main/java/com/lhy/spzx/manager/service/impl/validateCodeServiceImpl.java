package com.lhy.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.alibaba.excel.util.StringUtils;
import com.lhy.spzx.common.exception.BusinessException;
import com.lhy.spzx.manager.service.validateCodeService;
import com.lhy.spzx.model.dto.system.LoginDto;
import com.lhy.spzx.model.vo.common.ResultCodeEnum;
import com.lhy.spzx.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class validateCodeServiceImpl implements validateCodeService {

    private RedisTemplate redisTemplate;
    @Override
    public ValidateCodeVo generateValidateCode() {

        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 2);
        String code = circleCaptcha.getCode();
        String imageBase64 = circleCaptcha.getImageBase64();
        String key = UUID.randomUUID().toString().replaceAll("_", "");

        redisTemplate.opsForValue().set("user:validate"+key,code,5, TimeUnit.MINUTES);

        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(key);
        validateCodeVo.setCodeValue("data:image/png;base64,"+imageBase64);

        return validateCodeVo;
    }

    @Override
    public boolean checkValidateCode(LoginDto logindto) {

        String captcha = logindto.getCaptcha();
        String key = logindto.getCodeKey();

        String captchaReal = (String) redisTemplate.opsForValue().get("user:validate" + key);

        if (StringUtils.isEmpty(captchaReal) || !captchaReal.equals(captcha)){
            throw new BusinessException(ResultCodeEnum.VALIDATECODE_ERROR);
        }else{
            redisTemplate.delete("user:validate" + key);
            return true;
        }
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
