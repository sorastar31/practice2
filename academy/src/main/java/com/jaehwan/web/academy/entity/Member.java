package com.jaehwan.web.academy.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name="User")
public class Member {
	
	@Id
	private String id;
	private String name;
	private String email;
	@Column(name="pwd")
	private String password;
	@Column(insertable=false)
	private Date regDate;
	//@Column(name="picture")
	private String photo;
	
	public Member() {
		
	}
	
	public Member(String id, String name, String email, String password, Date regDate,String photo) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.regDate = regDate;
		this.photo = photo;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", regDate=" + regDate
				+ ", photo=" + photo + "]";
	}

	
}
