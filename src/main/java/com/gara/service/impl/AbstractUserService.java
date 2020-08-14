package com.gara.service.impl;

import com.gara.service.UserService;

abstract class AbstractUserService implements UserService {

    abstract void checkUser();

    @Override
    public void queryUser(Long uerId) {
    }

    @Override
    public void queryAccount(Long accountId) {

    }
}
