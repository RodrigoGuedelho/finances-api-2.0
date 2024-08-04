package com.guedelho.finances_api_20.dtos.request;

import com.guedelho.finances_api_20.enums.CashFlowCategoriesType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashFlowCategoryRequest {
    @NotBlank(message = "Descrição invalida.")
    private String description;
    private BigDecimal expenseLimitTarget;
    @NotNull(message = "Tipo de conta não informado. (PAY ou TO_RECEIVE)")
    private CashFlowCategoriesType type;
}
