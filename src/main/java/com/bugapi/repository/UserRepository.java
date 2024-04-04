package com.bugapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bugapi.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmailIdOrPassword(String emailId, String password);
    User findByEmailId(String emailId);
}
