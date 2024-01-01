package Leaveservice.Leave.service.IMPL;

import Leaveservice.Leave.client.EmployeRestClient;
import Leaveservice.Leave.dto.EmployeResponse;
import Leaveservice.Leave.dto.LeaveRequest;
import Leaveservice.Leave.dto.LeaveResponse;
import Leaveservice.Leave.entities.Leave;
import Leaveservice.Leave.repository.LeaveRepository;
import Leaveservice.Leave.service.LeaveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeRestClient employeRestClient;

    public LeaveServiceImpl(LeaveRepository leaveRepository, EmployeRestClient employeRestClient) {
        this.leaveRepository = leaveRepository;
        this.employeRestClient = employeRestClient;
    }

//    @Transactional
    public void addLeave(Long employeId, LeaveRequest leaveRequest) {

        // Get the Employe for FeingClient :
        EmployeResponse employe = employeRestClient.getEmployeById(employeId);
        String emp = employe.getName();

        // Check if the employee has ennough leave Credit :
        float requestedLeave = leaveRequest.getMaternityLeave()
                + leaveRequest.getSickLeave()
                + leaveRequest.getUnjustifiedLeave()
                + leaveRequest.getVacationLeave();
        if(employe.getLeaveCredit() > requestedLeave){

        // Retreive the last Leave :
        List<Leave> leaves = leaveRepository.findAll();
            Optional<Leave> lastLeave = leaves.stream()
                    .max(Comparator.comparing(Leave::getStartLeave));
         if(!lastLeave.isPresent()){
             lastLeave.get().setCreditLeave(employe.getLeaveCredit());
         }

            // New Leave :
       Leave leave = Leave.builder()
                    .maternityLeave(leaveRequest.getMaternityLeave())
                    .sickLeave(leaveRequest.getSickLeave())
                    .unjustifiedLeave(leaveRequest.getUnjustifiedLeave())
                    .startLeave(LocalDate.now())
                    .duration(leaveRequest.getDuration())
                    .vacationLeave(leaveRequest.getVacationLeave())
                    .employeId(leaveRequest.getEmployeId())
                    .creditLeave(lastLeave.get().getCreditLeave())
                    .build();

       // Updating Credit Leave :
       leave.setCreditLeave(leave.getCreditLeave() - requestedLeave);
        leaveRepository.save(leave);

        } else {
            throw new IllegalArgumentException("Not Ennough Leave Credit ! ");
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
}
