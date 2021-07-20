package com.ceeemall.longyuange.security.config;

import com.ceeemall.longyuange.security.custom.UserAuthenticationProvider;
import com.ceeemall.longyuange.security.handler.*;
import com.ceeemall.longyuange.security.kaptcha.KaptchaAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author llp
 * @date 2021/7/20 10:29
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启权限注解,默认是关闭的
public class SecurityConfig  extends WebSecurityConfigurerAdapter {


    /**
     * 自定义登录成功处理器
     */
    @Autowired
    private UserLoginSuccessHandler userLoginSuccessHandler;
    /**
     * 自定义登录失败处理器
     */
    @Autowired
    private UserLoginFailureHandler userLoginFailureHandler;
    /**
     * 自定义注销成功处理器
     */
    @Autowired
    private UserLogoutSuccessHandler userLogoutSuccessHandler;
    /**
     * 自定义暂无权限处理器
     */
    @Autowired
    private UserAuthAccessDeniedHandler userAuthAccessDeniedHandler;
    /**
     * 自定义未登录的处理器
     */
    @Autowired
    private UserAuthenticationEntryPointHandler userAuthenticationEntryPointHandler;
    /**
     * 自定义登录逻辑验证器
     */
    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;


    /**
     * 配置登陆验证逻辑
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userAuthenticationProvider);
    }

    /**
     * 配置security控制逻辑
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //不进行权限验证的请求或资源(从配置文件中读取)
                .antMatchers(MatchConfig.antMatchers.split(",")).permitAll()
                //其他需要通过登录后才可以访问
                .anyRequest().authenticated()
                .and()
                //配置未登录自定义处理类
                .httpBasic().authenticationEntryPoint(userAuthenticationEntryPointHandler)
                .and()
                .formLogin()//设置表单登录，后续可以在这里修改自定义登录页面
                //配置登录地址
                .loginPage("/login").loginProcessingUrl("/api/login").permitAll()
                //配置自定义成功处理器
                .successHandler(userLoginSuccessHandler)
                //配置自定义登录失败处理器
                .failureHandler(userLoginFailureHandler)
                .and()
                .logout()
                .logoutUrl("/login/userLogout")
                //配置自定义退出成功处理器
                .logoutSuccessHandler(userLogoutSuccessHandler)
                .and()
                //配置没有权限处理类
                .exceptionHandling().accessDeniedHandler(userAuthAccessDeniedHandler)
                .and()
                //开启跨域
                .cors()
                .and()
                //取消跨站请求伪造防护
                .csrf().disable();
        //禁用缓存
        http.headers().cacheControl();
        //在认证用户名之前认证验证码，如果验证码错误，将不执行用户名和密码的认证
        http.addFilterBefore(new KaptchaAuthenticationFilter("/api/login", "/login?error"), UsernamePasswordAuthenticationFilter.class);

    }


    /**
     * 密码加密方式
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
