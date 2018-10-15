package com.jaehwan.web.academy.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jaehwan.web.academy.dao.MemberDao;
import com.jaehwan.web.academy.entity.Member;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	/*@Autowired
	private HbHomeService service;*/
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("join")
	public String join(Model model) {

		
		return "member.join";
	}
	
	@GetMapping("email-join")
	public String emailJoin(){
	
		return "member.email-join";
	}
	
	/*
	@GetMapping("is-id-duplicated")
	@ResponseBody
	public String isIdDuplicated(String id) {
		boolean duplicated = service.isIdDuplicated(id);
		
		if(duplicated)
			return "true";
		
		return "false";
	}
	
	@GetMapping("email-duplicated-error")
	@ResponseBody
	public String emailDuplicatedError() {
		return "<script>alert('이미 가입되어있는 이메일입니다.');location.href='email-join';</script>";
	}
	
	
	@PostMapping("email-join")
	public String emailJoin(String email, 	HttpServletResponse response){
		boolean duplicated = service.isEmailDuplicated(email);
		
		if(duplicated)
			return "redirect:email-duplicated-error";
		
		UUID uuid = UUID.randomUUID();
		MessageDigest salt = null;
		String digest = null;

		try {
			salt = MessageDigest.getInstance("SHA-256");
			salt.update(uuid.toString().getBytes());
			
			byte[] key = salt.digest();
			//바이트열을 문자열로 바꾸기 위해서 더하기가 반복되어야 한다
			StringBuilder builder = new StringBuilder();
			for(byte b : key) {
				builder.append(String.format("%02x", b));
			}
			digest = builder.toString();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(uuid);
		System.out.println(digest);	
		System.out.println(email);
		
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("noreply@newlecture.com");
			helper.setTo(email);
			helper.setSubject("뉴렉처 회원가입을 위한 인증메일");
			helper.setText("<a href=\"http://211.238.142.41:8080/jaehwan/member/join-reg?id="
								+digest+"&em="+email+"\">가입링크</a>", true);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Cookie cookie = new Cookie("joinId", digest);
		cookie.setPath("/jaehwan/member/");
		cookie.setMaxAge(60*60*24);
		
		response.addCookie(cookie);
		
		mailSender.send(message);
		return "member.email-join-info";
	}
	*/
	@GetMapping("join-reg")
	public String joinReg(@RequestParam(value="id", defaultValue="") String key,
							@RequestParam(value="em", defaultValue="") String email,
							@CookieValue(value="joinId", defaultValue="") String joinId,
							Model model) {
		System.out.println(key);
		System.out.println(email);
		System.out.println(joinId);
		
		/*if(key.equals("") || joinId.equals("") || !key.equals(joinId))
			return "member.join-error";*/
		
		String uid= email.split("@")[0];
		
		model.addAttribute("uid", uid);
		model.addAttribute("email",email);
		
		return "member.join-reg";
	}
	
	@GetMapping("moonjae.jpg")
	public void moonjae(HttpSession session, HttpServletResponse response) throws IOException {
		Random rand = new Random();
		int x = rand.nextInt(100);
		int y = rand.nextInt(10);
		
		String fmtString = String.format("%d+%d=", x,y);
		
		session.setAttribute("moonjae", x+y);
		
		//비트맵이라는 도화지 -> 메모리상의 이미지가 필요
		BufferedImage img = new BufferedImage(60, 30, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		g.setFont(new Font("돋움", 0, 13));
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 60, 30);
		g.setColor(Color.BLACK);
		g.drawString(fmtString, 5, 19);
		
		response.setContentType("image/png");
		
		ImageIO.write(img, "png", response.getOutputStream());
	}
	
	/*@PostMapping("join-reg")
	public String joinReg(Member member, 
			@RequestParam("photo-file") MultipartFile photoFile,
			HttpServletRequest request) throws IOException {
		
		String resLocation = "/resources/users/sora/";
		
		ServletContext context = request.getServletContext();
		String homeDir = context.getRealPath(resLocation);
		String uploadedFileName = photoFile.getOriginalFilename();
		String filePath = homeDir + uploadedFileName;
		
		System.out.println(filePath);
		
		File dir = new File(homeDir);
		if(!dir.exists())
			dir.mkdirs();
		
		InputStream fis = photoFile.getInputStream();
		FileOutputStream fos = new FileOutputStream(filePath);
		
		byte[] buffer = new byte[1024];
		while(fis.read(buffer, 0, buffer.length) != -1)
			fos.write(buffer);
		
		int size = 0;
		while((size=fis.read(buffer, 0, buffer.length)) != -1)
			fos.write(buffer, 0, size);
				
		fis.close();
		fos.close();
		
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPwd = encoder.encode(member.getPwd());
		
		member.setPhoto(uploadedFileName);
		member.setPwd(encodedPwd);

		System.out.println(encodedPwd);
		
		service.insertMember(member);
		
		return "redirect:../index";
	}*/
	
	@GetMapping("login")
	public String login() {
		return "member.login";
	}
	
	@GetMapping("reset-pwd")
	public String resetPwd() {
		return "member.reset-pwd";
	}
	
	/*@PostMapping("reset-pwd")
	public String resetPwd(String id, HttpServletResponse response) {
		Member member = service.getMember(id);
		String email = member.getEmail();
		
		UUID uuid = UUID.randomUUID();
		
		MessageDigest salt = null;
		String digest = null;
		
		try {
			salt = MessageDigest.getInstance("SHA-256");
			salt.update(uuid.toString().getBytes());
			
			byte[] key = salt.digest();
			
			StringBuilder builder = new StringBuilder();
			
			for(byte b : key)
				builder.append(String.format("%02x", b));
			
			digest = builder.toString();
			System.out.println(digest);
			
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Cookie cookie = new Cookie("joinId", digest);
		
		cookie.setPath("/jaehwan/member/");
		cookie.setMaxAge(60*60*24);
		
		response.addCookie(cookie);
		
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("noreply@newlecture.com");
			helper.setTo(email);
			helper.setSubject("뉴렉처 비밀번호를 위한 인증메일");
			helper.setText("<a href='http://localhost:8080/jaehwan/member/pwd-edit?id="
								+digest+"&em="+email+"'>비밀번호 재설정 링크</a>", true);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mailSender.send(message);
		
		return "member.reset-pwd";
	}*/

	@GetMapping("pwd-edit")
	public String pwdEdit() {
		return "member.pwd-edit";
	}
}
