package Loanservice.Loan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeResponse {
    private Long id;
    private String name;
    private String cin;
    private Long departmentId;
    private LocalDate hiringDate;
    private float seniority;
    private List<LoanResponse> loans = new ArrayList<>();
}
