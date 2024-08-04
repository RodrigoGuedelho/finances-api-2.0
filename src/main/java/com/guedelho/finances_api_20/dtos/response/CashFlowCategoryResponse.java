package com.guedelho.finances_api_20.dtos.response;

import com.guedelho.finances_api_20.enums.CashFlowCategoriesType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashFlowCategoryResponse {
    private Long id;
    private String description;
    private BigDecimal expenseLimitTarget;
    private CashFlowCategoriesType type;
}
