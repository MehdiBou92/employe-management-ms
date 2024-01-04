package Loanservice.Loan.client;

import Loanservice.Loan.dto.EmployeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employe-service")
public interface EmployeRestClient {

    @GetMapping("/api/employe/{employeId}/loans")
    EmployeResponse findEmployeByIdWithLoans(@PathVariable Long employeId);

}
