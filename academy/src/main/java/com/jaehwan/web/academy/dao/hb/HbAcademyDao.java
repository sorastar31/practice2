package com.jaehwan.web.academy.dao.hb;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jaehwan.web.academy.dao.AcademyDao;
import com.jaehwan.web.academy.entity.Academy;

@Repository
public class HbAcademyDao implements AcademyDao {

	@Autowired
	private SessionFactory SessionFactory;
	
	@Override
	@Transactional
	public int insert(Academy academy) {
		Session session = SessionFactory.getCurrentSession();
		Object id = session.save(academy);
		if(id != null)
			return 1;
		return 0;
	}

	@Override
	@Transactional
	public int update(Academy academy) {
		Session session = SessionFactory.getCurrentSession();
		session.update(academy);
		return 1;
	}

	@Override
	@Transactional
	public int delete(String id) {
		Session session = SessionFactory.getCurrentSession();
		Academy academy = new Academy();
		academy.setId(id);
		session.remove(academy);
		return 1;
	}

	@Override
	@Transactional
	public Academy get(String id) {
		Session session = SessionFactory.getCurrentSession();
		Academy academy = session.get(Academy.class, id);
		return academy;
	}

	@Override
	@Transactional
	public List<Academy> getList() {
		Session session = SessionFactory.getCurrentSession();
		String hql = "from Academy";
		List<Academy> list = session
								.createQuery(hql, Academy.class)
								.getResultList();
		return list;
	}

}
