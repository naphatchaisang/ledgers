package com.example.ledger.user.datasource.entity;

import java.util.Date;

import com.example.ledger.model.enums.Status;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ledger_user")
@Data
@Access(AccessType.FIELD)
public class LedgerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name", unique = true, nullable = false, length = 30)
    private String userName;

    @Column(name = "password", nullable = false, length = 250)
    private String password;

    // @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10)
    private Status status;

    @Column(name = "created_by", length = 30)
    private String createdBy;

    @Column(name = "create_dtm")
    private Date createDtm;

    @Column(name = "updated_by", length = 30)
    private String updatedBy;

    @Column(name = "update_dtm")
    private Date updateDtm;
}
