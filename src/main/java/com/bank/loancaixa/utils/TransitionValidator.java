package com.bank.loancaixa.utils;


import com.bank.loancaixa.model.LoanRequest;
import com.bank.loancaixa.model.LoanRequest.StatusEnum;

public class TransitionValidator {

    public static boolean isValidTransition(LoanRequest.StatusEnum current, StatusEnum next) {
        return (current == LoanRequest.StatusEnum.PENDING &&
                (next == LoanRequest.StatusEnum.APPROVED || next == LoanRequest.StatusEnum.REJECTED))
            || (current == LoanRequest.StatusEnum.APPROVED && next == LoanRequest.StatusEnum.CANCELLED);
    }
    
}
