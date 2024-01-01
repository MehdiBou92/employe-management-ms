package Leaveservice.Leave.dto;

import Leaveservice.Leave.entities.Leave;
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
public class EmployeResponse {
    private Long id;
    private String name;
    private String cin;
    private Long departmentId;
    private Float leaveCredit;
    private List<Leave> leaves = new ArrayList<>();
}
