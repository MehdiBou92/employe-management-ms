package Loanservice.Loan.service.Impl;

import Loanservice.Loan.client.EmployeRestClient;
import Loanservice.Loan.dto.EmployeResponse;
import Loanservice.Loan.dto.LoanRequest;
import Loanservice.Loan.dto.LoanResponse;
import Loanservice.Loan.entities.Loan;
import Loanservice.Loan.repository.LoanRepository;
import Loanservice.Loan.service.LoanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Service
public class LoanServiceImpl implements LoanService {
    private final EmployeRestClient employeRestClient;
    private final LoanRepository loanRepository;

    public LoanServiceImpl(EmployeRestClient employeRestClient, LoanRepository loanRepository) {
        this.employeRestClient = employeRestClient;
        this.loanRepository = loanRepository;
    }

    // RULES :
    // If the employee has more than 2 years of seniority , he can qualify for a loan
    // If the employee never took a loan , interest rate at 1%
    // If the employee already took and refunded the loan, interest rate at 3%
    // If the employee already has an ongoing loan and wants another, throw exception ("Loan Refused").
    @Transactional
    public void addLoan(Long employeId, LoanRequest loanRequest) {

        //Retreive Employe By Id :
        EmployeResponse employeResponse = employeRestClient.findEmployeByIdWithLoans(employeId);

        // Check if the employee is able to take a loan :


            // Check if the employee has an ongoing Loan :
            List<LoanResponse> onGoingLeave = employeResponse.getLoans().stream()
                    .filter(loanResponse -> loanResponse.getEndLoanDate().isAfter(LocalDate.now()))
                    .toList();
            if(onGoingLeave.isEmpty() & employeResponse.getSeniority() >= 2){

            // New Loan  :
                Loan loan = new Loan();
                loan.setEmployeId(employeId);
                loan.setStartLoanDate(LocalDate.now());
                loan.setAmount(loanRequest.getAmount());
                loan.setDuration(loanRequest.getDuration());
                loan.setEndLoanDate(loan.getStartLoanDate().plusMonths(loan.getDuration()));

                // Set loan Rate :
                setLoanRate(employeResponse, loan);

                // Calculate Interest :
                float interest = calculateInterest(loan);
                loan.setInterest(interest);

                // Set Monthly Payment :
                float principal = calculateMonthlyPayment(loan);
                loan.setMonthlyPayment(principal + interest);

                // Save Loan :
                loanRepository.save(loan);
            } else {
                throw new IllegalArgumentException("refused");
            }

        }




    public List<LoanResponse> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        return loans.stream()
                .map(this::maoToLoanResponse)
                .toList();
    }

    private LoanResponse maoToLoanResponse(Loan loan) {
        LoanResponse loanResponse = new LoanResponse();
        loanResponse.setId(loan.getId());
        loanResponse.setAmount(loan.getAmount());
        loanResponse.setEndLoanDate(loan.getEndLoanDate());
        loanResponse.setStartLoanDate(loan.getStartLoanDate());
        loanResponse.setRate(loan.getRate());
        loanResponse.setDuration(loan.getDuration());
        loanResponse.setInterest(loan.getInterest());
        loanResponse.setEmployeId(loan.getEmployeId());
        loanResponse.setStatut(loan.isStatut());
        loanResponse.setMonthlyPayment(loan.getMonthlyPayment());
        return loanResponse;
    }

    private static void setLoanRate(EmployeResponse employeResponse, Loan loan) {
        float FIRST_TIME_LOAN = 0.01F;
        float OTHER_LOANS = 0.03F;
        // Set the Rate :
        if (employeResponse.getLoans().isEmpty()) {
            loan.setRate(FIRST_TIME_LOAN);
        } else {
            loan.setRate(OTHER_LOANS);
        }
    }

    private float calculateMonthlyPayment(Loan loan) {
        return loan.getAmount() / loan.getDuration();
    }

    private float calculateInterest(Loan loan) {
        return (loan.getAmount() * loan.getRate()) / loan.getDuration();
    }

}
