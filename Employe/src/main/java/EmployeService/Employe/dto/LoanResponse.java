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
    private Integer amount;
    private float rate;
    private Integer interest;
    private Integer duration;
    private Integer monthlyPayment;
    private LocalDate startLoanDate;
    private LocalDate endLoanDate;

}
