package com.mainul35.chainservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mainul35.chainservice.model.domain.sqlDomains.Authority;
import com.mainul35.chainservice.model.domain.sqlDomains.Employee;
import com.mainul35.chainservice.model.domain.sqlDomains.UserEntity;
import com.mainul35.chainservice.repositories.sqlRepositories.AuthorityRepository;
import com.mainul35.chainservice.repositories.sqlRepositories.EmployeeRepository;
import com.mainul35.chainservice.service.UserService;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import java.util.List;

@RestController
@SpringBootApplication
@ComponentScan(basePackages={
"com.mainul35.chainservice.controllers.RestControllers",
"com.mainul35.chainservice.service",
"com.mainul35.chainservice.securityConfig",
"com.mainul35.chainservice.controllers"
}, 
basePackageClasses={
//		WebConfig.class
})
@EnableJpaRepositories(basePackages={"com.mainul35.chainservice.repositories.sqlRepositories"})
@EntityScan(basePackages={"com.mainul35.chainservice.model.domain.sqlDomains"})
public class ChainserviceApplication extends WebMvcConfigurerAdapter implements CommandLineRunner{
	
	@Autowired UserService userService;
	@Autowired AuthorityRepository authorityRepository;
	@Autowired EmployeeRepository employeeRepository;

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

		this.employeeRepository.save(new Employee("Frodo", "Baggins", "ring bearer"));

	}

	@GetMapping("/employees")
	public List<Employee> getEmployees(){
		return (List<Employee>) employeeRepository.findAll();
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler(
        		 "/resources/**",
        		 "/resources/static/**",
        		 "/resources/static/built/**",
        		 "/resources/static/js/**",
        		 "/resources/static/css/**",
				 "/built/**"
        		 ).addResourceLocations(
				 "/resources/",
				 "/WEB-INF/resources/built/");

    }

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		super.configureViewResolvers(registry);
		registry.viewResolver(viewResolver());
	}

	@Bean
	@Description("Thymeleaf View Resolver")
	public ThymeleafViewResolver viewResolver() {

		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setPrefix("WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setCacheable(false);
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		templateEngine.addDialect(new SpringSecurityDialect());
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		viewResolver.setCache(false);
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setOrder(1);
		return viewResolver;
	}

}
