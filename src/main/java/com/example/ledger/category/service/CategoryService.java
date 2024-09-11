package com.example.ledger.category.service;



import java.util.List;

import com.example.ledger.dto.response.CategoryResp;
import com.example.ledger.model.enums.Status;
import com.example.ledger.model.enums.TransactionType;
import com.example.ledger.msg.CategoryReq;

public interface CategoryService {

    CategoryResp addCategory(CategoryReq request);

    CategoryResp updateCategory(Long catId, CategoryReq request);

    CategoryResp changeCategoryStatus(Long catId, Status status);

    List<CategoryResp> getAllCategories();

    CategoryResp getCategoryById(Long catId);

    List<CategoryResp> getCategoriesByTxType(TransactionType txType);
}

