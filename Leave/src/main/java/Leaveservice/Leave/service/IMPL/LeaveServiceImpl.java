package Leaveservice.Leave.service.IMPL;

import Leaveservice.Leave.client.EmployeRestClient;
import Leaveservice.Leave.dto.EmployeResponse;
import Leaveservice.Leave.dto.LeaveRequest;
import Leaveservice.Leave.dto.LeaveResponse;
import Leaveservice.Leave.entities.Leave;
import Leaveservice.Leave.repository.LeaveRepository;
import Leaveservice.Leave.service.LeaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeRestClient employeRestClient;

    public LeaveServiceImpl(LeaveRepository leaveRepository, EmployeRestClient employeRestClient) {
        this.leaveRepository = leaveRepository;
        this.employeRestClient = employeRestClient;
    }

    @Transactional
    public void addLeave(Long employeId, LeaveRequest leaveRequest) {

        // Get the Employe for FeingClient :
        EmployeResponse employe = employeRestClient.getEmployeByIdWithLeaves(employeId);
        String emp = employe.getName();
        log.info(String.valueOf(employe.getLeaves().size()));

        // Check if the employee has ennough leave Credit :
        float requestedLeave = leaveRequest.getMaternityLeave()
                + leaveRequest.getSickLeave()
                + leaveRequest.getUnjustifiedLeave()
                + leaveRequest.getVacationLeave();
        if(employe.getLeaveCredit() > requestedLeave){


            // New Leave :
       Leave leave = Leave.builder()
                    .maternityLeave(leaveRequest.getMaternityLeave())
                    .sickLeave(leaveRequest.getSickLeave())
                    .unjustifiedLeave(leaveRequest.getUnjustifiedLeave())
                    .startLeave(LocalDate.now())
                    .duration(leaveRequest.getDuration())
                    .vacationLeave(leaveRequest.getVacationLeave())
                    .employeId(leaveRequest.getEmployeId())
                    .build();



      if(!employe.getLeaves().isEmpty()){
          // Find last Leave :
          LeaveResponse lastLeave = findLastLeaveByEmployeId(employe.getId());
          leave.setCreditLeave(lastLeave.getCreditLeave()- requestedLeave);
      } else {
          leave.setCreditLeave(employe.getLeaveCredit() - requestedLeave);
      }

        leaveRepository.save(leave);

        } else {
            throw new IllegalArgumentException("Not Ennough Leave Credit ! ");
        }
    }


    // Calculate the supplement depending on seniority :
    public float calculateSupplmentBasedOnSeniority(float seniority){

        float supplement = 0;
        if(seniority< 5){
            supplement += 1.5;
        } else if (seniority >= 5 && seniority <= 10){
            supplement += 2;
        } else {
            supplement += 2.5;
        }
        return  supplement;
    }


   @Transactional
   public void updateMonthlyLeave (){
        // Retreive All Employees :
       List<EmployeResponse> employeResponses = employeRestClient.getAllEmployees();

       for(EmployeResponse employeResponse : employeResponses){

           // Calculate the supplement
           float supplement = calculateSupplmentBasedOnSeniority(employeResponse.getSeniority());


           // Creating new Leave :
           Leave leave = new Leave();
           leave.setCreditLeave(employeResponse.getLeaveCredit() + supplement);

       }
   }

    public List<LeaveResponse> leaves() {
        // Get Leaves :
        List<Leave> leaves = leaveRepository.findAll();
        // mapToLeaveResponse :
        return leaves.stream()
                .map(this::mapToLeaveResponse)
                .toList();
    }


    public LeaveResponse findLastLeaveByEmployeId(Long id) {
        EmployeResponse employeResponse = employeRestClient.getEmployeByIdWithLeaves(id);
        Optional<Leave> lastLeave = employeResponse.getLeaves().stream()
                .max(Comparator.comparing(Leave::getId));
        return mapToLeaveResponse(lastLeave.get());
    }

    private LeaveResponse mapToLeaveResponse(Leave leave) {
        return LeaveResponse.builder()
                .id(leave.getId())
                .maternityLeave(leave.getMaternityLeave())
                .sickLeave(leave.getSickLeave())
                .startLeave(leave.getStartLeave())
                .unjustifiedLeave(leave.getUnjustifiedLeave())
                .vacationLeave(leave.getVacationLeave())
                .creditLeave(leave.getCreditLeave())
                .employeId(leave.getEmployeId())
                .build();
    }

    private Leave mapToLeave(LeaveResponse leaveResponse) {
        return Leave.builder()
                .id(leaveResponse.getId())
                .maternityLeave(leaveResponse.getMaternityLeave())
                .sickLeave(leaveResponse.getSickLeave())
                .startLeave(leaveResponse.getStartLeave())
                .unjustifiedLeave(leaveResponse.getUnjustifiedLeave())
                .vacationLeave(leaveResponse.getVacationLeave())
                .creditLeave(leaveResponse.getCreditLeave())
                .employeId(leaveResponse.getEmployeId())
                .build();
    }
}
