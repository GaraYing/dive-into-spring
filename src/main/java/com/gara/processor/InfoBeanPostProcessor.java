package com.gara.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author GARA
 * @description TODO
 * @date 2023/9/2 17:12
 */
@Component
public class InfoBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("infoBeanInfact".equals(beanName)){
            System.out.println("初始化前: "+bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("infoBeanInfact".equals(beanName)){
            System.out.println("初始化后: "+bean);
        }
        return bean;
    }
}
