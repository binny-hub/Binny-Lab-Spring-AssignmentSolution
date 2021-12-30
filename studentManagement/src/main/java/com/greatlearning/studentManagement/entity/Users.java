package com.greatlearning.studentManagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "username")
	private String username;
	
	
	@Column(name = "password")
	private String password;
	
	
	
    public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public List<Role> getRole() {
		return roles;
	}



	public void setRole(List<Role> role) {
		this.roles = role;
	}


	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType. EAGER)
    @JoinTable(name = "users_roles",
        joinColumns = @JoinColumn(name ="user_id"),
        inverseJoinColumns=@JoinColumn(name="role_Id"))
    
    private List<Role> roles = new ArrayList<>();
    
}
