package model;
import java.io.Serializable;

// Java Beans
public class user_info implements Serializable{
	private String name;
	private String password;

	public user_info(){
	}
	public user_info(String name, String password){
		this.name = name;
		this.password = password;
	}

	// getter とsetter の実装
	public String getName(){
		return this.name;
	}
	public String getPassword(){
		return this.password;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setPassword(String password){
		this.password = password;
	}
}
