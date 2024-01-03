package EmployeService.Employe.service.Impl;

import EmployeService.Employe.client.LeaveRestClient;
import EmployeService.Employe.dto.EmployeRequest;
import EmployeService.Employe.dto.EmployeResponse;
import EmployeService.Employe.dto.LeaveResponse;
import EmployeService.Employe.entities.Employe;
import EmployeService.Employe.repository.EmployeRepository;
import EmployeService.Employe.service.EmployeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeServiceImpl implements EmployeService {

    private final EmployeRepository employeRepository;
    private final LeaveRestClient leaveRestClient;


    public EmployeServiceImpl(EmployeRepository employeRepository, LeaveRestClient leaveRestClient) {
        this.employeRepository = employeRepository;
        this.leaveRestClient = leaveRestClient;
    }

    public void addEmploye(EmployeRequest employeRequest) {
        // Create the Employee :
        Employe employe = Employe.builder()
                .cin(employeRequest.getCin())
                .name(employeRequest.getName())
                .departmentId(employeRequest.getDepartmentId())
                .hiringDate(employeRequest.getHiringDate())
                .build();
        employeRepository.save(employe);
    }

    public EmployeResponse findEmployeById(Long id) {
        // Retreive the Employe :
        Employe employe = employeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        // Find Leaves :
        List<LeaveResponse> leaves = leaveRestClient.leaves();


            leaves.stream()
                    .filter(leaveResponse -> leaveResponse.getEmployeId().equals(employe.getId()))
                    .forEach(leaveResponse -> employe.getConges().add(leaveResponse));

            // Get Employe Last Leave  & Update Employee
        if(!employe.getConges().isEmpty()){
            Optional<LeaveResponse> lastLeave = employe.getConges().stream()
                    .max(Comparator.comparing(LeaveResponse::getId));
            employe.setLeaveCredit(lastLeave.get().getCreditLeave());
        }

        return mapToEmployeDto(employe);
    }


    public List<EmployeResponse> findAllEmployees() {
        List<Employe> employes = employeRepository.findAll();
        List<LeaveResponse> leaves = leaveRestClient.leaves();
        for(Employe e : employes){
            for(LeaveResponse l : leaves){
                if(e.getId().equals(l.getEmployeId())){
                    e.getConges().add(l);
                    e.getConges().sort(Comparator.comparing(LeaveResponse::getId).reversed());
                    e.setLeaveCredit(e.getConges().get(0).getCreditLeave());
                }
            }
        }

        return employes.stream()
                .map(this::mapToEmployeDto)
                .toList();
    }
        private EmployeResponse mapToEmployeDto(Employe employe) {
        return EmployeResponse.builder()
                .id(employe.getId())
                .name(employe.getName())
                .cin(employe.getCin())
                .leaves(employe.getConges())
                .leaveCredit(employe.getLeaveCredit())
                .hiringDate(employe.getHiringDate())
                .seniority(employe.getSeniority())
                .departmentId(employe.getDepartmentId())
                .build();
    }

    private Employe mapDtoToEmploye (EmployeResponse employeResponse) {
        return Employe.builder()
                .id(employeResponse.getId())
                .name(employeResponse.getName())
                .cin(employeResponse.getCin())
                .conges(employeResponse.getLeaves())
                .leaveCredit(employeResponse.getLeaveCredit())
                .departmentId(employeResponse.getDepartmentId())
                .build();
    }
}







