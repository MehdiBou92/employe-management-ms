package Loanservice.Loan.service.Impl;

import Loanservice.Loan.client.EmployeRestClient;
import Loanservice.Loan.dto.LoanRequest;
import Loanservice.Loan.repository.LoanRepository;
import Loanservice.Loan.service.LoanService;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {
    private final EmployeRestClient employeRestClient;
    private final LoanRepository loanRepository;

    public LoanServiceImpl(EmployeRestClient employeRestClient, LoanRepository loanRepository) {
        this.employeRestClient = employeRestClient;
        this.loanRepository = loanRepository;
    }

    public void addLoan(Long employeId, LoanRequest loanRequest) {

    }
}
