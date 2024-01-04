package Loanservice.Loan.service;

import Loanservice.Loan.dto.LoanRequest;
import Loanservice.Loan.dto.LoanResponse;

import java.util.List;

public interface LoanService {

    void addLoan (Long employeId, LoanRequest loanRequest);
    List<LoanResponse> getAllLoans();


}
