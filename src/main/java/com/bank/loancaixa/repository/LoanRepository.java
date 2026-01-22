package com.bank.loancaixa.repository;

import com.bank.loancaixa.model.LoanRequest;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class LoanRepository {

    private final Map<UUID, LoanRequest> storage = new ConcurrentHashMap<>();

    public LoanRequest save(LoanRequest loan) {
        storage.put(loan.getId(), loan);
        return loan;
    }

    public Optional<LoanRequest> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<LoanRequest> findAll() {
        return new ArrayList<>(storage.values());
    }
    
    public List<LoanRequest> searchLoan(
            String documentId,
            LoanRequest.StatusEnum status,
            String applicantName,
            Double minAmount,
            Double maxAmount
    ) {
        return storage.values().stream()
                .filter(loan -> documentId == null || documentId.equals(loan.getDocumentId()))
                .filter(loan -> status == null || loan.getStatus() == status)
                .filter(loan -> applicantName == null || loan.getApplicantName().equalsIgnoreCase(applicantName))
                .filter(loan -> minAmount == null || loan.getAmount() >= minAmount)
                .filter(loan -> maxAmount == null || loan.getAmount() <= maxAmount)
                .toList();
    }



}