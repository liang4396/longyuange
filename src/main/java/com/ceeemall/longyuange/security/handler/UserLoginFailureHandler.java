package com.ceeemall.longyuange.security.handler;

import com.ceeemall.longyuange.common.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author llp
 * @date 2020/1/8 15:37
 */
@Slf4j
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        // 这些对于操作的处理类可以根据不同异常进行不同处理
        if (e instanceof UsernameNotFoundException){
            log.info("【登录失败】"+e.getMessage());
            ResultUtil.responseJson(httpServletResponse,ResultUtil.resultCode(500,"用户名不存在"));
        }
        if (e instanceof LockedException){
            log.info("【登录失败】"+e.getMessage());
            ResultUtil.responseJson(httpServletResponse,ResultUtil.resultCode(500,"用户被冻结"));
        }
        if (e instanceof BadCredentialsException){
            log.info("【登录失败】"+e.getMessage());
            ResultUtil.responseJson(httpServletResponse, ResultUtil.resultCode(500,"用户名密码不正确"));
        }
        ResultUtil.responseJson(httpServletResponse,ResultUtil.resultCode(500,"登录失败"));

    }
}
