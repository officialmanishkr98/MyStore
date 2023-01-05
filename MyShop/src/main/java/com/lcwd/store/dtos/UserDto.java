package com.lcwd.store.dtos;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lcwd.store.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDto {

	private String id;
	
	@NotBlank(message = "Name is required")
	@Size(min=5,max=15, message = "Username must be between 5 to 15 chars")
	private String name;
	
	@Email(message = "valid email is required")
	private String email;
	
	
	@Pattern(regexp = "(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}", message = "pass shoudl contain - 1.At least one upper case  2.At least one lower case English letter, 3.At least one digit, 4.At least one special character, 5.Minimum eight in length")
	private String password;
	
	private String about;
	
	@NotBlank(message = "gender is required")
	private String gender;
	

    @JsonFormat(pattern = "dd/MM/yyyy")
	private Date dob;
    
    private Set<RoleDto> roles = new HashSet<>();
}
