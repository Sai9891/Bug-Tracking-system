package com.bugapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugapi.dto.UserDTO;
import com.bugapi.exceptions.ResourceNotFoundException;
import com.bugapi.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Validated @RequestBody UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        
        UserDTO registeredUser = userService.registerUser(userDTO);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
    
    @PostMapping("/login/{emailId}/{password}")
    public ResponseEntity<String> loginUser(@PathVariable String emailId, 
                                            @PathVariable String password) {  
        UserDTO userDTO = new UserDTO();
        userDTO.setEmailId(emailId);
        userDTO.setPassword(password);

        String loginStatus = userService.loginUser(userDTO);
        
        if (loginStatus.equals("Login successful")) 
                return ResponseEntity.ok(loginStatus);
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginStatus);
    }
    
    @GetMapping("/getUser/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        try {
            UserDTO userDTO = userService.getUserById(userId);
            return ResponseEntity.ok(userDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Employee not found with ID: " + userId);
        }
    }
    
    
    @GetMapping("/email/{email}")
    public ResponseEntity<Object> getUserByEmail(@Validated @PathVariable String email) throws ResourceNotFoundException {
        // Validate email format using annotations from DTO
        if (email == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ID cannot be blank");
        }

        // Proceed to retrieve user by email from the service layer
        UserDTO userDTO = userService.getUserByEmail(email);
        if (userDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with email: " + email);
        }

        return ResponseEntity.ok(userDTO);
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllUsers() {
    	ResponseEntity<Object> respEntity = null;
        List<UserDTO> users = userService.getAllUsers();
        respEntity =  new ResponseEntity<>(users,HttpStatus.OK);
        return respEntity;
    }
    
}


