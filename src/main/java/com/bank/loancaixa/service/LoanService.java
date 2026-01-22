package com.bank.loancaixa.service;

import com.bank.loancaixa.model.LoanRequest;
import com.bank.loancaixa.model.LoanRequest.StatusEnum;
import com.bank.loancaixa.repository.LoanRepository;
import com.bank.loancaixa.utils.TransitionValidator;

import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LoanService {

    private final LoanRepository repository;

    public LoanService(LoanRepository repository) {
        this.repository = repository;
    }

    public LoanRequest create(LoanRequest loan) {
        loan.setId(UUID.randomUUID());
        loan.setCreationDate(OffsetDateTime.now());
        loan.setStatus(LoanRequest.StatusEnum.PENDING);
       
        return repository.save(loan);
    }

    public List<LoanRequest> getAll() {
        return repository.findAll();
    }

    public LoanRequest getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
    }
    
    
    public List<LoanRequest> getByFilters( String documentId,
            LoanRequest.StatusEnum status,
            String applicantName,
            Double minAmount,
            Double maxAmount) {
        return repository.searchLoan(documentId, status, applicantName, minAmount, maxAmount);
    }
    
    public LoanRequest changeStatus(UUID id, StatusEnum newStatus) {
        LoanRequest loan = getById(id);

        if (!TransitionValidator.isValidTransition(loan.getStatus(), newStatus)) {
            throw new IllegalStateException("Transition not permitted");
        }

        loan.setStatus(newStatus);
        return repository.save(loan);
    }

}