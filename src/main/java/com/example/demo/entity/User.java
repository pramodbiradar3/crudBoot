package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="User")
public class User {
	private String userName;
	private String loginName;
	private String password;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userId;

	
	public User() {}
	
	public User(String loginName,String userName,String password) {
		this.loginName=loginName;
		this.userName=userName;
		this.password=password;
	}
}
