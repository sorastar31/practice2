package com.jaehwan.web.academy.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;

import javax.persistence.Transient;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jaehwan.web.academy.dao.AcademyDao;
import com.jaehwan.web.academy.dao.MainMenuDao;
import com.jaehwan.web.academy.entity.Academy;
import com.jaehwan.web.academy.entity.MainMenu;
import com.jaehwan.web.academy.service.MemberService;
import com.jaehwan.web.academy.service.UiService;

@Controller
@RequestMapping("/")
public class HomeController {

	/*@Autowired
	private MainMenuDao mainMenuDao;
	
	@Autowired
	private AcademyDao academyDao;*/
	
	@Autowired
	private UiService uiService;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("{id}")
	//@Transactional
	public String index(@PathVariable("id") String id, Model model) {
		//Academy academy = academyDao.get(id);
		Academy academy = memberService.getAcademy(id);
		
		/*List<MainMenu> mainMenus = mainMenuDao.getListByAcademyId(id);
		academy.setMainMenus(mainMenus);*/
		//System.out.println(id);
		
		model.addAttribute("academy", academy);
		//return "home.index";
		return "index";
	}

	//@GetMapping("index")
	//public String index(Model model) {
		/*MainMenu mainMenu = mainMenuDao.get(1);
		System.out.println(mainMenu);*/
		/*List<MainMenu> mainMenus = mainMenuDao.getList();
		model.addAttribute("mainMenus", mainMenus);*/
		//return "home.index";
	//}
	
	@PostMapping("upload-Ajax")
	@ResponseBody
	public String uploadAjax(MultipartFile file
								,HttpServletRequest request
								/*, Principal principal*/) {
		//String memberId = principal.getName();
		String academyId = "sist";//service.academyIdByMemberId(memberId);
		
		String urlPath = "/resources/partners/"+academyId;
		
		ServletContext context = request.getServletContext();
		String realPath = context.getRealPath(urlPath);
		String uploadedFileName = file.getOriginalFilename();
		String filePath = realPath + uploadedFileName;
		
		File uploadFile = new File(realPath);
		if(!uploadFile.exists())
			uploadFile.mkdirs();
		
		InputStream fis;
		try {
			fis = file.getInputStream();
			FileOutputStream fos = new FileOutputStream(filePath);
			
			byte[] buffer = new byte[1024];
			int size = 0;
			
			while((size=fis.read(buffer, 0, buffer.length)) != -1)
				fos.write(buffer, 0, size);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return "";
	}
}
