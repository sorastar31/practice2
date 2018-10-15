package com.jaehwan.web.academy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jaehwan.web.academy.dao.MainMenuDao;
import com.jaehwan.web.academy.entity.MainMenu;

@Service
public class AcademyViewMngService implements ViewMngService {

	@Autowired
	private MainMenuDao mainMenuDao;
	
	
	@Override
	public List<MainMenu> getMainMenuList() {
	
		return mainMenuDao.getList();
	}

	@Override
	public int addMainMenu(MainMenu mainMenu) {
		int result = mainMenuDao.insert(mainMenu);
		
		return result;
	}

	@Override
	public MainMenu getMainMenu(int selectedMenuId) {
		MainMenu menu = mainMenuDao.get(selectedMenuId);
		return menu;
	}

	@Override
	@Transactional
	public int updateMainMenu(MainMenu mainMenu) {
		MainMenu curMenu = mainMenuDao.get(mainMenu.getId());
		curMenu.setTitle(mainMenu.getTitle());
		curMenu.setUrl(mainMenu.getUrl());
		return mainMenuDao.update(curMenu);
	}

	@Override
	@Transactional
	public int deleteMainMenuList(Integer[] menuIds) {
		//1.반복문을 이용해서 Dao.delete메소드 호출
		/*for(int id : menuIds)
			mainMenuDao.delete(id);*/
		
		//2.Dao에 menuIds 배열을 통째로 옮겨서 Dao가 한번에 삭제하게 하는방법
		
		return mainMenuDao.deleteList(menuIds);
	}

	@Override
	public int deleteMainMenu(Integer selectedMenuId) {
		
		return mainMenuDao.delete(selectedMenuId);
	}

	

}
