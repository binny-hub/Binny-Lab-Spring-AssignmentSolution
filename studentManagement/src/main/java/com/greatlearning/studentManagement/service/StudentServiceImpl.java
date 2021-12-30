package com.greatlearning.studentManagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatlearning.studentManagement.entity.Student;
import com.greatlearning.studentManagement.repository.StudentRepository;


@Repository
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	
	StudentRepository studentRepository;

	@Override
	@Transactional
	public List<Student> findAll() {
		List<Student> students= studentRepository.findAll();
		return students;
	}

	@Override
	@Transactional
	public Student findById(int id) {
		Student student = new Student();
	    student = studentRepository.findById(id).get();
		return student;
	}

	@Override
	//@Transactional
	public void save(Student student) {
		studentRepository.save(student);
	}

	@Override
	//@Transactional
	public void deleteById(int id) {
		studentRepository.deleteById(id);
	}

	@Override
	public List<Student> searchBy(String firstName, String department) {
		List <Student> student= studentRepository.findByfirstNameContainsAndDepartmentContainsAllIgnoreCase(firstName, department);
	    return student;
	}
	
	

}
