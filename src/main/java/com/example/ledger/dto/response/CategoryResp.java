package com.example.ledger.dto.response;

import java.util.Date;

import com.example.ledger.model.enums.Status;
import com.example.ledger.model.enums.TransactionType;

import lombok.Data;

@Data
public class CategoryResp {

    private Long catId;
    private String catName;
    private TransactionType txType;
    private Status status;
    private String createdBy;
    private String updatedBy;
    private Date createDtm;
    private Date updateDtm;
}

