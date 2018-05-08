package com.mainul35.chainservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mainul35.chainservice.model.domain.sqlDomains.Authority;
import com.mainul35.chainservice.model.domain.sqlDomains.UserEntity;
import com.mainul35.chainservice.repositories.sqlRepositories.AuthorityRepository;
import com.mainul35.chainservice.repositories.sqlRepositories.UserRepository;
import com.mainul35.chainservice.securityConfig.SecurityWebApplicationInitializer;
import com.mainul35.chainservice.service.UserService;


@SpringBootApplication
@ComponentScan(basePackages={
"com.mainul35.chainservice.controller",
"com.mainul35.chainservice.controllers.RestControllers",
"com.mainul35.chainservice.service",
"com.mainul35.chainservice.securityConfig"
})
@EnableJpaRepositories(basePackages={"com.mainul35.chainservice.repositories.sqlRepositories"})
@EntityScan(basePackages={"com.mainul35.chainservice.model.domain.sqlDomains"})
@Import(value={SecurityWebApplicationInitializer.class})
public class ChainserviceApplication implements CommandLineRunner{
	
	@Autowired UserService userService;
	@Autowired AuthorityRepository authorityRepository;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(11);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(ChainserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		authorityRepository.save(new Authority(1L, "ROLE_OWNER"));
		authorityRepository.save(new Authority(2L, "ROLE_CLIENT"));
		authorityRepository.save(new Authority(3L, "ROLE_CUSTOMER"));
		authorityRepository.save(new Authority(4L, "ROLE_ADMIN"));
		authorityRepository.save(new Authority(5L, "ROLE_EMPLOYEE"));
		
		userService.save(new UserEntity(
				1L,
				"Syed Mainul Hasan",
				getPasswordEncoder().encode("secret"),
				"mainuls18@gmail.com",
				"mainul35",
				true,
				authorityRepository.findAuthorityById(4L)));

//		userService.save(new UserEntity(
//				1L,
//				"Syed Mainul Hasan",
//				passwordEncoder.encode("secret"),
//				"mainuls18@gmail.com",
//				"mainul35",
//				true,
//				authorityRepository.findAuthorityById(4L)));
//
//		userService.save(new UserEntity(
//				1L,
//				"Syed Mainul Hasan",
//				passwordEncoder.encode("secret"),
//				"mainuls18@gmail.com",
//				"mainul35",
//				true,
//				authorityRepository.findAuthorityById(4L)));
//
//		userService.save(new UserEntity(
//				1L,
//				"Syed Mainul Hasan",
//				passwordEncoder.encode("secret"),
//				"mainuls18@gmail.com",
//				"mainul35",
//				true,
//				authorityRepository.findAuthorityById(4L)));
//
//		userService.save(new UserEntity(
//				1L,
//				"Syed Mainul Hasan",
//				passwordEncoder.encode("secret"),
//				"mainuls18@gmail.com",
//				"mainul35",
//				true,
//				authorityRepository.findAuthorityById(4L)));

	}
	
}
