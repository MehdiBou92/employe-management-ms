package Loanservice.Loan.controller;

import Loanservice.Loan.dto.LoanRequest;
import Loanservice.Loan.dto.LoanResponse;
import Loanservice.Loan.service.LoanService;
import jakarta.ws.rs.Path;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/{employeId}")
    public ResponseEntity<String> addLoanToEmploye (@PathVariable Long employeId, @RequestBody LoanRequest loanRequest){
        loanService.addLoan(employeId,loanRequest);
       return ResponseEntity.ok("Loan Added !");
    }

    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAllLoans () {
        return ResponseEntity.ok(loanService.getAllLoans());
    }
}
