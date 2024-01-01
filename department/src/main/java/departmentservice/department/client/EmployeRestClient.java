package departmentservice.department.client;

import departmentservice.department.dto.EmployeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "employe-service") // Use the name, not the full URL
public interface EmployeRestClient {
    @GetMapping("/api/employe/all") // Include the full path
    List<EmployeResponse> findAllEmployees();
}
