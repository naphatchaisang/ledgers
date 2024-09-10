package com.example.ledger.user.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ledger.user.datasource.entity.LedgerUser;

@Repository
public interface UserRepository extends JpaRepository<LedgerUser, Long> {
}
