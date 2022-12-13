package com.lcwd.store.services;

import java.util.List;

import com.lcwd.store.dtos.UserDto;

public interface UserService {

	// add User
	UserDto addUser(UserDto UserDto);

	// update
	UserDto updateUser(UserDto user, String userId);

	// get single
	UserDto getUser(String userId);

	// get All
	List<UserDto> getAll();

	// delete User
	void deletUser(String userId);

}
