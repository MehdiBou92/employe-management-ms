package Leaveservice.Leave.service;

import Leaveservice.Leave.dto.LeaveRequest;
import Leaveservice.Leave.dto.LeaveResponse;

import java.util.List;
import java.util.Optional;

public interface LeaveService {

    void addLeave (Long employeId , LeaveRequest leaveRequest);
    List<LeaveResponse> leaves ();
    Optional<LeaveResponse> findLastLeaveByEmployeId (Long employeId);
}
