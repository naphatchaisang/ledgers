package com.example.ledger.category.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ledger.category.datasource.entity.LedgerCat;

@Repository
public interface CategoryRepository extends JpaRepository<LedgerCat, Long> {
}
