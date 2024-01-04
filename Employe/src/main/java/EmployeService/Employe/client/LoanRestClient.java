package EmployeService.Employe.client;

import EmployeService.Employe.dto.LoanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "loan-service")
public interface LoanRestClient {

    @GetMapping("/api/loan")
    List<LoanResponse> getLoans ();
}
