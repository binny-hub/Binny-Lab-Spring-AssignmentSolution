package com.greatlearning.studentManagement.repository;

import com.greatlearning.studentManagement.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users, Integer> {
     
	//Classname 
	 @Query("select u from Users u where u.username= ?1")
	 public Users getUserByUsername(String username); //class variable
	 
}
