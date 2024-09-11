package com.example.ledger.transaction.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ledger.dbservice.TransactionDbService;
import com.example.ledger.dto.response.TransactionResp;
import com.example.ledger.model.mapper.TransactionMapper;
import com.example.ledger.msg.TransactionReq;
import com.example.ledger.transaction.datasource.entity.LedgerTx;
import com.example.ledger.transaction.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDbService transactionDbService;

    @Override
    public TransactionResp addTransaction(TransactionReq request) {
        LedgerTx transaction = TransactionMapper.toEntity(request);
        transaction.setCreateDtm(new Date());
        return TransactionMapper.toResponseDto(transactionDbService.saveTransaction(transaction));
    }

    @Override
    public List<TransactionResp> getAllTransactions() {
        return transactionDbService.findAllTransactions().stream()
                .map(TransactionMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionResp getTransactionById(Long ledgerId) {
        LedgerTx transaction = transactionDbService.findTransactionById(ledgerId);
        return TransactionMapper.toResponseDto(transaction);
    }

    @Override
    public List<TransactionResp> getTransactionsByUserId(Long userId) {
        return transactionDbService.findTransactionsByUserId(userId).stream()
                .map(TransactionMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionResp> filterTransactions(Date startDate, Date endDate, String userName) {
        // Fetch filtered transactions from the database service
        List<LedgerTx> transactions = transactionDbService.filterTransactions(startDate, endDate, userName);

        // Convert the filtered transactions to response DTOs
        return transactions.stream()
                .map(TransactionMapper::toResponseDto)
                .collect(Collectors.toList());
    }



    @Override
    public TransactionResp updateTransaction(Long ledgerId, TransactionReq request) {
        LedgerTx transaction = TransactionMapper.toEntity(request);
        transaction.setUpdatedBy(request.getUpdatedBy());
        transaction.setUpdateDtm(new Date());
        return TransactionMapper.toResponseDto(transactionDbService.updateTransaction(ledgerId, transaction));
    }

    @Override
    public TransactionResp changeTransactionStatus(Long ledgerId) {
        LedgerTx transaction = transactionDbService.changeStatus(ledgerId, "INACTIVE");
        return TransactionMapper.toResponseDto(transaction);
    }
}
