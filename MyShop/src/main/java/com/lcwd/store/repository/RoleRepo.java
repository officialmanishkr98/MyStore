package com.lcwd.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.store.entities.Role;

public interface RoleRepo extends JpaRepository<Role, String> {

}
