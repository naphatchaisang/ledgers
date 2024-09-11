package com.example.ledger.model.mapper;

import java.util.Date;

import com.example.ledger.category.datasource.entity.LedgerCat;
import com.example.ledger.dto.response.CategoryResp;
import com.example.ledger.msg.CategoryReq;

public class CategoryMapper {

    public static LedgerCat toEntity(CategoryReq req) {
        LedgerCat category = new LedgerCat();
        category.setCatName(req.getCatName());
        category.setTxType(req.getTxType());
        category.setStatus(req.getStatus());
        category.setCreatedBy(req.getCreatedBy());
        category.setCreateDtm(new Date());
        return category;
    }

    public static CategoryResp toResponseDto(LedgerCat category) {
        CategoryResp resp = new CategoryResp();
        resp.setCatId(category.getCatId());
        resp.setCatName(category.getCatName());
        resp.setTxType(category.getTxType());
        resp.setStatus(category.getStatus());
        resp.setCreatedBy(category.getCreatedBy());
        resp.setUpdatedBy(category.getUpdatedBy());
        resp.setCreateDtm(category.getCreateDtm());
        resp.setUpdateDtm(category.getUpdateDtm());
        return resp;
    }
}

