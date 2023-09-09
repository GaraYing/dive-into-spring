package com.gara.self.service;

import com.gara.self.spring.Autowired;
import com.gara.self.spring.Component;
import com.gara.self.spring.InitializingBean;

/**
 * @author GARA
 * @description TODO
 * @date 2023/8/6 15:45
 */

@Component("userService")
// @Scope("prototype")
public class UserService implements InitializingBean {

    @Autowired
    private OrderService orderService;

    public void test(){
        System.out.println("test");
        System.out.println("orderService = " + orderService);
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("userService#afterPropertiesSet is invoked");
    }
}
