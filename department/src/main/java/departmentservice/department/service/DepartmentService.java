package departmentservice.department.service;

import departmentservice.department.dto.DepartmentResponse;
import departmentservice.department.dto.DepartmentRequest;

public interface DepartmentService {

    void addDepartment (DepartmentRequest departmentRequest);
    DepartmentResponse findDepartmentById (Long id);


}
