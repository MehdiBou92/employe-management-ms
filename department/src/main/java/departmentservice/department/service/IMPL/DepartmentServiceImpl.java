package departmentservice.department.service.IMPL;

import departmentservice.department.client.EmployeRestClient;
import departmentservice.department.dto.DepartmentResponse;
import departmentservice.department.dto.DepartmentRequest;
import departmentservice.department.dto.EmployeResponse;
import departmentservice.department.entities.Department;
import departmentservice.department.repository.DepartmentRepository;
import departmentservice.department.service.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeRestClient employeRestClient;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EmployeRestClient employeRestClient) {
        this.departmentRepository = departmentRepository;
        this.employeRestClient = employeRestClient;
    }

    public void addDepartment(DepartmentRequest departmentRequest) {
        Department department = Department.builder()
                .departmentName(departmentRequest.getDepartmentName())
                .build();
        department.setEmployees(new ArrayList<>());
        departmentRepository.save(department);
    }


    @Transactional
    public DepartmentResponse findDepartmentById(Long id) {
        // Find the Department within the same transactional context:
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department does not exist"));

        // Find Employees within the same transactional context:
        List<EmployeResponse> employees = employeRestClient.findAllEmployees();

        // Filter and insert only employees with matching departmentId:
        employees.stream()
                .filter(employeResponse -> employeResponse.getDepartmentId().equals(department.getId()))
                .forEach(employeResponse -> department.getEmployees().add(employeResponse));

        return DepartmentResponse.builder()
                .id(department.getId())
                .departmentName(department.getDepartmentName())
                .employees(department.getEmployees())
                .build();
    }



}
