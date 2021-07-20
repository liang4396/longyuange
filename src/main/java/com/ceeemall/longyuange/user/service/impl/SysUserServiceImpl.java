package com.ceeemall.longyuange.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ceeemall.longyuange.user.mapper.SysUserMapper;
import com.ceeemall.longyuange.user.pojo.entity.SysUser;
import com.ceeemall.longyuange.user.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author llp
 * @since 2021-07-20
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser selectUserByName(String name) {
        return this.baseMapper.selectByName(name);
    }

    @Override
    public Integer updateUserPassword(String name, String password) {

        return this.baseMapper.updatePasswordByName(name, password);
    }
}
