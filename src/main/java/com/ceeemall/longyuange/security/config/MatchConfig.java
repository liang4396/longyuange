package com.ceeemall.longyuange.security.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author llp
 * @date 2021/7/20 10:37
 */
@Getter
@Component
@ConfigurationProperties(prefix = "match")
public class MatchConfig {

    /**
     * 不需要认证的接口
     */
    public static String antMatchers;

    public void setAntMatchers(String antMatchers) {
        this.antMatchers = antMatchers;
    }
}
