package com.effiya.Maker_Checker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.effiya.Maker_Checker.model.User;

@Repository
public interface UserRepo extends CrudRepository<User, Integer>{
	User findByUserEmail(String userEmail);

	int countByUserRole(String userRole);

	List<User> findAllByUserRole(String userRole);
}
