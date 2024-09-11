package com.example.ledger.msg;

import com.example.ledger.model.enums.Status;
import com.example.ledger.model.enums.TransactionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryReq {

    @NotBlank(message = "Category name is required.")
    @Size(max = 20, message = "Category name must be less than 20 characters.")
    private String catName;

    @NotNull(message = "Transaction type is required.")
    private TransactionType txType; // INCOME, EXPENSE

    @NotNull(message = "Status is required.")
    private Status status; // ACTIVE, INACTIVE

    @NotBlank(message = "CreatedBy is required.")
    private String createdBy;
}

