package com.example.ledger.category.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ledger.category.datasource.entity.LedgerCat;
import com.example.ledger.category.service.CategoryService;
import com.example.ledger.dbservice.CategoryDbService;
import com.example.ledger.dto.response.CategoryResp;
import com.example.ledger.model.enums.Status;
import com.example.ledger.model.enums.TransactionType;
import com.example.ledger.model.mapper.CategoryMapper;
import com.example.ledger.msg.CategoryReq;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDbService categoryDbService;

    @Override
    public CategoryResp addCategory(CategoryReq request) {
        LedgerCat category = CategoryMapper.toEntity(request);
        category.setCreateDtm(new Date());
        return CategoryMapper.toResponseDto(categoryDbService.saveCategory(category));
    }

    @Override
    public CategoryResp updateCategory(Long catId, CategoryReq request) {
        LedgerCat category = CategoryMapper.toEntity(request);
        category.setUpdatedBy(request.getCreatedBy());
        return CategoryMapper.toResponseDto(categoryDbService.updateCategory(catId, category));
    }

    @Override
    public CategoryResp changeCategoryStatus(Long catId, Status status) {
        return CategoryMapper.toResponseDto(categoryDbService.changeStatus(catId, status));
    }

    @Override
    public List<CategoryResp> getAllCategories() {
        return categoryDbService.findAllCategories().stream()
                .map(CategoryMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResp getCategoryById(Long catId) {
        return CategoryMapper.toResponseDto(categoryDbService.findCategoryById(catId));
    }

    @Override
    public List<CategoryResp> getCategoriesByTxType(TransactionType txType) {
        return categoryDbService.findAllCategories().stream()
                .filter(category -> txType == category.getTxType())
                .map(CategoryMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
