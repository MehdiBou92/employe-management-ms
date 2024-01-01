package EmployeService.Employe.client;

import EmployeService.Employe.dto.LeaveResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "leave-service")
public interface LeaveRestClient {
    @GetMapping("/api/leave")
    List<LeaveResponse> leaves ();

}
