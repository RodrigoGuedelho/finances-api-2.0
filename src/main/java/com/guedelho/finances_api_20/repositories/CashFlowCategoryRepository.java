package com.guedelho.finances_api_20.repositories;

import com.guedelho.finances_api_20.models.CashFlowCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface CashFlowCategoryRepository extends JpaRepository<CashFlowCategory, Long> {
    @Query("select c from CashFlowCategory c " +
            "where (c.id = :id or :id = 0) and c.description like :description  " +
            "and c.user.id = :userId")
    public Page<CashFlowCategory> find(Pageable pageable, @PathVariable Long id, @PathVariable String description,
                                       @PathVariable Long userId);
}
