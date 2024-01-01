package EmployeService.Employe.entities;

import EmployeService.Employe.dto.LeaveResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Transient
    private List<LeaveResponse> conges = new ArrayList<>() ;
    private Long departmentId;
}
