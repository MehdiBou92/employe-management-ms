package Leaveservice.Leave.service;

import Leaveservice.Leave.dto.LeaveRequest;
import Leaveservice.Leave.dto.LeaveResponse;

import java.util.List;

public interface LeaveService {

    void addLeave (Long employeId , LeaveRequest leaveRequest);
    List<LeaveResponse> leaves ();
    LeaveResponse findLastLeaveByEmployeId (Long employeId);
}
