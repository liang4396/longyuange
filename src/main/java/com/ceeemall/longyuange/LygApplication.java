package com.ceeemall.longyuange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:mykaptcha.xml"})
public class LygApplication {

    public static void main(String[] args) {
        SpringApplication.run(LygApplication.class, args);
    }

}
