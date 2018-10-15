package com.jaehwan.web.academy.dao;

import java.util.List;

import com.jaehwan.web.academy.entity.Academy;

public interface AcademyDao {
	int insert(Academy academy);
	int update(Academy academy);
	int delete(String id);
	
	Academy get(String id);
	List<Academy> getList();
}
