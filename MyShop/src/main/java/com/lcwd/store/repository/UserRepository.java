package com.lcwd.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lcwd.store.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

	// custom finder methods :
	// below methods are implemented by spring boot data jpa
	
	User findByName(String name);
	
	Optional<User> findByEmail(String email);
	
	User findByEmailAndPassword(String email, String password);
	
	User findByEmailOrPassword(String email, String password);
	
	List<User> findByNameContaining(String name);
	
	// query methods: we write queries
	//HQL (Hibernate Query Language) / JPQL ( Java Persistant Query Language )
	
	@Query("Select u from User u where u.email =: email")
	User findByEmailUsingJPQL(String email);
}
