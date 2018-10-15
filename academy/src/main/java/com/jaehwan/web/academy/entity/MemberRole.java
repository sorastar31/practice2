package com.jaehwan.web.academy.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MemberRole {
	
	@EmbeddedId
	private MemberRoleId id;
	/*private String memberId;
	private String roleName;*/
	
	@Column(insertable=false)
	private boolean defaultRole;

	public MemberRole() {
		
	}
	
	public MemberRole(String memberId, String roleName, boolean defaultRole) {
		super();
		this.id = new MemberRoleId(memberId, roleName);
		/*this.memberId = memberId;
		this.roleName = roleName;*/
		this.defaultRole = defaultRole;
	}
	
	public boolean getDefaultRole() {
		return defaultRole;
	}
	
	public void setDefaultRole(boolean defaultRole) {
		this.defaultRole = defaultRole;
	}
	
	public String getMemberId() {
		return id.getMemberId();
	}
	public void setMemberId(String memberId) {
		this.id.setMemberId(memberId);
	}
	public String getRoleName() {
		return id.getRoleName();
	}
	public void setRoleName(String roleName) {
		this.id.setRoleName(roleName);
	}

	@Override
	public String toString() {
		return "MemberRole [id=" + id + ", defaultRole=" + defaultRole + "]";
	}

	
	
	
}

