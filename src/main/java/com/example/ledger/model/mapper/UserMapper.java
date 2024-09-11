package com.example.ledger.model.mapper;


import com.example.ledger.dto.response.UserResp;
import com.example.ledger.msg.UserReq;
import com.example.ledger.user.datasource.entity.LedgerUser;

public class UserMapper {
    public static LedgerUser toEntity(UserReq requestDto) {
        LedgerUser user = new LedgerUser();
        user.setUserName(requestDto.getUserName());
        user.setPassword(requestDto.getPassword());
        user.setStatus(requestDto.getStatus());
        return user;
    }

    public static UserResp toResponseDto(LedgerUser user) {
        UserResp responseDto = new UserResp();
        responseDto.setUserId(user.getUserId());
        responseDto.setUserName(user.getUserName());
        responseDto.setStatus(user.getStatus());
        responseDto.setCreateDtm(user.getCreateDtm());
        responseDto.setUpdateDtm(user.getUpdateDtm());
        return responseDto;
    }
}

