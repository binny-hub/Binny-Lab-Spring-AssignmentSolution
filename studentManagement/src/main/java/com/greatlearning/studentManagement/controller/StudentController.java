package com.greatlearning.studentManagement.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.studentManagement.entity.Student;
import com.greatlearning.studentManagement.service.StudentService;


import java.util.*;

@Controller
@RequestMapping("/student")

public class StudentController {
	
	@Autowired
	private StudentService studentService;

	@RequestMapping("/list")
	public String listStudents(Model model) {
		List<Student> students = studentService.findAll();
		model.addAttribute("Student", students);
		return "list-student";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Student student = new Student();
		model.addAttribute("Student", student);
		return "student-form";
	}

	@RequestMapping("/search")
	public String search(@RequestParam("firstName") String firstName,
			@RequestParam("department") String department,
			Model theModel) {

		// check names, if both are empty then just give list of all students

		if (firstName.trim().isEmpty() || department.trim().isEmpty()) {
			return "redirect:/student/list";
		}
		else {
			// else, search by first name and department
			List <Student> student = studentService.searchBy(firstName, department);

			// add to the spring model
			theModel.addAttribute("Student", student);

			// send to list-Books
			return "list-student";
		}
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int id, Model model) {
		//Optional<Student> student = new Student();
		Student student = studentService.findById(id);
		model.addAttribute("Student", student);
		return "student-form";}

@PostMapping("/save")
	public String saveStudent(@RequestParam("studentId") int id, @RequestParam("firstName") String fName,
			@RequestParam("lastName") String lName, @RequestParam("department") String department,
			@RequestParam("country") String country) {

		System.out.println(id);
		Student student;

		if (id != 0) {
			student = studentService.findById(id);
			student.setFirstName(fName);
			student.setLastName(lName);
			student.setDepartment(department);
			student.setCountry(country);
		} else {
			student = new Student(fName, lName, department, country);

		}
		studentService.save(student);
		return "redirect:/student/list";

	}

	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int id) {
		studentService.deleteById(id);
		return "redirect:/student/list";
	}

	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			"You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}

}
