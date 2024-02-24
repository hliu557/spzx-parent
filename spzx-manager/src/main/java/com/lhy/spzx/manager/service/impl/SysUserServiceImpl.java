package com.lhy.spzx.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.lhy.spzx.manager.mapper.SysUserMapper;
import com.lhy.spzx.manager.service.SysUserService;
import com.lhy.spzx.model.dto.system.LoginDto;
import com.lhy.spzx.model.entity.system.SysUser;
import com.lhy.spzx.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public LoginVo login(LoginDto loginDto) {
        String userName = loginDto.getUserName();
        SysUser sysUSer = sysUserMapper.selectUserInfoByUserName(userName);
        if(sysUSer == null){
            throw new RuntimeException("用户名不存在");

        }
        String dataBase_password = sysUSer.getPassword();
        String input_password = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());
        if(!dataBase_password.equals(input_password)){
            throw new RuntimeException("密码不正确");
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
}
