package com.ceeemall.longyuange.security.kaptcha;

import com.google.code.kaptcha.Constants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录时验证码拦截器  用于判断验证码是否正确
 */
public class KaptchaAuthenticationFilter  extends AbstractAuthenticationProcessingFilter {

    private String servletPath;

    public KaptchaAuthenticationFilter(String servletPath, String failureUrl) {
        super(servletPath);
        this.servletPath = servletPath;
        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(failureUrl));
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        //获取请求是否为Post
        if ("POST".equalsIgnoreCase(req.getMethod()) && servletPath.equals(req.getServletPath())) {
            //在Session中获取 Kaptcha生成的验证码
            String expect = (String) req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if (expect != null && !expect.equalsIgnoreCase(req.getParameter("kaptcha"))) {
               /* unsuccessfulAuthentication(req, res, new InsufficientAuthenticationException("输入的验证码不正确"));*/
                //输入验证码不正确则返回错误信息
                res.setStatus(HttpServletResponse.SC_OK);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.getWriter().println("{\"exceptionId\":\"0\",\"code\":\"202\"}");
            return;
            }
        }
        chain.doFilter(request, response);
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        return null;
    }
}
