package Leaveservice.Leave.repository;

import Leaveservice.Leave.entities.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave,Long> {
}
