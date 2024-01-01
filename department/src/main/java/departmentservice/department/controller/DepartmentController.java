package departmentservice.department.controller;

import departmentservice.department.dto.DepartmentRequest;
import departmentservice.department.dto.DepartmentResponse;
import departmentservice.department.service.DepartmentService;
import jakarta.ws.rs.Path;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<String> addDepartment (@RequestBody DepartmentRequest departmentRequest){
        departmentService.addDepartment(departmentRequest);
        return ResponseEntity.ok("Department Added.");
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponse> findDepartmentById (@PathVariable Long departmentId){
        DepartmentResponse response = departmentService.findDepartmentById(departmentId);
        return ResponseEntity.ok(response);
    }



}
