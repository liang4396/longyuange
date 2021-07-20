package com.ceeemall.longyuange.user.controller;

import com.ceeemall.longyuange.common.PassEncode;
import com.ceeemall.longyuange.common.result.R;
import com.ceeemall.longyuange.user.pojo.entity.SysUser;
import com.ceeemall.longyuange.user.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author llp
 * @date 2021/7/20 10:24
 */
@Controller
public class LoginController {

    @Resource
    SysUserService sysUserService;

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping("error/403")
    public String error403() {
        return "error/403";
    }

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping(value = "/resetPwd", method = RequestMethod.GET)
    public String resetPwd() {
        return "resetPwd";
    }


    /**
     * 修改用户密码
     * @return
     */
    @RequestMapping(value = "/login/resetPwd",method = RequestMethod.POST)
    @ResponseBody
    public R updatePassWord(String userName, String password, String passowrdTwo){

        //用户名判断是否存在
        SysUser sysUser = sysUserService.selectUserByName(userName);
        if(sysUser == null || sysUser.getId() == null) {
           return R.error().message("用户名不存在！");
        }
        //两次密码不一样
        if(!password.equals(passowrdTwo)){
            return R.error().message("两次密码输入不一致！");
        }
        String encode = PassEncode.BCrypt(password);//密码加密
        if(encode.equals(sysUser.getPassword())){
            return R.error().message("修改的密码不能和原密码一致！");
        }

        //修改密码
        try {
            sysUserService.updateUserPassword(userName, encode);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().message("密码修改失败！");
        }
    }

}
