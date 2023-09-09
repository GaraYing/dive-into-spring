package com.gara.self.service;

import com.gara.self.spring.BeanPostProcessor;
import com.gara.self.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author GARA
 * @description TODO
 * @date 2023/8/19 10:57
 */
@Component
public class GaraBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("GaraBeanPostProcessor#postProcessAfterInitialization is invoked");


        if ("infoService".equals(beanName)){
            Proxy.newProxyInstance(GaraBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // 切面逻辑
                    System.out.println("切面逻辑");
                    // 执行普通的对象方法
                    method.invoke(bean, args);
                    return proxy;
                }
            });
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
