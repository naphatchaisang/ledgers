package com.example.ledger.user.service.api;

import java.util.List;

import com.example.ledger.user.datasource.entity.LedgerUser;

public interface UserService {
    LedgerUser addUser(LedgerUser user);
    LedgerUser updateUser(Long userId, LedgerUser user);
    LedgerUser changeUserStatus(Long userId, String status);
    List<LedgerUser> getAllUsers();
    LedgerUser getUserById(Long userId);
}
