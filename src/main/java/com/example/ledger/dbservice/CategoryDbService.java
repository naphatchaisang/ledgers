package com.example.ledger.dbservice;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ledger.category.datasource.entity.LedgerCat;
import com.example.ledger.category.datasource.repository.CategoryRepository;
import com.example.ledger.model.enums.Status;

@Service
public class CategoryDbService {

    @Autowired
    private CategoryRepository categoryRepository;

    public LedgerCat saveCategory(LedgerCat category) {
        category.setCreateDtm(new Date());
        return categoryRepository.save(category);
    }

    public LedgerCat updateCategory(Long catId, LedgerCat category) {
        LedgerCat existingCategory = categoryRepository.findById(catId)
                .orElseThrow(() -> new RuntimeException("Category not found."));
        existingCategory.setCatName(category.getCatName());
        existingCategory.setTxType(category.getTxType());
        existingCategory.setStatus(category.getStatus());
        existingCategory.setUpdatedBy(category.getUpdatedBy());
        existingCategory.setUpdateDtm(new Date());
        return categoryRepository.save(existingCategory);
    }

    public LedgerCat changeStatus(Long catId, Status status) {
        LedgerCat category = categoryRepository.findById(catId)
                .orElseThrow(() -> new RuntimeException("Category not found."));
        category.setStatus(status);
        category.setUpdateDtm(new Date());
        return categoryRepository.save(category);
    }

    public List<LedgerCat> findAllCategories() {
        return categoryRepository.findAll();
    }

    public LedgerCat findCategoryById(Long catId) {
        return categoryRepository.findById(catId)
                .orElseThrow(() -> new RuntimeException("Category not found."));
    }
}
