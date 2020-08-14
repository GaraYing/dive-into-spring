package com.gara.config;

import com.gara.bean.TestDao;
import com.gara.service.UserService;
import com.gara.service.impl.UserAccountServiceImpl;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@Configuration
public class BeanConfig {

    private final UserService userService;

    public BeanConfig(UserService userService) {
        this.userService = userService;
        System.out.println("beanConfig");
    }

    @PostConstruct
    private void postConstruct(){
        System.out.println("postConstruct");
    }

    @Bean({"test","test2"})
    @Scope("prototype")
    public TestDao testDao(){
        return new TestDao();
    }

    @Bean
    public UserAccountServiceImpl userService(){
        return new UserAccountServiceImpl();
    }
}
