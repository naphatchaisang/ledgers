package com.example.ledger.msg;


import com.example.ledger.model.enums.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserReq {

    @NotBlank(message = "User name is required.")
    @Size(max = 30, message = "User name must be less than 30 characters.")
    private String userName;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;

    private Status status; 
}

