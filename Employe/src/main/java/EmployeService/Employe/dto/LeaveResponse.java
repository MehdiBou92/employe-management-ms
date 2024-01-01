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
public class LeaveResponse {

    private Long id;
    private float maternityLeave;
    private float sickLeave;
    private LocalDate startLeave;
    private float duration;
    private float unjustifiedLeave;
    private float vacationLeave;
    private float creditLeave;
    private Long employeId;
}
