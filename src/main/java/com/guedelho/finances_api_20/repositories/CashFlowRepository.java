package com.guedelho.finances_api_20.repositories;

import com.guedelho.finances_api_20.models.CashFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashFlowRepository extends JpaRepository<CashFlow, Long> {
}
