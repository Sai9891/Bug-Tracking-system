package com.bugapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugapi.dto.UserDTO;
import com.bugapi.entity.User;
import com.bugapi.exceptions.ResourceNotFoundException;
import com.bugapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
       
    }

    @Override
    public UserDTO registerUser(UserDTO usersDTO) {
        User user = mapDTOToUser(usersDTO);
        User savedUser = userRepository.save(user);
        return mapUserToDTO(savedUser);
    }
	
	@Override
	public String loginUser(UserDTO userDTO) {
	    // Find user by email
	    User user = userRepository.findByEmailId(userDTO.getEmailId());

	    if (user != null) {
	        // User found, check password
	        if (user.getPassword().equals(userDTO.getPassword())) {
	            return "Login successful";
	        } else {
	            return "Login failed: Incorrect password";
	        }
	    } else {
	        return "Login failed: User not found";
	    }
	}
	
	
	public UserDTO getUserById(Long userId) throws ResourceNotFoundException {
	    return userRepository.findById(userId)
	            .map(this::mapUserToDTO)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + userId));
	}

//	@Override
//    public UserDTO getUserByEmail(String email) throws ResourceNotFoundException {
//        User user = userRepository.findByEmailId(email);
//        if (user == null) {
//            throw new ResourceNotFoundException("User not found with email: " + email);
//        }
//        return mapUserToDTO(user);
//    }
//	
	
	@Override
	public UserDTO getUserByEmail(String email) {
	    User user = userRepository.findByEmailId(email);
	    if (user == null) {
	        return null; // User not found
	    }
	    return mapUserToDTO(user);
	}

	@Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }


	@Override
	public void updatePassword(Long userId, String newPassword) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void signOut(Long userId) {
		// TODO Auto-generated method stub
		
	}
	
	private UserDTO mapUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmailId(user.getEmailId());
        userDTO.setPassword(user.getPassword());
        
        return userDTO;
    }

	private User mapDTOToUser(UserDTO userDTO) {
	    return new User(
	            userDTO.getUserId(),
	            userDTO.getFirstName(),
	            userDTO.getLastName(),
	            userDTO.getEmailId(),
	            userDTO.getPassword()
	    );
	}

}


/*// Service method
public UserDTO getUserById(Long userId) throws ResourceNotFoundException {
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) {
        User user = optionalUser.get();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmailId(user.getEmailId());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    } else {
        throw new ResourceNotFoundException("Employee not found with ID: " + userId);
    }
}*/


/*@Override
public UserDTO registerUser(UserDTO usersDTO) {
	User user = new User();
	user.setFirstName(usersDTO.getFirstName());
    user.setLastName(usersDTO.getLastName());
    user.setEmailId(usersDTO.getEmailId());
    user.setPassword(usersDTO.getPassword());
    
    User savedUser = userRepository.save(user);
    
    // Update the userId in the userDTO with the generated value
    usersDTO.setMessage("Registration Successful");
    usersDTO.setUserId(savedUser.getUserId());
    
	return usersDTO;
}*/


