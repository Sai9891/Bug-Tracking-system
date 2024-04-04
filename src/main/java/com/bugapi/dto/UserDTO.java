package com.bugapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserDTO {
	
    private Long userId;
    
    @NotBlank(message = "First name cannot be blank")
    @Size(max = 50, message = "First name must be less than or equal to 50 characters")
    private String firstName;
    
    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 50, message = "Last name must be less than or equal to 50 characters")
    private String lastName;
    
    @NotBlank(message = "Email ID cannot be blank")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email ID must be less than or equal to 100 characters")
    private String emailId;
    
    /*
	Password should contains at least 8 characters and at most 20 characters.
	It should contains at least one digit.
	It should contains at least one upper case alphabet.
	It should contains at least one lower case alphabet.
	It should contains at least one special character which includes !@#$%&*()-+=^.
	It should doesn’t contain any white space.
	
	where:
	^ represents starting character of the string.
	(?=.*[0-9]) represents a digit must occur at least once.
	(?=.*[a-z]) represents a lower case alphabet must occur at least once.
	(?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
	(?=.*[@#$%^&-+=()] represents a special character that must occur at least once.
	(?=\\S+$) white spaces don’t allowed in the entire string.
	.{8, 20} represents at least 8 characters and at most 20 characters.
	$ represents the end of the string.
 */
	@Schema(example = "Enter your password")
    @NotEmpty(message = "Password Field Can't be Empty")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$", message = "Invalid Password Rule")
	@Column(name="password")
	private String password;
	
	//To display successful messages
	@JsonIgnore
	private String message;
	

}

