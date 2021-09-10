package com.challenge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.challenge.model.User;

public interface IUserRepo extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
