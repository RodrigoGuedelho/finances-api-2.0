package com.guedelho.finances_api_20.models;

import com.guedelho.finances_api_20.enums.CashFlowCategoriesType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "cash_flow_categories")
public class CashFlowCategory extends GenericModel{
    private String description;
    @Column(name = "expense_limit_target")
    private BigDecimal expenseLimitTarget;
    private CashFlowCategoriesType type;
    @OneToOne
    @JoinColumn(unique = false)
    private User user;
}
