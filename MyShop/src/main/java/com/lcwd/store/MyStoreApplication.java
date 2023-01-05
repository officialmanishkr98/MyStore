 package com.lcwd.store;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lcwd.store.entities.Role;
import com.lcwd.store.repository.RoleRepo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;


//@ComponentScan, @EnableAutoConfiguration, and @Configuration.
@SpringBootApplication
@ComponentScan(basePackages = {"com.lcwd.store","security"})
public class MyStoreApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MyStoreApplication.class, args);
		
	}

	@Autowired
	private RoleRepo repos;
	
	@Override
	public void run(String... args) throws Exception {
		
		//System.out.println(passwordEncoder.encode("123"));
		
		try {
			
			Role role1 = Role.builder().roleId("sfswefsdvsasfag").roleName("ROLE_ADMIN").build();

			Role role2 = Role.builder().roleId("sasdcxncsscsnkasg").roleName("ROLE_NORMAL").build();
			repos.save(role1);
			repos.save(role2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}
