package com.gara.service.impl;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
public class UserServiceImpl extends AbstractUserService implements InitializingBean, DisposableBean, Lifecycle {

    @Override
    public void queryUser(Long uerId) {
        System.out.println("userServiceImpl#queryUser().uerId + " + uerId);
    }

    @Override
    void checkUser() {

    }


    @Override
    public void destroy() throws Exception {
        System.out.println("method is destroyed");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UserServiceImpl.afterPropertiesSet() method afterPropertiesSet()");
    }

    @Override
    public void start() {
        System.out.println("method start()");
    }

    @Override
    public void stop() {
        System.out.println("method stop()");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
