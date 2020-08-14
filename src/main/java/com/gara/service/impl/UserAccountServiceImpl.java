package com.gara.service.impl;

import org.springframework.stereotype.Service;

//@Service("userAccountServiceImpl")
public class UserAccountServiceImpl extends AbstractUserService {

    @Override
    public void queryAccount(Long accountId) {
        System.out.println("userAccountServiceImpl#queryUser().accountId + " + accountId);
    }

    @Override
    void checkUser() {

    }
}
