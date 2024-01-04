package Leaveservice.Leave.client;

import Leaveservice.Leave.dto.EmployeResponse;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "employe-service")
public interface EmployeRestClient {
    @GetMapping("/api/employe/{employeId}/leaves")
    EmployeResponse getEmployeByIdWithLeaves(@PathVariable Long employeId);

    @GetMapping("/api/employe/all")
    List<EmployeResponse> getAllEmployees();


}
