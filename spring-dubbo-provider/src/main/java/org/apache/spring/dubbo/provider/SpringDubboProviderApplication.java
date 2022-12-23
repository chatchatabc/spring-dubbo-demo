package org.apache.spring.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class SpringDubboProviderApplication  {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringDubboProviderApplication.class, args);
    }

}
