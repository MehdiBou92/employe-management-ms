package EmployeService.Employe.client;

import EmployeService.Employe.dto.LeaveResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "leave-service")
public interface LeaveRestClient {
    @GetMapping("/api/leave")
    List<LeaveResponse> leaves ();

    @GetMapping("/api/leave/last/{id}")
    Optional<LeaveResponse> getLastLeave (@PathVariable Long id);

}
