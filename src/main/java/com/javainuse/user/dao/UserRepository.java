package com.javainuse.user.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javainuse.user.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUsername(String username);
	
}