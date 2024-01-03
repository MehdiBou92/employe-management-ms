package Leaveservice.Leave;

import Leaveservice.Leave.entities.Leave;
import Leaveservice.Leave.repository.LeaveRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
@EnableFeignClients
public class LeaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaveApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner (LeaveRepository leaveRepository){
		return args -> {
			Leave leave = Leave.builder()
					.sickLeave(2f)
					.startLeave(LocalDate.of(2022,05,07))
					.employeId(1L)
					.creditLeave(13f)
					.build();

			Leave leave2 = Leave.builder()
					.unjustifiedLeave(3f)
					.startLeave(LocalDate.of(2023,05,07))
					.employeId(1L)
					.creditLeave(10f)
					.build();

			Leave leave3 = Leave.builder()
					.unjustifiedLeave(3f)
					.startLeave(LocalDate.of(2022,05,10))
					.employeId(2L)
					.build();
//			leaveRepository.save(leave);
//			leaveRepository.save(leave2);
//			leaveRepository.save(leave3);

		};
	}

}
