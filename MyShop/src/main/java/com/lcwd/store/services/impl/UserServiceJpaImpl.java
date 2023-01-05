package com.lcwd.store.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.lcwd.store.dtos.UserDto;
import com.lcwd.store.entities.Role;
import com.lcwd.store.entities.User;
import com.lcwd.store.exceptions.ResourceNotFoundException;
import com.lcwd.store.repository.RoleRepo;
import com.lcwd.store.repository.UserRepository;
import com.lcwd.store.services.UserService;

@Service
@Primary
public class UserServiceJpaImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Value("${user.normal.role.id}")
	private String normalUserId;

	@Override
	public UserDto addUser(UserDto userDto) {
		User user = mapper.map(userDto, User.class);
		user.setId(UUID.randomUUID().toString());
		
		// get the user as a normal user:
		
		Role role = roleRepo.findById(normalUserId).get();
		user.getRoles().add(role);
		
		User savedUser = userRepository.save(user);
		UserDto savedUserDto = mapper.map(savedUser, UserDto.class);

		return savedUserDto;
	}

	@Override
	public UserDto updateUser(UserDto userDto, String userDtoId) {
		// get the old user
		User user = userRepository.findById(userDtoId)
				.orElseThrow(() -> new ResourceNotFoundException("user with given id not found"));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		user.setGender(userDto.getGender());
		user.setDob(userDto.getDob());

		// update
		User updatUser = userRepository.save(user);

		return mapper.map(updatUser, UserDto.class);
	}

	@Override
	public UserDto getUser(String userDtoId) {
		User user = userRepository.findById(userDtoId)
				.orElseThrow(() -> new ResourceNotFoundException("user with given id not found"));
		return mapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAll() {

		List<User> findAllUsers = userRepository.findAll();

		return findAllUsers.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(String userDtoId) {

		userRepository.deleteById(userDtoId);

	}

	@Override
	public List<UserDto> searchUsers(String keywords) {
		List<User> list = userRepository.findByNameContaining(keywords);
		List<UserDto> userDtos = list.stream().map(user -> mapper.map(user, UserDto.class))
				.collect(Collectors.toList());

		return userDtos;
	}

}
