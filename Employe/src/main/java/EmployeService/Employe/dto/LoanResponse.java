package EmployeService.Employe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanResponse {

    private Long id;
    private Float amount;
    private Float rate;
    private Float interest;
    private Long duration;
    private Float monthlyPayment;
    private LocalDate startLoanDate;
    private LocalDate endLoanDate;
    private Long employeId;
    private boolean statut;

}
