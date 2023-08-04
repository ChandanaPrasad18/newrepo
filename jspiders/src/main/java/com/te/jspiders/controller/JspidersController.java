package com.te.jspiders.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.jspiders.dto.EmployeeDTO;
import com.te.jspiders.dto.LoginDTO;
import com.te.jspiders.dto.StudentDTO;
import com.te.jspiders.dto.TrainerDTO;
import com.te.jspiders.response.SuccessResponse;
import com.te.jspiders.security.utils.JwtUtils;
import com.te.jspiders.service.JspidersService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(path = "/public")
@RestController
public class JspidersController {

	private final JspidersService qspidersService;
	private final JwtUtils jwtUtils;
	private final AuthenticationManager authenticationManager;

	@PostMapping(path = "/employee/register")
	public SuccessResponse<Integer> employeeRegister(@RequestBody EmployeeDTO employeeDTO) {
		return SuccessResponse.<Integer>builder().data(qspidersService.employeeRegister(employeeDTO)).message(null)
				.build();
	}

	@PostMapping(path = "/trainer/register")
	public SuccessResponse<Integer> trainerRegister(@RequestBody TrainerDTO trainerDTO) {
		return SuccessResponse.<Integer>builder().data(qspidersService.trainerRegister(trainerDTO)).message(null)
				.build();
	}

	@PostMapping(path = "/student/register")
	public SuccessResponse<Integer> studentRegister(@RequestBody StudentDTO studentDTO) {
		return SuccessResponse.<Integer>builder().data(qspidersService.studentRegister(studentDTO)).message(null)
				.build();
	}

	@PostMapping(path = "/login")
	public SuccessResponse<String> login(@RequestBody LoginDTO loginDTO) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
																					loginDTO.getPassword()));
		
		return SuccessResponse.<String>builder().data(null).token(jwtUtils.generateToken(loginDTO.getUsername()))
				.message(null).build();
	}

}
