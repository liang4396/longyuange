package com.ceeemall.longyuange.security.service;

import com.ceeemall.longyuange.security.entity.SelfUserEntity;
import com.ceeemall.longyuange.user.pojo.entity.SysUser;
import com.ceeemall.longyuange.user.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author llp
 * @date 2020/1/9 16:11
 */
@Component
public class SelfUserDetailsService implements UserDetailsService {
    @Autowired
    SysUserService sysUserService;

    /**
     * 查询用户信息
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public SelfUserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUserEntity = sysUserService.selectUserByName(username);
        if(sysUserEntity != null){
            // 组装参数
            SelfUserEntity selfUserEntity = new SelfUserEntity();
            BeanUtils.copyProperties(sysUserEntity,selfUserEntity);
            return selfUserEntity;
        }
        return null;
    }
}
