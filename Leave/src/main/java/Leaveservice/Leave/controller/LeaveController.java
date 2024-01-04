package Leaveservice.Leave.controller;

import Leaveservice.Leave.client.EmployeRestClient;
import Leaveservice.Leave.dto.LeaveRequest;
import Leaveservice.Leave.dto.LeaveResponse;
import Leaveservice.Leave.repository.LeaveRepository;
import Leaveservice.Leave.service.LeaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {

    private final LeaveService leaveService;
    private final LeaveRepository leaveRepository;

    public LeaveController(LeaveService leaveService, LeaveRepository leaveRepository) {
        this.leaveService = leaveService;
        this.leaveRepository = leaveRepository;
    }

    @PostMapping("/{employeId}")
    public ResponseEntity<String> addLeaveToEmploye (@PathVariable Long employeId, @RequestBody LeaveRequest leaveRequest){
        leaveService.addLeave(employeId,leaveRequest);
        return ResponseEntity.ok("Leave Added ! ");
    }

    @GetMapping
    public ResponseEntity<List<LeaveResponse>> getAllLeaves () {
        return ResponseEntity.ok(leaveService.leaves());
    }

    @GetMapping("/last/{id}")
    public ResponseEntity<LeaveResponse> getLastLeave (@PathVariable Long id){
        return ResponseEntity.ok(leaveService.findLastLeaveByEmployeId(id));
    }
}
