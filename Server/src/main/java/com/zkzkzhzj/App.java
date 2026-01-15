package com.zkzkzhzj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
// JPA Auditing 기능 활성화
@EnableJpaAuditing
public class App 
{
    @RequestMapping("/api/hello")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);

        System.out.println("DB 연결 확인: " + context.getBean("dataSource").getClass().getName());
    }
}
