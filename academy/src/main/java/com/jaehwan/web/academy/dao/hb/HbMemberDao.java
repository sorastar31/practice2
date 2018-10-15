package com.jaehwan.web.academy.dao.hb;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jaehwan.web.academy.dao.MemberDao;
import com.jaehwan.web.academy.entity.Member;



@Repository
public class HbMemberDao implements MemberDao {

	@Autowired
	private SessionFactory SessionFactory;

	@Override
	@Transactional
	public Member get(String id) {
		Session session = SessionFactory.getCurrentSession();
		Member member = session.get(Member.class, id);
		return member;
	}
	

}
