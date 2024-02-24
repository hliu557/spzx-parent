package com.lhy.spzx.manager.mapper;

import com.lhy.spzx.model.entity.system.SysUser;
import com.lhy.spzx.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {
   List<SysUser> selectUsers();
   SysUser selectUserInfoByUserName(String userName);
}
