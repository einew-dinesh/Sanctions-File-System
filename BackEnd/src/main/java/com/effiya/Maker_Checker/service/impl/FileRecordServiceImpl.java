package com.effiya.Maker_Checker.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.effiya.Maker_Checker.dto.FileRecordDTO;
import com.effiya.Maker_Checker.model.File;
import com.effiya.Maker_Checker.model.FileRecord;
import com.effiya.Maker_Checker.repository.FileHelper;
import com.effiya.Maker_Checker.repository.FileRecordRepo;
import com.effiya.Maker_Checker.service.FileRecordService;

@Service
public class FileRecordServiceImpl implements FileRecordService {

	@Autowired
	FileRecordRepo fileRecordRepo;

	@Override
	public void save(MultipartFile csv, File file) {

		try {

			List<FileRecord> fileRecords = FileHelper.csvToFileRecords(csv.getInputStream(), file);

			fileRecordRepo.saveAll(fileRecords);
		} catch (IOException e) {

			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
		// TODO Auto-generated method stub

	}

	@Override
	public List<FileRecord> getAllRecords() {
		// TODO Auto-generated method stub
		List<FileRecord> fileRecords = fileRecordRepo.findAll();

		return fileRecords;
	}

	@Override
	public List<FileRecord> getAllRecordsOfFile(File file) {
		// TODO Auto-generated method stub
		List<FileRecord> fileRecords = fileRecordRepo.findAllByFile(file);
		return fileRecords;
	}

	@Override
	public FileRecord updateStatus(int fileRecordId, String newStatus) {
		// TODO Auto-generated method stub
		FileRecord fileRecord = fileRecordRepo.findById(fileRecordId).orElseThrow();
		fileRecord.setStatus(newStatus);

		return fileRecordRepo.save(fileRecord);
	}

	@Override
	public int countOfRecordsOfFile(File file) {
		// TODO Auto-generated method stub
		int totalRecords = fileRecordRepo.countByFile(file);
		return totalRecords;
	}

	@Override
	public int countOfRecordsByStatusForFile(File file, String status) {
		// TODO Auto-generated method stub
		int totalCount = fileRecordRepo.countByStatusAndFile(status, file);
		return totalCount;
	}

	@Override
	public FileRecord update(Integer fileRecordId, FileRecordDTO fileRecordDTO) {
		// TODO Auto-generated method stub
		FileRecord fileRecord = fileRecordRepo.findById(fileRecordId).orElseThrow();
		if(fileRecordDTO.getPan()!= "") {
			fileRecord.setPan(fileRecordDTO.getPan());
		}
		if(fileRecordDTO.getEmail()!= "") {
			fileRecord.setEmail(fileRecordDTO.getEmail());
		}
		if(fileRecordDTO.getName()!= "") {
			fileRecord.setName(fileRecordDTO.getName());
		}
		
		
		return fileRecordRepo.save(fileRecord);
	}

	@Override
	public String deleteRecord(Integer fileRecordId) {
		// TODO Auto-generated method stub
		try {
			FileRecord fileRecord = fileRecordRepo.findById(fileRecordId).orElseThrow();
			fileRecordRepo.delete(fileRecord);
			return "Successfully Deleted";

		}catch(Exception e) {
			return "Record Doesn't exist";
		}
	}

	@Override
	public int countOfRecordsUploaded() {
		// TODO Auto-generated method stub
		int recordsCount = (int) fileRecordRepo.count();
		return recordsCount;
	}

	@Override
	public int countOfRecordsByStatus(String status) {
		// TODO Auto-generated method stub
		int recordsCount = (int) fileRecordRepo.countByStatus(status);
		return recordsCount;
	}

	@Override
	public int countOfUniqueRecordsUploaded() {
		// TODO Auto-generated method stub
		List<FileRecord> records= fileRecordRepo.findAll();
		
		int uniqueRecords = records.stream().collect(Collectors.groupingBy(FileRecord::getPan, Collectors.counting())).size();
		return uniqueRecords;
	}
	

//	@Override
//	public List<FileRecord> getAllRecordsOfFile(File file) {
//		// TODO Auto-generated method stub
//		return fileRecordRepo.findAllByFile(file);
//	}
//
//	@Override
//	public int getTotalRecordsOfFile(File file) {
//		// TODO Auto-generated method stub
//		
//		return fileRecordRepo.countByFile(file);
//	}
//
//	@Override
//	public List<FileRecord> getRecordsOfFile(File file) {
//		// TODO Auto-generated method stub
//		List<FileRecord> list = fileRecordRepo.findAllByFile(file);
//		return list;
//	}
//
//	

}
