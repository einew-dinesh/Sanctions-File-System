package com.effiya.Maker_Checker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.effiya.Maker_Checker.model.File;
import com.effiya.Maker_Checker.model.FileRecord;

@Repository
public interface FileRecordRepo extends JpaRepository<FileRecord, Integer> {

	List<FileRecord> findAllByPan(String pan);

	List<FileRecord> findAll();

	List<FileRecord> findAllByFile(File file);

	int countByFile(File file);

	int countByStatusAndFile(String status, File file);

	int countByStatus(String status);

}
