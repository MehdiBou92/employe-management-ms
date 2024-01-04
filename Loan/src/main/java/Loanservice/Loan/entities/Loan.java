package Loanservice.Loan.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_loan")
@Builder
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float amount;
    private Float rate;
    private Float interest;
    private Long duration;
    private Float monthlyPayment;
    private LocalDate startLoanDate;
    private LocalDate endLoanDate;
    private Float totalRefund;
    private boolean statut;
    private Long employeId;

}
