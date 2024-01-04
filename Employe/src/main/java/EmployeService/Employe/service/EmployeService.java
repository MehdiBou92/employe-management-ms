package EmployeService.Employe.service;

import EmployeService.Employe.dto.EmployeRequest;
import EmployeService.Employe.dto.EmployeResponse;
import EmployeService.Employe.entities.Employe;

import java.util.List;

public interface EmployeService {

    void addEmploye (EmployeRequest employeRequest);

    EmployeResponse findEmployeByIdWithLeave(Long id);
    EmployeResponse findEmployeByIdWithLoans(Long id);
    EmployeResponse findEmployeById(Long id);

    List<EmployeResponse> findAllEmployees ();


}
