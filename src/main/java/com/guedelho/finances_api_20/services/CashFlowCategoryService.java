package com.guedelho.finances_api_20.services;

import com.guedelho.finances_api_20.enums.CashFlowCategoriesType;
import com.guedelho.finances_api_20.enums.GenericStatus;
import com.guedelho.finances_api_20.models.CashFlowCategory;
import com.guedelho.finances_api_20.models.User;
import com.guedelho.finances_api_20.repositories.CashFlowCategoryRepository;
import com.guedelho.finances_api_20.repositories.UserRepository;
import com.guedelho.finances_api_20.security.TokenService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class CashFlowCategoryService {
    @Autowired
    private CashFlowCategoryRepository cashFlowCategoryRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    public CashFlowCategory save(CashFlowCategory cashFlowCategory, String token){
        if (cashFlowCategory.getExpenseLimitTarget() == null)
            cashFlowCategory.setExpenseLimitTarget(BigDecimal.ZERO);

        cashFlowCategory.setStatus(GenericStatus.ACTIVE);
        cashFlowCategory.setCreatedAt(OffsetDateTime.now());
        cashFlowCategory.setUpdatedAt(OffsetDateTime.now());

        cashFlowCategory.setUser(tokenService.validateTokenUser(token));
        return cashFlowCategoryRepository.save(cashFlowCategory);
    }

    public CashFlowCategory update(Long id, CashFlowCategory cashFlowCategory, String token) throws BadRequestException {
        Optional<CashFlowCategory> cashFlowCategoryOptional = cashFlowCategoryRepository.findById(id);

        BadRequestException exception = validateInput(cashFlowCategoryOptional, token);
        if(exception != null)
            throw exception;

        cashFlowCategoryOptional.get().setUpdatedAt(OffsetDateTime.now());
        cashFlowCategoryOptional.get().setDescription(cashFlowCategory.getDescription());
        cashFlowCategoryOptional.get().setExpenseLimitTarget(cashFlowCategory.getExpenseLimitTarget());
        if (cashFlowCategoryOptional.get().getExpenseLimitTarget() == null)
            cashFlowCategoryOptional.get().setExpenseLimitTarget(BigDecimal.ZERO);

        return cashFlowCategoryRepository.save(cashFlowCategoryOptional.get());
    }

    public Page<CashFlowCategory> find(Pageable pageable, Long id, String description, String token){
        User user = tokenService.validateTokenUser(token);
        return cashFlowCategoryRepository.find(pageable, id, "%" + description + "%", user.getId());
    }

    public CashFlowCategory updateStatus(Long id, GenericStatus status, String token) throws BadRequestException {
        Optional<CashFlowCategory> cashFlowCategoryOptional = cashFlowCategoryRepository.findById(id);

        BadRequestException exception = validateInput(cashFlowCategoryOptional, token);
        if(exception != null)
            throw exception;
        cashFlowCategoryOptional.get().setStatus(status);
        return cashFlowCategoryOptional.get();
    }

    private BadRequestException validateInput(Optional<CashFlowCategory> cashFlowCategoryOptional, String token){
        User user = tokenService.validateTokenUser(token);
        if(cashFlowCategoryOptional.isEmpty())
            return new BadRequestException("Categoria de fluxo de caixa não existe.");
        if (cashFlowCategoryOptional.get().getUser().getId() != user.getId())
            return new BadRequestException("Categoria não pertece a esse usuário.");
        return null;
    }
}
