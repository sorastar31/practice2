package com.jaehwan.web.academy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaehwan.web.academy.dao.AcademyDao;
import com.jaehwan.web.academy.dao.MainMenuDao;
import com.jaehwan.web.academy.entity.Academy;

@Service
public class AcademyMemberService implements MemberService {
	@Autowired
	private AcademyDao academyDao;
	
	@Autowired
	private MainMenuDao mainMenuDao;
	
	@Override
	public Academy getAcademy(String id) {
		Academy academy = academyDao.get(id);
		
		/*List<MainMenu> mainMenus = mainMenuDao.getListByAcademyId(id);
		academy.setMainMenus(mainMenus);*/
		//service에서 service를 호출하는것은 재귀의 문제가 있어 무한루프될수도 있음
		
		return academy;
	}

}
