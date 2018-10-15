package com.jaehwan.web.academy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jaehwan.web.academy.entity.MemberRole;



public interface MemberRoleDao {

	List<MemberRole> getListByMemberId(String memberId);
	
}
