package com.example.demo.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ログイン用のユーザ名、パスワードを格納するEntity
 * @author naoto.suzuki
 *
 */
@Entity
@Table(name = "login_user")
public class LoginUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String password;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public void setTitle(String password) {
		this.password = password;
	}
	
	public LoginUser() {
		super();
	}
	public LoginUser(Long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	public LoginUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", username=" + username + ", password=" + password + "]";
	}


}
