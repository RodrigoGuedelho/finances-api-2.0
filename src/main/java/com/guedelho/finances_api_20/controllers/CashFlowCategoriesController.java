package com.guedelho.finances_api_20.controllers;

import com.guedelho.finances_api_20.dtos.request.CashFlowCategoryRequest;
import com.guedelho.finances_api_20.dtos.request.StatusRequest;
import com.guedelho.finances_api_20.dtos.response.CashFlowCategoryResponse;
import com.guedelho.finances_api_20.models.CashFlowCategory;
import com.guedelho.finances_api_20.services.CashFlowCategoryService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/cash-flow/categories")
public class CashFlowCategoriesController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CashFlowCategoryService cashFlowCategoryService;

    @PostMapping
    public ResponseEntity<CashFlowCategoryResponse> save(@Valid @RequestBody CashFlowCategoryRequest cashFlowCategoryRequest,
                                                         @RequestHeader("Authorization") String token){
        CashFlowCategory cashFlowCategory = cashFlowCategoryService.save(toEntity(cashFlowCategoryRequest), token);
        return ResponseEntity.ok(toResponse(cashFlowCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CashFlowCategoryResponse> update(@Valid @RequestBody CashFlowCategoryRequest cashFlowCategoryRequest,
                                                           @RequestHeader("Authorization") String token,
                                                           @PathVariable("id") Long id) throws BadRequestException {
        CashFlowCategory cashFlowCategory = cashFlowCategoryService.update(id, toEntity(cashFlowCategoryRequest), token);
        return ResponseEntity.ok(toResponse(cashFlowCategory));

    }
    @PutMapping("/{id}/status")
    public ResponseEntity<CashFlowCategoryResponse> updateStatus(@PathVariable Long id, @RequestHeader("Authorization") String token,
                                                                 @RequestBody StatusRequest statusRequest) throws BadRequestException {
        CashFlowCategory cashFlowCategory = cashFlowCategoryService.updateStatus(id, statusRequest.getStatus(), token);
        return ResponseEntity.ok(toResponse(cashFlowCategory));
    }

    @GetMapping
    public ResponseEntity<Page> find(@RequestParam(required = false, defaultValue = "0") Long id,
                                     @RequestParam(required = false, defaultValue = "") String description,
                                     Pageable pageable,
                                     @RequestHeader("Authorization") String token ){
        Page<CashFlowCategory> page = cashFlowCategoryService.find(pageable, id, description, token);
        return ResponseEntity.ok(toPage(page));
    }

    private CashFlowCategory toEntity(CashFlowCategoryRequest cashFlowCategoryRequest){
        return modelMapper.map(cashFlowCategoryRequest, CashFlowCategory.class);
    }

    private CashFlowCategoryResponse toResponse(CashFlowCategory cashFlowCategory){
        return modelMapper.map(cashFlowCategory, CashFlowCategoryResponse.class);
    }

    private Page<CashFlowCategoryResponse> toPage(Page<CashFlowCategory> page){
        List<CashFlowCategoryResponse> cashFlowCategoryResponseList = new ArrayList<>();
        for (CashFlowCategory cashFlowCategory : page.getContent())
            cashFlowCategoryResponseList.add(toResponse(cashFlowCategory));
        return new PageImpl<>(cashFlowCategoryResponseList);
    }
}
