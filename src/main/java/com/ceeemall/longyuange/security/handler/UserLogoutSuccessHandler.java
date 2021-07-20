package com.ceeemall.longyuange.security.handler;

import com.ceeemall.longyuange.common.ResultUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author llp
 * 退出登录处理
 * @date 2020/1/10 13:20
 */
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Map<String,Object> map = new HashMap<>();
        map.put("code","200");
        map.put("msg","退出成功！");
        SecurityContextHolder.clearContext();
        ResultUtil.responseJson(httpServletResponse,ResultUtil.resultSuccess(map));
    }
}
