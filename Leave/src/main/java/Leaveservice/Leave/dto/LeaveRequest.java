package Leaveservice.Leave.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveRequest {

    private float maternityLeave;
    private float sickLeave;
    private float unjustifiedLeave;
    private LocalDate startLeave;
    private float duration;
    private float vacationLeave;
    private Long employeId;

}
