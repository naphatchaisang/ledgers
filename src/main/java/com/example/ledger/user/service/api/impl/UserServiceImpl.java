package com.example.ledger.user.service.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ledger.dbservice.UserDbService;
import com.example.ledger.user.datasource.entity.LedgerUser;
import com.example.ledger.user.service.api.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDbService userDbService;

    @Override
    public LedgerUser addUser(LedgerUser user) {
        return userDbService.saveUser(user);
    }

    @Override
    public LedgerUser updateUser(Long userId, LedgerUser user) {
        return userDbService.updateUser(userId, user);
    }

    @Override
    public LedgerUser changeUserStatus(Long userId, String status) {
        return userDbService.changeStatus(userId, status);
    }

    @Override
    public List<LedgerUser> getAllUsers() {
        return userDbService.findAllUsers();
    }

    @Override
    public LedgerUser getUserById(Long userId) {
        return userDbService.findUserById(userId);
    }
}
