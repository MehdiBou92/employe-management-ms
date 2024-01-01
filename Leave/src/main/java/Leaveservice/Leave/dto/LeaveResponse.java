package Leaveservice.Leave.dto;

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
    private float unjustifiedLeave;
    private float vacationLeave;
    private LocalDate startLeave;
    private float creditLeave;
    private float duration;
    private Long employeId;
}
