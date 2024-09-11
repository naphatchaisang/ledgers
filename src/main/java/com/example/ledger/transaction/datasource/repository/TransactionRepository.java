package com.example.ledger.transaction.datasource.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ledger.transaction.datasource.entity.LedgerTx;

public interface TransactionRepository extends JpaRepository<LedgerTx, Long> {

    List<LedgerTx> findByUserId(Long userId);

    List<LedgerTx> findByLedgerDtmBetweenAndUserName(Date startDate, Date endDate, String userName);
}
