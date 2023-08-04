package com.te.jspiders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.jspiders.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
