package com.jaehwan.web.academy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jaehwan.web.academy.entity.MainMenu;

@Service
public interface ViewMngService {

	List<MainMenu> getMainMenuList();

	int addMainMenu(MainMenu mainMenu);

	MainMenu getMainMenu(int selectedMenuId);

	int updateMainMenu(MainMenu mainMenu);

	int deleteMainMenuList(Integer[] menuIds);

	int deleteMainMenu(Integer selectedMenuId);

}
