package com.ceeemall.longyuange.security.handler;

import com.ceeemall.longyuange.common.ResultUtil;
import com.ceeemall.longyuange.security.entity.SelfUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author llp
 * 用户登录成功处理类
 * @date 2020/1/8 15:51
 */
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //获取用户信息
        SelfUserEntity selfUserEntity =  (SelfUserEntity) authentication.getPrincipal();
        // 封装返回参数
        Map<String,Object> resultData = new HashMap<>();
        resultData.put("isFirst",selfUserEntity.getFirstLogin());
        resultData.put("code","200");
        resultData.put("msg", "登录成功");
        ResultUtil.responseJson(httpServletResponse,resultData);


    }
}
