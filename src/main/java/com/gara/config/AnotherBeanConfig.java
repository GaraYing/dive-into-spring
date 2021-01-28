package com.gara.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class AnotherBeanConfig implements BeanPostProcessor, InitializingBean {
    public AnotherBeanConfig() {
        System.out.println("AnotherBeanConfig *********");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("AnotherBeanConfig.postProcessBeforeInitialization() is called");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("AnotherBeanConfig.postProcessAfterInitialization() is called");
        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("AnotherBeanConfig.afterPropertiesSet() is called");
    }
}
