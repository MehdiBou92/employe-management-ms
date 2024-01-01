package departmentservice.department.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeResponse {


    private Long id;
    private String name;
    private String cin;
    private Long departmentId;
}
