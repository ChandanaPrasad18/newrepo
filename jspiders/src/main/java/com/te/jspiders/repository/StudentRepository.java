package com.te.jspiders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.jspiders.entity.Student;

public interface StudentRepository extends JpaRepository<Student, String>{

}
