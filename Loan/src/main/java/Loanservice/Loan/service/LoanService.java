package Loanservice.Loan.service;

import Loanservice.Loan.dto.LoanRequest;

public interface LoanService {

    void addLoan (Long employeId, LoanRequest loanRequest);


}
