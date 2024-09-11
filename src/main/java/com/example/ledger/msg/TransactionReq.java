package com.example.ledger.msg;

import java.util.Date;

import com.example.ledger.model.enums.Status;
import com.example.ledger.model.enums.TransactionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionReq {

    @NotNull
    private Long userId;

    @NotBlank
    private String userName;

    @NotNull
    private Long catId;

    @NotBlank
    private String catName;

    @NotNull
    private TransactionType txType;

    @NotBlank
    private String ledgerDesc;

    @NotNull
    private Double amount;

    @NotNull
    private Date ledgerDtm;

    @NotNull
    private Status status;

    @NotBlank
    private String createdBy;

    private String updatedBy;
}
