package com.gara.self;


import com.gara.self.service.OrderService;
import com.gara.self.service.UserService;
import com.gara.self.spring.GaraApplicationContext;

/**
 * @author GARA
 * @description TODO
 * @date 2023/8/6 15:45
 */
public class GarApplicationContextMain {

    public static void main(String[] args) {
        GaraApplicationContext applicationContext = new GaraApplicationContext(AppConfig.class);

        UserService userService = (UserService) applicationContext.getBean("userService");
        UserService userService1 = (UserService) applicationContext.getBean("userService");
        UserService userService2 = (UserService) applicationContext.getBean("userService");
        UserService userService3 = (UserService) applicationContext.getBean("userService");
        OrderService orderService = (OrderService) applicationContext.getBean("orderService");

        System.out.println(userService);
        System.out.println(userService1);
        System.out.println(userService2);
        System.out.println(userService3);
        System.out.println(orderService);
        userService.test();

    }
}
