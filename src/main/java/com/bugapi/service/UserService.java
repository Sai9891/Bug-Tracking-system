package com.bugapi.service;

import java.util.List;

import com.bugapi.dto.UserDTO;
import com.bugapi.exceptions.ResourceNotFoundException;

public interface UserService {
    UserDTO registerUser(UserDTO usersDTO);
    String loginUser(UserDTO usersDTO);
	public UserDTO getUserById(Long userId) throws ResourceNotFoundException;
	List<UserDTO> getAllUsers();
    UserDTO getUserByEmail(String email) throws ResourceNotFoundException;
    void updatePassword(Long userId, String newPassword) throws ResourceNotFoundException;
    void signOut(Long userId);
	
}

