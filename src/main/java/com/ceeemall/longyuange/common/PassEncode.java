package com.ceeemall.longyuange.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author llp
 * @date 2021/7/20 14:53
 */
public class PassEncode {

    public static String  BCrypt(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new  BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(password);
        return encode;
    }
}
