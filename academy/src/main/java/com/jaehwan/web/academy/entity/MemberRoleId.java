package com.jaehwan.web.academy.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MemberRoleId implements Serializable {
	
	private String memberId;
	private String roleName;
	
	public MemberRoleId() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberRoleId(String memberId, String roleName) {
		super();
		this.memberId = memberId;
		this.roleName = roleName;
	}


	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
