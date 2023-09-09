package com.gara.config;

import com.gara.bean.TestBean;
import com.gara.bean.TestDao;
import com.gara.service.UserService;
import com.gara.service.impl.UserAccountServiceImpl;
import org.springframework.context.annotation.*;

import javax.annotation.PostConstruct;

@Configuration
@ImportResource("classpath:properties-config.xml")
@Import(AnotherBeanConfig.class)
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

    // @Bean
    // public JdbcTemplate jdbcTemplate(){
    //     return new JdbcTemplate();
    // }

    // @Bean
    // public InfoBeanInfact infoBeanInfact(){
    //     return new InfoBeanInfact();
    // }

    @Bean({"test","test2"})
    @Scope("prototype")
    public TestDao testDao(){
        return new TestDao();
    }

    @Bean
    public UserAccountServiceImpl userService(){
        return new UserAccountServiceImpl();
    }

    @Bean
    public TestBean.Test testBeanTest(){
        return new TestBean.Test();
    }
}
