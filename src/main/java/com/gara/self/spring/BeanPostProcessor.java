package com.gara.self.spring;

import com.sun.istack.internal.Nullable;

/**
 * @author GARA
 * @description TODO
 * @date 2023/8/19 10:56
 */
public interface BeanPostProcessor {

    @Nullable
    default Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Nullable
    default Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
