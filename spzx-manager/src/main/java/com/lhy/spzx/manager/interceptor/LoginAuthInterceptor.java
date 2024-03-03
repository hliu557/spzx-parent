package com.lhy.spzx.manager.interceptor;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.lhy.spzx.AuthContextUtil;
import com.lhy.spzx.model.entity.system.SysUser;
import com.lhy.spzx.model.vo.common.Result;
import com.lhy.spzx.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Component
public class LoginAuthInterceptor  implements HandlerInterceptor{
    private RedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String method = request.getMethod();
        if("OPTIONS".equals(method)){
            return true;
        }

        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)){
            responseNoLoginInfo(response);
            return false;
        }

        String o = (String) redisTemplate.opsForValue().get("user:login" + token);
        if (StringUtils.isBlank(token)){
            responseNoLoginInfo(response);
            return false;
        }else {
            AuthContextUtil.set(JSON.parseObject(o, SysUser.class));
            redisTemplate.expire("user:login" + token,5, TimeUnit.MINUTES);
            return true;
        }





    }

    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuthContextUtil.remove();
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
