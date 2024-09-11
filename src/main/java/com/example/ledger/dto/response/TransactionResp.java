package com.example.ledger.dto.response;

import java.util.Date;

import com.example.ledger.model.enums.Status;
import com.example.ledger.model.enums.TransactionType;

import lombok.Data;

@Data
public class TransactionResp {

    private Long ledgerId;

    private Long userId;

    private String userName;

    private Long catId;

    private String catName;

    private TransactionType txType;

    private String ledgerDesc;

    private Double amount;

    private Date ledgerDtm;

    private Status status;

    private Date createDtm;

    private String updatedBy;

    private Date updateDtm;
}
