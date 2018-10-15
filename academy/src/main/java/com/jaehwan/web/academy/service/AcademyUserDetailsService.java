package com.jaehwan.web.academy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jaehwan.web.academy.dao.MemberDao;
import com.jaehwan.web.academy.dao.MemberRoleDao;
import com.jaehwan.web.academy.entity.Member;
import com.jaehwan.web.academy.entity.MemberRole;

@Service("userDetailsService")
public class AcademyUserDetailsService implements UserDetailsService {

	@Autowired
	MemberDao memberDao;
	
	@Autowired
	MemberRoleDao memberRoleDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		Member member = memberDao.get(username);
		
		List<MemberRole> memberRoles = memberRoleDao.getListByMemberId(username);
		
		UserBuilder builder = null;
		String[] authorities = memberRoles
									.stream()
									.map(mr->mr.getRoleName())
									.toArray(String[]::new);
		builder = User.withUsername(username);
		builder.password(member.getPassword());
		builder.authorities(authorities);
		
		
		return builder.build();
	}

}
