package com.jaehwan.web.academy.service;

import org.springframework.stereotype.Service;

import com.jaehwan.web.academy.entity.Academy;

@Service
public interface MemberService {
	Academy getAcademy(String id);
}
