package com.example.ledger.dbservice;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ledger.model.enums.Status;
import com.example.ledger.user.datasource.entity.LedgerUser;
import com.example.ledger.user.datasource.repository.UserRepository;


@Service
public class UserDbService {

    @Autowired
    private UserRepository userRepository;


    public LedgerUser saveUser(LedgerUser user) {
        user.setCreateDtm(new Date());
        return userRepository.save(user);
    }


    public LedgerUser updateUser(Long userId, LedgerUser user) {
        LedgerUser existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
        existingUser.setUserName(user.getUserName());
        existingUser.setPassword(user.getPassword());
        existingUser.setStatus(user.getStatus());
        existingUser.setUpdatedBy(user.getUpdatedBy());
        existingUser.setUpdateDtm(new Date());
        return userRepository.save(existingUser);
    }


    public LedgerUser changeStatus(Long userId, String status) {
        LedgerUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
        try {
            user.setStatus(Status.valueOf(status.toUpperCase())); // Converts string to enum, ensuring the correct type
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status value provided.");
        }
        user.setUpdateDtm(new Date());
        return userRepository.save(user);
    }


    public List<LedgerUser> findAllUsers() {
        return userRepository.findAll();
    }


    public LedgerUser findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }
}
