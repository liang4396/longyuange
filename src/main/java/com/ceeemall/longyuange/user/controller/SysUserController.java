package com.ceeemall.longyuange.user.controller;


import com.ceeemall.longyuange.common.ResultUtil;
import com.ceeemall.longyuange.user.pojo.entity.SysUser;
import com.ceeemall.longyuange.user.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author llp
 * @since 2021-07-20
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    SysUserService sysUserService;
    @GetMapping("info")
    public Map info(){
        SysUser admin = sysUserService.selectUserByName("admin");
        Map<String,Object> map = new HashMap<>();
        map.put("data",admin);
        return  ResultUtil.resultSuccess(map);
    }

}

