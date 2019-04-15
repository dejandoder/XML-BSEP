package com.eureka.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eureka.auth.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

		User save(User u);
		
		@Query("select user from User user where user.username = :username")
		public User findUserByUserName(@Param("username") String username);
		
}
