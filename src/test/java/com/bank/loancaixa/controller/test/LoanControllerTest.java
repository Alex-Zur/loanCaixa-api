package com.bank.loancaixa.controller.test;

import com.bank.loancaixa.controller.LoanController;
import com.bank.loancaixa.model.LoanRequest;
import com.bank.loancaixa.model.LoanRequest.StatusEnum;
import com.bank.loancaixa.service.LoanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanControllerTest {

    @Mock
    private LoanService service;

    @InjectMocks
    private LoanController controller;

    @Test
    void loanCreatePost() {
        LoanRequest loan = new LoanRequest();
        loan.setStatus(StatusEnum.PENDING);

        when(service.create(any())).thenReturn(loan);

        ResponseEntity<LoanRequest> response = controller.loanCreatePost(
                "12345678Z", "Alejandro", 5000.0, "EUR"
        );

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(StatusEnum.PENDING, response.getBody().getStatus());
    }


    @Test
    void loanVisualizeGet() {
        List<LoanRequest> loans = List.of(new LoanRequest());

        when(service.getByFilters(
                any(), any(), any(), any(), any()
        )).thenReturn(loans);

        ResponseEntity<List<LoanRequest>> response =
                controller.loanVisualizeGet(
                        "Alejandro", "12345678Z", "PENDING", null, null
                );

        assertEquals(1, response.getBody().size());
    }

    @Test
    void loanIdStatusPatch() {
        UUID id = UUID.randomUUID();
        LoanRequest loan = new LoanRequest();
        loan.setStatus(StatusEnum.APPROVED);

        when(service.changeStatus(id, StatusEnum.APPROVED))
                .thenReturn(loan);

        ResponseEntity<LoanRequest> response =
                controller.loanIdStatusPatch(id, "APPROVED");

        assertEquals(StatusEnum.APPROVED, response.getBody().getStatus());
    }
}