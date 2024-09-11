package com.example.ledger.model.mapper;

import java.math.BigDecimal;
import java.util.Date;

import com.example.ledger.dto.response.TransactionResp;
import com.example.ledger.msg.TransactionReq;
import com.example.ledger.transaction.datasource.entity.LedgerTx;

public class TransactionMapper {

    public static LedgerTx toEntity(TransactionReq request) {
        LedgerTx transaction = new LedgerTx();
        transaction.setUserId(request.getUserId());
        transaction.setUserName(request.getUserName());
        transaction.setCatId(request.getCatId());
        transaction.setCatName(request.getCatName());
        transaction.setTxType(request.getTxType());
        transaction.setLedgerDesc(request.getLedgerDesc());
        transaction.setAmount(BigDecimal.valueOf(request.getAmount())); // Convert Double to BigDecimal
        transaction.setLedgerDtm(request.getLedgerDtm());
        transaction.setStatus(request.getStatus());
        transaction.setCreatedBy(request.getCreatedBy());
        transaction.setUpdatedBy(request.getUpdatedBy());
        return transaction;
    }

    public static TransactionResp toResponseDto(LedgerTx transaction) {
        TransactionResp response = new TransactionResp();
        response.setLedgerId(transaction.getLedgerId());
        response.setUserId(transaction.getUserId());
        response.setUserName(transaction.getUserName());
        response.setCatId(transaction.getCatId());
        response.setCatName(transaction.getCatName());
        response.setTxType(transaction.getTxType());
        response.setLedgerDesc(transaction.getLedgerDesc());
        response.setAmount(transaction.getAmount().doubleValue()); // Convert BigDecimal to Double
        response.setLedgerDtm(transaction.getLedgerDtm());
        response.setStatus(transaction.getStatus());
        response.setCreateDtm(transaction.getCreateDtm());
        return response;
    }

    public static void updateEntity(LedgerTx existingTransaction, LedgerTx newTransaction) {
        existingTransaction.setUserId(newTransaction.getUserId());
        existingTransaction.setUserName(newTransaction.getUserName());
        existingTransaction.setCatId(newTransaction.getCatId());
        existingTransaction.setCatName(newTransaction.getCatName());
        existingTransaction.setTxType(newTransaction.getTxType());
        existingTransaction.setLedgerDesc(newTransaction.getLedgerDesc());
        existingTransaction.setAmount(newTransaction.getAmount());
        existingTransaction.setLedgerDtm(newTransaction.getLedgerDtm());
        existingTransaction.setStatus(newTransaction.getStatus());
        existingTransaction.setUpdatedBy(newTransaction.getUpdatedBy());
        existingTransaction.setUpdateDtm(new Date());
    }
}
