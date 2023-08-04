package com.te.jspiders.service;

import com.te.jspiders.dto.EmployeeDTO;
import com.te.jspiders.dto.StudentDTO;
import com.te.jspiders.dto.TrainerDTO;

public interface JspidersService {

	int employeeRegister(EmployeeDTO employeeDTO);

	int trainerRegister(TrainerDTO trainerDTO);

	int studentRegister(StudentDTO studentDTO);

}
