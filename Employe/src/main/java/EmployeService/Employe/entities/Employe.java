package EmployeService.Employe.entities;

import EmployeService.Employe.dto.LeaveResponse;
import EmployeService.Employe.dto.LoanResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "t_employe")
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cin;
    private float leaveCredit;
    private LocalDate hiringDate;
    private float seniority;
    @Transient
    private List<LeaveResponse> conges = new ArrayList<>() ;
    private Long departmentId;
    @Transient
    private List<LoanResponse> loans = new ArrayList<>();



    @PrePersist
    @PreUpdate
    public void calculateSeniority () {
       LocalDate now = LocalDate.now();
       this.seniority = now.getYear() - this.getHiringDate().getYear();
    }
}
