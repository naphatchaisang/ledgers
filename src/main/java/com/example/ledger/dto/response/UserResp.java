package com.example.ledger.dto.response;

import java.util.Date;

import com.example.ledger.model.enums.Status;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserResp {

    private Long userId;
    private String userName;
    private Status status;
    private Date createDtm;
    private Date updateDtm;
}

