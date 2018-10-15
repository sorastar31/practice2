package com.jaehwan.web.academy.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jaehwan.web.academy.entity.MainMenu;
import com.jaehwan.web.academy.service.ViewMngService;

@Controller
@RequestMapping("/sist/admin/view/")
public class ViewController {
	
	@Autowired
	private ViewMngService service;
	
	@GetMapping("header/menu-list")
	public String headerMenuList(
			@RequestParam(name="s", required=false) Integer selectedMenuId,
			Model model) {
		
		if(selectedMenuId != null) {
			//int menuId = Integer.parseInt(selectedMenuId);
			MainMenu selectedMenu = service.getMainMenu(selectedMenuId);
			model.addAttribute("selectedMenu", selectedMenu);
		}
		
		List<MainMenu> mainMenus = service.getMainMenuList();
		model.addAttribute("mainMenus", mainMenus);
		return "admin.view.header.menu-list";
	}
	
	@PostMapping("header/menu-list")
	public String headerMenuList(
			MainMenu mainMenu,
			@RequestParam(defaultValue="") String action,
			@RequestParam(name="selected-menu-id", required=false) Integer selectedMenuId,
			/*@RequestParam("title") String selectedMenuTitle,
			@RequestParam("url") String selectedMenuUrl,*/
			@RequestParam(name="menu-id", required=false) Integer[] menuIds) {
		
		mainMenu.setAcademyId("sist");
		
		switch(action) {
		case "일괄삭제":
			service.deleteMainMenuList(menuIds);
			break;
		case "삭제":
			service.deleteMainMenu(selectedMenuId);
			break;
		case "수정":
			return "redirect:menu-list?s="+selectedMenuId;
		case "추가":
			service.addMainMenu(mainMenu);
			break;
		case "저장":
			//int menuId = Integer.parseInt(selectedMenuId);
			if(selectedMenuId != null) {
				mainMenu.setId(selectedMenuId);
				service.updateMainMenu(mainMenu);
			}
			break;
		}
		//삽입의 경우
		//
		
		/*for(String a : menuIds)
			System.out.println(a);*/
		
		
		
		return "redirect:menu-list";
	}
}
