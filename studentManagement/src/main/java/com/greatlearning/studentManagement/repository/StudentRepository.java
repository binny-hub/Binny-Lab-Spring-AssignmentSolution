package com.greatlearning.studentManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.studentManagement.entity.*;

public interface StudentRepository extends JpaRepository<Student,Integer> {
	List <Student> findByfirstNameContainsAndDepartmentContainsAllIgnoreCase(String firstName, String department);
        
}
