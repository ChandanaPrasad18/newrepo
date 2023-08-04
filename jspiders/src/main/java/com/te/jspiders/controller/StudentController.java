package com.te.jspiders.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.jspiders.response.SuccessResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(path = "/private/student")
@RestController
public class StudentController {

	@PutMapping(path = "/update")
	public SuccessResponse<String> update() {
		return SuccessResponse.<String>builder().data("Student update").message(null).build();
	}

	@DeleteMapping(path = "/delete")
	public SuccessResponse<String> delete() {
		return SuccessResponse.<String>builder().data("Student delete").message(null).build();
	}

	@GetMapping(path = "/get")
	public SuccessResponse<String> get() {
		return SuccessResponse.<String>builder().data("Student get").message(null).build();
	}

}
