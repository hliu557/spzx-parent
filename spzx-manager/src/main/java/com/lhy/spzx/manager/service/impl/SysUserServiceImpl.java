package com.lhy.spzx.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.lhy.spzx.common.exception.BusinessException;
import com.lhy.spzx.manager.mapper.SysUserMapper;
import com.lhy.spzx.manager.service.SysUserService;
import com.lhy.spzx.model.dto.system.LoginDto;
import com.lhy.spzx.model.entity.system.SysUser;
import com.lhy.spzx.model.vo.common.ResultCodeEnum;
import com.lhy.spzx.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {

    private SysUserMapper sysUserMapper;

    private RedisTemplate redisTemplate;
    @Override
    public LoginVo login(LoginDto loginDto) {

        String userName = loginDto.getUserName();
        SysUser sysUSer = sysUserMapper.selectUserInfoByUserName(userName);

        if(sysUSer == null){
            throw new BusinessException(ResultCodeEnum.LOGIN_ERROR);

        }

        String dataBase_password = sysUSer.getPassword();
        String input_password = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());
        if(!dataBase_password.equals(input_password)){
            throw new BusinessException(ResultCodeEnum.LOGIN_ERROR);
        }

        String token = UUID.randomUUID().toString().replaceAll("_", "");
        redisTemplate.opsForValue().set("user:login"+token,
                                    JSON.toJSONString(sysUSer),
                                    7,
                                    TimeUnit.DAYS
        );

        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);



        return loginVo;
    }

    @Override
    public SysUser getUserInfoByToken(String token) {

        String userJSON = (String) redisTemplate.opsForValue().get("user:login" + token);
        SysUser sysUser = JSON.parseObject(userJSON, SysUser.class);
        return sysUser;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @Autowired
    public void setSysUserMapper(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }
}
