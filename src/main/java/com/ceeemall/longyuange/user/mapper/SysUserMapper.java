package com.ceeemall.longyuange.user.mapper;

import com.ceeemall.longyuange.user.pojo.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author llp
 * @since 2021-07-20
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectByName(String name);

    Integer updatePasswordByName(String name,String password);

}
