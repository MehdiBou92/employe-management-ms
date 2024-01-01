package departmentservice.department.dto;

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
public class DepartmentResponse {

    private Long id;
    private String departmentName;
    private List<EmployeResponse> employees = new ArrayList<>();
}
