package com.lcwd.store.services;

import java.util.List;

import com.lcwd.store.dtos.UserDto;


public interface UserService {
	
	//add user
	UserDto addUser(UserDto userDto);
	//update
	UserDto updateUser(UserDto userDto, String userDtoId);
	//get single
	UserDto getUser(String userId);
	//get all
	List<UserDto> getAll();
	//delete user
	void deleteUser(String userDtoId);
	//search user
	List<UserDto> searchUsers(String keywords);
	

}
