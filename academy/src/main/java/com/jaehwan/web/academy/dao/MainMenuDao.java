package com.jaehwan.web.academy.dao;

import java.util.List;

import com.jaehwan.web.academy.entity.MainMenu;

public interface MainMenuDao {
	public int insert(MainMenu mainMenu);
	public int update(MainMenu mainMenu);
	public int delete(int id);
	
	public MainMenu get(int id);
	public List<MainMenu> getList();
	public int deleteList(Integer[] menuIds);
	public List<MainMenu> getListByAcademyId(String id);
}
