package com.example.ledger.dbservice;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ledger.model.enums.Status;
import com.example.ledger.transaction.datasource.entity.LedgerTx;
import com.example.ledger.transaction.datasource.repository.TransactionRepository;

@Service
public class TransactionDbService {

    @Autowired
    private TransactionRepository transactionRepository;

    public LedgerTx saveTransaction(LedgerTx transaction) {
        transaction.setCreateDtm(new Date());
        return transactionRepository.save(transaction);
    }

    public LedgerTx updateTransaction(Long ledgerId, LedgerTx transaction) {
        LedgerTx existingTransaction = transactionRepository.findById(ledgerId)
                .orElseThrow(() -> new RuntimeException("Transaction not found."));
        existingTransaction.setUserId(transaction.getUserId());
        existingTransaction.setUserName(transaction.getUserName());
        existingTransaction.setCatId(transaction.getCatId());
        existingTransaction.setCatName(transaction.getCatName());
        existingTransaction.setTxType(transaction.getTxType());
        existingTransaction.setLedgerDesc(transaction.getLedgerDesc());
        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setLedgerDtm(transaction.getLedgerDtm());
        existingTransaction.setStatus(transaction.getStatus());
        existingTransaction.setUpdatedBy(transaction.getUpdatedBy());
        existingTransaction.setUpdateDtm(new Date());
        return transactionRepository.save(existingTransaction);
    }

    public LedgerTx changeStatus(Long ledgerId, String status) {
        LedgerTx transaction = transactionRepository.findById(ledgerId)
                .orElseThrow(() -> new RuntimeException("Transaction not found."));
        transaction.setStatus(Status.valueOf(status.toUpperCase()));
        transaction.setUpdateDtm(new Date());
        return transactionRepository.save(transaction);
    }

    public List<LedgerTx> findAllTransactions() {
        return transactionRepository.findAll();
    }

    public LedgerTx findTransactionById(Long ledgerId) {
        return transactionRepository.findById(ledgerId)
                .orElseThrow(() -> new RuntimeException("Transaction not found."));
    }

    public List<LedgerTx> findTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public List<LedgerTx> filterTransactions(Date startDate, Date endDate, String userName) {

    return transactionRepository.findAll().stream()
            .filter(tx -> (startDate == null || !tx.getLedgerDtm().before(startDate))) 
            .filter(tx -> (endDate == null || !tx.getLedgerDtm().after(endDate)))     
            .filter(tx -> (userName == null || tx.getUserName().equalsIgnoreCase(userName))) 
            .collect(Collectors.toList());
}

}
