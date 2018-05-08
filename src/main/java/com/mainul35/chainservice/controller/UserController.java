package com.mainul35.chainservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mainul35.chainservice.model.domain.sqlDomains.Authority;
import com.mainul35.chainservice.model.domain.sqlDomains.UserEntity;
import com.mainul35.chainservice.repositories.sqlRepositories.AuthorityRepository;
import com.mainul35.chainservice.service.UserService;

@Controller
public class UserController {
	
//	@Autowired UserService userService;
//	@Autowired AuthorityRepository authorityRepository;
//	@Autowired BCryptPasswordEncoder passwordEncoder;
//
//	
//	@RequestMapping("/login")
//	@ResponseBody public List<UserEntity> createData(){
//		authorityRepository.save(new Authority(1L, "ROLE_OWNER"));
//		authorityRepository.save(new Authority(2L, "ROLE_CLIENT"));
//		authorityRepository.save(new Authority(3L, "ROLE_CUSTOMER"));
//		authorityRepository.save(new Authority(4L, "ROLE_ADMIN"));
//		authorityRepository.save(new Authority(5L, "ROLE_EMPLOYEE"));
//		
//		userService.save(new UserEntity(
//				1L,
//				"Syed Mainul Hasan",
//				passwordEncoder.encode("secret"),
//				"mainuls18@gmail.com",
//				"mainul35",
//				true,
//				authorityRepository.findAuthorityById(1L)));
//		
//		return userService.findAll();
//	}
}
