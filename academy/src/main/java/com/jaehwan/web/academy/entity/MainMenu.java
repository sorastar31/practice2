package com.jaehwan.web.academy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

@Entity
public class MainMenu {
	@Id
	@GeneratedValue(	//mySQL의 경우 /SQLserver나 oracle의 경우는 ()안에 지정해줘야함
		strategy=GenerationType.AUTO,
		generator="native"
	)
	@GenericGenerator(
		name="native",
		strategy="native"
	)
	private int id;
	private String title;
	private String url;
	@Column(updatable=false, insertable=false)
	private String academyId;
	@Column(updatable=false)
	private Integer parentId;	//null이 가능한 정수형을 써줍니다 그것은 Integer
	@Column(insertable=false, name="[order]")
	private int order;
	
	@ManyToOne
	@JoinColumn(name="academyId")
	private Academy academy;

	public MainMenu() {
		// TODO Auto-generated constructor stub
	}
	
	public MainMenu(int id, String title, String url) {
		this.id = id;
		this.title = title;
		this.url = url;
	}

	public MainMenu(int id, String title, String url, String academyId, int parentId, int order) {
		this.id = id;
		this.title = title;
		this.url = url;
		this.academyId = academyId;
		this.parentId = parentId;
		this.order = order;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAcademyId() {
		return academy.getId();
	}

	public void setAcademyId(String academyId) {
		this.academy.setId(academyId);
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	@Override
	public String toString() {
		return "academy [id=" + id + ", title=" + title + ", url=" + url + ", academyId=" + academyId + ", parentId="
				+ parentId + ", order=" + order + "]";
	}
	
}
