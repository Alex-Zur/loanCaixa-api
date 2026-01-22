package com.bank.loancaixa.controller;

import com.bank.loancaixa.api.LoanApi;
import com.bank.loancaixa.model.LoanRequest;
import com.bank.loancaixa.model.LoanRequest.StatusEnum;
import com.bank.loancaixa.service.LoanService;
import com.bank.loancaixa.utils.ValidatorNifNie;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
public class LoanController implements LoanApi {

    private final LoanService service;
    

    public LoanController(LoanService service) {
        this.service = service;
    }

    public ResponseEntity<LoanRequest> loanCreatePost(String documentId, String ApplicantName, Double amount, String currency) {
    	LoanRequest loanRequest = new LoanRequest();
    	
    	if(!ValidatorNifNie.isValid(documentId)) {
    		throw new IllegalStateException("NIF or NIE not valid");
    		
    	} 
    	
    	loanRequest.setDocumentId(documentId);
    	loanRequest.setApplicantName(ApplicantName);	
    	loanRequest.setAmount(amount);	
    	loanRequest.setCurrency(currency);	
    	
        return ResponseEntity.ok(service.create(loanRequest));
    }
    
    public ResponseEntity<List<LoanRequest>> loanVisualizeGet(String applicantName,
            String documentId,
            String status,
            Double minAmount,
            Double maxAmount) {
    	
    	StatusEnum statusEnum = status != null ? StatusEnum.valueOf(status) : null;
    	
        return ResponseEntity.ok(service.getByFilters(  documentId,
        		statusEnum,
                applicantName,
                minAmount,
                maxAmount));
    }
    
    public ResponseEntity<LoanRequest> loanIdStatusPatch(UUID id, String newStatus) {
    	
    	StatusEnum newStatusEnum = StatusEnum.valueOf(newStatus);
    	
        return ResponseEntity.ok(service.changeStatus(id, newStatusEnum));
    }
    



}
