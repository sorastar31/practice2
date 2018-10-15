package com.jaehwan.web.academy.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminHomeController")
@RequestMapping("/sist/admin/")
public class HomeController {

	@GetMapping("index")
	public String index() {
		return "admin.index";
	}
}
