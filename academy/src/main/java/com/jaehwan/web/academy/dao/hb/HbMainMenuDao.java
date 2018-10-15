package com.jaehwan.web.academy.dao.hb;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jaehwan.web.academy.dao.MainMenuDao;
import com.jaehwan.web.academy.entity.MainMenu;

@Repository
public class HbMainMenuDao implements MainMenuDao{

	@Autowired
	private SessionFactory SessionFactory;
	
	@Override
	@Transactional
	public int insert(MainMenu mainMenu) {
		Session session = SessionFactory.getCurrentSession();
		Object id = session.save(mainMenu);
		
		if(id != null)
			return 1;
		return 0;
	}

	@Override
	@Transactional
	public int update(MainMenu mainMenu) {
		Session session = SessionFactory.getCurrentSession();
		session.update(mainMenu);
		return 1;
	}

	@Override
	@Transactional
	public int delete(int id) {
		Session session = SessionFactory.getCurrentSession();
		MainMenu mainMenu = new MainMenu();
		mainMenu.setId(id);
		session.remove(mainMenu);
		return 1;
	}

	@Override
	@Transactional
	public MainMenu get(int id) {
		Session session = SessionFactory.getCurrentSession();
		MainMenu mainMenu = session.get(MainMenu.class, id);
		return mainMenu;
	}

	@Override
	@Transactional
	public List<MainMenu> getList() {
		Session session = SessionFactory.getCurrentSession();
		String hql = "from MainMenu";
		List<MainMenu> list = session
								.createQuery(hql, MainMenu.class)
								.getResultList();
		return list;
	}

	@Override
	@Transactional
	public int deleteList(Integer[] menuIds) {
		Session session = SessionFactory.getCurrentSession();
		/*delete 
		from MainMenu
		where id IN (1,2,3)*/
		String hql = "delete MainMenu where id in (:ids)";
		Query query = session
							.createQuery(hql)
							.setParameterList("ids", menuIds);
		int result = query.executeUpdate();
		return result;
	}

	@Override
	@Transactional
	public List<MainMenu> getListByAcademyId(String id) {
		Session session = SessionFactory.getCurrentSession();
		String hql = "from MainMenu where academyId = :id";
		List<MainMenu> list = session
									.createQuery(hql, MainMenu.class)
									.setParameter("id", id)
									.getResultList();
		return list;
	}

}
