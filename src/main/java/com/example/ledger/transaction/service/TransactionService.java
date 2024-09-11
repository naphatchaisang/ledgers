package com.example.ledger.transaction.service;

import java.util.Date;
import java.util.List;

import com.example.ledger.dto.response.TransactionResp;
import com.example.ledger.msg.TransactionReq;

public interface TransactionService {

    TransactionResp addTransaction(TransactionReq request);

    List<TransactionResp> getAllTransactions();

    TransactionResp getTransactionById(Long ledgerId);

    List<TransactionResp> getTransactionsByUserId(Long userId);

    List<TransactionResp> filterTransactions(Date startDate, Date endDate, String userName);

    TransactionResp updateTransaction(Long ledgerId, TransactionReq request);

    TransactionResp changeTransactionStatus(Long ledgerId);
}
