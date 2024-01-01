package Leaveservice.Leave.controller;

import Leaveservice.Leave.dto.LeaveRequest;
import Leaveservice.Leave.dto.LeaveResponse;
import Leaveservice.Leave.entities.Leave;
import Leaveservice.Leave.service.LeaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
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
}
