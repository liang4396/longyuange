package com.ceeemall.longyuange.user.service;

import com.ceeemall.longyuange.user.pojo.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author llp
 * @since 2021-07-20
 */
public interface SysUserService extends IService<SysUser> {

    SysUser selectUserByName(String name);

    Integer updateUserPassword(String name,String password);

}
