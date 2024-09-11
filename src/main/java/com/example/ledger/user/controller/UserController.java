package com.example.ledger.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ledger.constants.Constants.PathUrl;
import static com.example.ledger.constants.Constants.PathUrl.USER_BASE_URL;
import com.example.ledger.dto.response.UserResp;
import com.example.ledger.model.mapper.UserMapper;
import com.example.ledger.msg.MsgUserReq;
import com.example.ledger.user.service.api.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(USER_BASE_URL)
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public UserResp addUser(@Valid @RequestBody MsgUserReq request) {
        validate(request);
        var user = UserMapper.toEntity(request.getRequestBody());
        var response = UserMapper.toResponseDto(userService.addUser(user));
        logResponse(response);
        return response;
    }

    @PutMapping(value=PathUrl.GET_USER_ID)
    public UserResp updateUser(@PathVariable Long userId, @Valid @RequestBody MsgUserReq request) {
        validate(request);
        var user = UserMapper.toEntity(request.getRequestBody());
        var response = UserMapper.toResponseDto(userService.updateUser(userId, user));
        logResponse(response);
        return response;
    }

    @PatchMapping(value=PathUrl.GET_USER_STATUS)
    public UserResp changeUserStatus(@PathVariable Long userId, @RequestParam String status) {
        validateStatusChange(status);
        var response = UserMapper.toResponseDto(userService.changeUserStatus(userId, status));
        logResponse(response);
        return response;
    }

    @GetMapping
    public List<UserResp> getAllUsers() {
        validateGetAllUsers();
        var response = userService.getAllUsers().stream()
                .map(UserMapper::toResponseDto)
                .collect(Collectors.toList());
        logger.info("Fetched users count: {}", response.size());
        return response;
    }

    @GetMapping(value=PathUrl.GET_USER_ID)
    public UserResp getUserById(@PathVariable Long userId) {
        validateGetUserById(userId);
        var response = UserMapper.toResponseDto(userService.getUserById(userId));
        logResponse(response);
        return response;
    }

    private void validate(MsgUserReq request) {
        if (request == null || request.getRequestBody() == null) {
            throw new IllegalArgumentException("Invalid request data");
        }
    }

    private void validateStatusChange(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
    }

    private void validateGetAllUsers() {
        
    }

    private void validateGetUserById(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
    }

    private void logResponse(Object response) {
        logger.info("Response: {}", response);
    }
}
