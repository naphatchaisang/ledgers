package com.example.ledger.transaction.datasource.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.example.ledger.model.enums.Status;
import com.example.ledger.model.enums.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "LEDGER_TX")
public class LedgerTx {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LEDGER_ID")
    private Long ledgerId;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "USER_NAME", nullable = false, length = 30)
    private String userName;

    @Column(name = "CAT_ID", nullable = false)
    private Long catId;

    @Column(name = "CAT_NAME", nullable = false, length = 20)
    private String catName;

    @Column(name = "TX_TYPE", nullable = false, length = 10)
    private TransactionType txType; // INCOME, EXPENSE

    @Column(name = "LEDGER_DESC", length = 200)
    private String ledgerDesc;

    @Column(name = "AMOUNT", nullable = false, precision = 20, scale = 2)
    private BigDecimal amount;

    @Column(name = "LEDGER_DTM", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ledgerDtm;

    @Column(name = "STATUS", nullable = false, length = 10)
    private Status status; // ACTIVE, INACTIVE

    @Column(name = "CREATE_BY", nullable = false, length = 30)
    private String createdBy;

    @Column(name = "CREATE_DTM", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDtm;

    @Column(name = "UPDATE_BY", length = 30)
    private String updatedBy;

    @Column(name = "UDATE_DTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDtm;
}

