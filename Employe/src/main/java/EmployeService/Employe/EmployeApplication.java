package EmployeService.Employe;

import EmployeService.Employe.entities.Employe;
import EmployeService.Employe.repository.EmployeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
@EnableFeignClients
public class EmployeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner (EmployeRepository employeRepository){
		return args -> {
			Employe employe = Employe.builder()
					.name("Mehdi")
					.cin("A5T")
					.leaveCredit(15F)
					.departmentId(1L)
					.hiringDate(LocalDate.of(2022,07,07))
					.build();
			employeRepository.save(employe);

			Employe employe2 = Employe.builder()
					.name("Bibich")
					.cin("AAAA45")
					.departmentId(2L)
					.leaveCredit(10f)
					.hiringDate(LocalDate.of(2015,05,01))
					.build();
			employeRepository.save(employe2);

			Employe employe3 = Employe.builder()
					.name("Inocha")
					.cin("A5T")
					.departmentId(1L)
					.leaveCredit(10f)
					.hiringDate(LocalDate.of(2020,10,01))
					.build();
			employeRepository.save(employe3);
		};
	}

}
