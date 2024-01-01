package EmployeService.Employe.controller;

import EmployeService.Employe.dto.EmployeRequest;
import EmployeService.Employe.dto.EmployeResponse;
import EmployeService.Employe.service.EmployeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employe")
public class EmployeController {

    private final  EmployeService employeService;

    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @PostMapping
    public ResponseEntity<String> addEmploye (@RequestBody EmployeRequest employeRequest){
        employeService.addEmploye(employeRequest);
        return ResponseEntity.ok("Employe Added Sucessefully");
    }

    @GetMapping("/{employeId}")
    public ResponseEntity<EmployeResponse> findEmployeById (@PathVariable Long employeId){
        EmployeResponse employeResponse = employeService.findEmployeById(employeId);
        return ResponseEntity.ok(employeResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeResponse>> findAllEmployees (){
        return ResponseEntity.ok(employeService.findAllEmployees());
    }


}
