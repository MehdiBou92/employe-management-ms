package Leaveservice.Leave.client;

import Leaveservice.Leave.dto.EmployeResponse;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "employe-service")
public interface EmployeRestClient {
    @GetMapping("/api/employe/{employeId}")
    EmployeResponse getEmployeById(@PathVariable Long employeId);

}
