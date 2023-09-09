package com.gara.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author GARA
 * @description TODO
 * @date 2023/9/2 17:09
 */
@Component("infoBeanInfact")
public class InfoBean implements FactoryBean {
    public InfoBean() {
    }

    @Override
    public Object getObject() throws Exception {
        return new InfoBeanInfact();
    }

    @Override
    public Class<?> getObjectType() {
        return InfoBeanInfact.class;
    }
}
