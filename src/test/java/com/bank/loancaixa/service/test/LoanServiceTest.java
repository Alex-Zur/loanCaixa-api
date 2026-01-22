package com.bank.loancaixa.service.test;

import com.bank.loancaixa.model.LoanRequest;
import com.bank.loancaixa.model.LoanRequest.StatusEnum;
import com.bank.loancaixa.repository.LoanRepository;
import com.bank.loancaixa.service.LoanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanRepository repository;

    @InjectMocks
    private LoanService service;

    @Test
    void createLoanAndSave() {
        LoanRequest loan = new LoanRequest();
        loan.setApplicantName("Alejandro");

        when(repository.save(any(LoanRequest.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        LoanRequest result = service.create(loan);

        assertNotNull(result.getId());
        assertNotNull(result.getCreationDate());
        assertEquals(StatusEnum.PENDING, result.getStatus());

        verify(repository).save(loan);
    }


    @Test
    void getByFilters() {
        List<LoanRequest> loans = List.of(new LoanRequest());

        when(repository.searchLoan(
                any(), any(), any(), any(), any()
        )).thenReturn(loans);

        List<LoanRequest> result = service.getByFilters(
                "12345678Z", StatusEnum.PENDING, "Alejandro", 100.0, 1000.0
        );

        assertEquals(1, result.size());
        verify(repository).searchLoan(
                "12345678Z", StatusEnum.PENDING, "Alejandro", 100.0, 1000.0
        );
    }

    @Test
    void updateStatus() {
        UUID id = UUID.randomUUID();
        LoanRequest loan = new LoanRequest();
        loan.setId(id);
        loan.setStatus(StatusEnum.PENDING);

        when(repository.findById(id)).thenReturn(Optional.of(loan));
        when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        LoanRequest result = service.changeStatus(id, StatusEnum.APPROVED);

        assertEquals(StatusEnum.APPROVED, result.getStatus());
    }

}