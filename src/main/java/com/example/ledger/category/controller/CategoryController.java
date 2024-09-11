package com.example.ledger.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ledger.category.service.CategoryService;
import com.example.ledger.constants.Constants.PathUrl;
import com.example.ledger.dto.response.CategoryResp;
import com.example.ledger.model.enums.Status;
import com.example.ledger.model.enums.TransactionType;
import com.example.ledger.msg.CategoryReq;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping(PathUrl.CATEGORY_BASE_URL)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public CategoryResp addCategory(@Valid @RequestBody CategoryReq request) {
        validateAddCategory(request);
        return categoryService.addCategory(request);
    }

    @PutMapping(PathUrl.GET_CATEGORY_ID)
    public CategoryResp updateCategory(@PathVariable Long catId, @Valid @RequestBody CategoryReq request) {
        validateUpdateCategory(catId, request);
        return categoryService.updateCategory(catId, request);
    }

    @PatchMapping(PathUrl.GET_CATEGORY_STATUS)
    public CategoryResp changeCategoryStatus(@PathVariable Long catId, @RequestParam Status status) {
        validateChangeCategoryStatus(catId, status);
        return categoryService.changeCategoryStatus(catId, status);
    }

    @GetMapping
    public List<CategoryResp> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping(PathUrl.GET_CATEGORY_ID)
    public CategoryResp getCategoryById(@PathVariable Long catId) {
        validateGetCategoryById(catId);
        return categoryService.getCategoryById(catId);
    }

    @GetMapping(PathUrl.GET_CATEGORY_TXTYPE)
    public List<CategoryResp> getCategoriesByTxType(@PathVariable TransactionType txType) {
        validateGetCategoriesByTxType(txType);
        return categoryService.getCategoriesByTxType(txType);
    }


    private void validateAddCategory(CategoryReq request) {
        if (request.getCatName() == null || request.getCatName().isEmpty()) {
            throw new ValidationException("Category name is required.");
        }
        if (request.getTxType() == null) {
            throw new ValidationException("Transaction type is required.");
        }
    }

    private void validateUpdateCategory(Long catId, CategoryReq request) {
        if (catId == null || catId <= 0) {
            throw new ValidationException("Valid category ID is required.");
        }
        if (request.getCatName() == null || request.getCatName().isEmpty()) {
            throw new ValidationException("Category name is required.");
        }
    }

    private void validateChangeCategoryStatus(Long catId, Status status) {
        if (catId == null || catId <= 0) {
            throw new ValidationException("Valid category ID is required.");
        }
        if (status == null) {
            throw new ValidationException("Status is required.");
        }
    }

    private void validateGetCategoryById(Long catId) {
        if (catId == null || catId <= 0) {
            throw new ValidationException("Valid category ID is required.");
        }
    }

    private void validateGetCategoriesByTxType(TransactionType txType) {
        if (txType == null) {
            throw new ValidationException("Transaction type is required.");
        }
    }
}
