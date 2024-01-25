package com.effiya.Maker_Checker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.effiya.Maker_Checker.model.File;
import com.effiya.Maker_Checker.model.FileRecord;
import com.effiya.Maker_Checker.model.User;

public interface FileRepo extends CrudRepository<File, Integer>{

	//List<File> findByUser(User user);


	List<File> findAllByUser(User user);

	int countByUser(User user);

	
	
	
	
	
}
