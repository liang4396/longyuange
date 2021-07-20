package com.ceeemall.longyuange.security.custom;

import com.ceeemall.longyuange.security.entity.SelfUserEntity;
import com.ceeemall.longyuange.security.service.SelfUserDetailsService;
import com.ceeemall.longyuange.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author llp
 * 定义自定义登录验证类
 * @date 2020/1/8 16:09
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private SelfUserDetailsService selfUserDetailsService;
    @Autowired
    private SysUserService sysUserService;


    /**
     * 自定义登录验证类
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取表单中的用户名
        String userName = (String) authentication.getPrincipal();
        //获取表单中的密码
        String passWord = (String) authentication.getCredentials();
        //获取用户信息
        SelfUserEntity selfUserEntity = selfUserDetailsService.loadUserByUsername(userName);
        //判断用户是否存在
        if (selfUserEntity == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        //判断密码是否正确
        if (!new BCryptPasswordEncoder().matches(passWord, selfUserEntity.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }

        //角色集合
/*        Set<GrantedAuthority> authorities = new HashSet<>();
        List<SysRoleEntity> sysRoleEntities = sysUserService.selectSysRoleByUserId(selfUserEntity.getUserId());
        for (SysRoleEntity sysRoleEntity : sysRoleEntities){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + sysRoleEntity.getRoleName()));
        }
        selfUserEntity.setAuthorities(authorities);*/
        //登录
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(selfUserEntity, passWord, null);
        return usernamePasswordAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
