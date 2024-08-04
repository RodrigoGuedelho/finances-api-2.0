package com.guedelho.finances_api_20.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cash_flow")
public class CashFlow extends GenericModel{
    private String description;
    private BigDecimal cash;
    private Date date;
    @OneToOne
    @JoinColumn(unique = false)
    private User user;
    @OneToOne
    @JoinColumn(unique = false)
    private CashFlowCategory type;
}
