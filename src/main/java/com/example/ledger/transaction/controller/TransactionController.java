package com.example.ledger.transaction.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ledger.constants.Constants.PathUrl;
import com.example.ledger.dto.response.TransactionResp;
import com.example.ledger.msg.TransactionReq;
import com.example.ledger.transaction.service.TransactionService;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping(PathUrl.TRANSACTION_BASE_URL)
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public TransactionResp addTransaction(@Valid @RequestBody TransactionReq request) {
        validate(request);
        return transactionService.addTransaction(request);
    }

    @GetMapping
    public List<TransactionResp> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping(PathUrl.GET_TRANSACTION_ID)
    public TransactionResp getTransactionById(@PathVariable Long ledgerId) {
        validateLedgerId(ledgerId);
        return transactionService.getTransactionById(ledgerId);
    }

    @GetMapping(PathUrl.GET_TRANSACTION_USER)
    public List<TransactionResp> getTransactionsByUserId(@PathVariable Long userId) {
        validateUserId(userId);
        return transactionService.getTransactionsByUserId(userId);
    }

    @GetMapping(PathUrl.GET_TRANSACTION_FILTER)
    public List<TransactionResp> getTransactionsByDateAndUser(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(required = false) String userName) {
                validateDateRange(startDate,endDate);
        return transactionService.filterTransactions(startDate, endDate, userName);
    }


    @PutMapping(PathUrl.GET_TRANSACTION_ID)
    public TransactionResp updateTransaction(@PathVariable Long ledgerId, @Valid @RequestBody TransactionReq request) {
        validate(request);
        validateLedgerId(ledgerId);
        return transactionService.updateTransaction(ledgerId, request);
    }

    @DeleteMapping(PathUrl.GET_TRANSACTION_STATUS)
    public TransactionResp changeTransactionStatus(@PathVariable Long ledgerId) {
        validateLedgerId(ledgerId);
        return transactionService.changeTransactionStatus(ledgerId);
    }

    // Validation Methods

    private void validate(TransactionReq request) {
        if (request.getUserId() == null || request.getUserId() <= 0) {
            throw new ValidationException("User ID must be a positive number.");
        }
        if (request.getCatId() == null || request.getCatId() <= 0) {
            throw new ValidationException("Category ID must be a positive number.");
        }
        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new ValidationException("Amount must be a positive number.");
        }
        if (request.getLedgerDtm() == null) {
            throw new ValidationException("Transaction date and time are required.");
        }
        if (request.getTxType() == null) {
            throw new ValidationException("Transaction type is required.");
        }
        if (request.getStatus() == null) {
            throw new ValidationException("Status is required.");
        }
    }

    private void validateLedgerId(Long ledgerId) {
        if (ledgerId == null || ledgerId <= 0) {
            throw new ValidationException("Ledger ID must be a positive number.");
        }
    }

    private void validateUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new ValidationException("User ID must be a positive number.");
        }
    }

    // private void validateUserName(String userName) {
    //     if (userName == null || userName.trim().isEmpty()) {
    //         throw new ValidationException("User name cannot be blank.");
    //     }
    // }

    private void validateDateRange(Date startDate, Date endDate) {
        if (isDateOutOfRange(startDate,endDate)) {
            throw new ValidationException("End date cannot be before start date.");
        }
    }

    private Boolean isDateOutOfRange(Date startDate, Date endDate){
        return (startDate != null) && (endDate != null) && (endDate.before(startDate));
    }

    
}
