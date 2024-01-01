package Leaveservice.Leave.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_leave")
@Builder
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float maternityLeave;
    private LocalDate startLeave;
    private float duration;
    private float sickLeave;
    private float unjustifiedLeave;
    private float vacationLeave;
    private float creditLeave;
    private Long employeId;

}
