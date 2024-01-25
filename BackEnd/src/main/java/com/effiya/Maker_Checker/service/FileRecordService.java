package com.effiya.Maker_Checker.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.effiya.Maker_Checker.dto.FileRecordDTO;
import com.effiya.Maker_Checker.model.File;
import com.effiya.Maker_Checker.model.FileRecord;

public interface FileRecordService {

	public void save(MultipartFile csv, File file);

	public List<FileRecord> getAllRecords();

	public List<FileRecord> getAllRecordsOfFile(File file);

	public FileRecord updateStatus(int fileRecordId, String newStatus);

	public int countOfRecordsOfFile(File f);

	public int countOfRecordsByStatusForFile(File f, String status);

	public FileRecord update(Integer fileRecordId, FileRecordDTO fileRecordDTO);
	
	public String deleteRecord(Integer fileRecordId);

	public int countOfRecordsUploaded();

	public int countOfRecordsByStatus(String string);

	public int countOfUniqueRecordsUploaded();

//	public List<FileRecord> getAllRecordsOfFile(File file);
//	
//	public int getTotalRecordsOfFile(File file);
//
//	public List<FileRecord> getRecordsOfFile(File file);

}
