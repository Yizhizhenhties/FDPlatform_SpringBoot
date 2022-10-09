package org.example.fdplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.fdplatform.mapper")
public class FDPlatformApplication
{
    public static void main(String[] args) {
        SpringApplication.run(FDPlatformApplication.class, args);
    }
}
