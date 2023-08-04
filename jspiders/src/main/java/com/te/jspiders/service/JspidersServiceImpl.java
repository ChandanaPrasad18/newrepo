package com.te.jspiders.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.te.jspiders.dto.EmployeeDTO;
import com.te.jspiders.dto.StudentDTO;
import com.te.jspiders.dto.TrainerDTO;
import com.te.jspiders.entity.AppUser;
import com.te.jspiders.entity.Employee;
import com.te.jspiders.entity.Role;
import com.te.jspiders.entity.Student;
import com.te.jspiders.entity.Trainer;
import com.te.jspiders.repository.AppUserRepository;
import com.te.jspiders.repository.EmployeeRepository;
import com.te.jspiders.repository.RoleRepository;
import com.te.jspiders.repository.StudentRepository;
import com.te.jspiders.repository.TrainerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JspidersServiceImpl implements JspidersService {

	private final EmployeeRepository employeeRepository;
	private final AppUserRepository appUserRepository;
	private final RoleRepository roleRepository;
	private final TrainerRepository trainerRepository;
	private final StudentRepository studentRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public int employeeRegister(EmployeeDTO employeeDTO) {
		Employee employee = Employee.builder().employeeName(employeeDTO.getEmployeeName()).build();

		AppUser appUser = AppUser.builder().roles(Lists.newArrayList()).username(employeeDTO.getUsername())
				.password(passwordEncoder.encode(employeeDTO.getPassword())).build();

		Role role = roleRepository.findByRoleName("ROLE_EMPLOYEE")
				.orElseThrow(() -> new RuntimeException("role already exists"));

		appUser.getRoles().add(role);
		role.getAppUsers().add(appUser);
		appUserRepository.save(appUser);

		return employeeRepository.save(employee).getEmployeeId();
	}

	@Override
	public int trainerRegister(TrainerDTO trainerDTO) {
		Trainer trainer = Trainer.builder().trainerName(trainerDTO.getTrainerName()).build();

		AppUser appUser = AppUser.builder().roles(Lists.newArrayList()).username(trainerDTO.getUsername())
				.password(passwordEncoder.encode(trainerDTO.getPassword())).build();

		Role role = roleRepository.findByRoleName("ROLE_TRAINER")
				.orElseThrow(() -> new RuntimeException("role already exists"));

		appUser.getRoles().add(role);
		role.getAppUsers().add(appUser);
		appUserRepository.save(appUser);
		return trainerRepository.save(trainer).getTrainerId();
	}

	@Override
	public int studentRegister(StudentDTO studentDTO) {
		Student student = Student.builder().studentName(studentDTO.getStudentName()).build();

		AppUser appUser = AppUser.builder().roles(new ArrayList<>()).username(studentDTO.getUsername())
				.password(passwordEncoder.encode(studentDTO.getPassword())).build();

		Role role = roleRepository.findByRoleName("ROLE_STUDENT")
				.orElseThrow(() -> new RuntimeException("role already exists"));

		appUser.getRoles().add(role);
		role.getAppUsers().add(appUser);
		appUserRepository.save(appUser);

		return studentRepository.save(student).getStudentId();
	}

}
