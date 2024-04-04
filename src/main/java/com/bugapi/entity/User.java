package com.bugapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_generator")
	@SequenceGenerator(name="user_generator", sequenceName = "user_generator", initialValue = 1001,allocationSize = 1)
	private Long userId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;

}
