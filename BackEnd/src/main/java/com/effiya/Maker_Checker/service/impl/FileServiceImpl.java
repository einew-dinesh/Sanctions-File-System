package com.effiya.Maker_Checker.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effiya.Maker_Checker.model.File;
import com.effiya.Maker_Checker.model.FileRecord;
import com.effiya.Maker_Checker.model.User;
import com.effiya.Maker_Checker.repository.FileHelper;
import com.effiya.Maker_Checker.repository.FileRepo;
import com.effiya.Maker_Checker.service.FileRecordService;
import com.effiya.Maker_Checker.service.FileService;
import com.effiya.Maker_Checker.service.UserService;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	FileRepo fileRepo;
	@Autowired
	UserService userService;
	@Autowired
	FileRecordService fileRecordService;

	@Override
	public void save(File file) {
		// TODO Auto-generated method stub
		fileRepo.save(file);

	}

	@Override
	public File getFileById(Integer fileId) {
		// TODO Auto-generated method stub
		File file = fileRepo.findById(fileId).orElseThrow();
		return file;
	}

	@Override
	public List<File> getAllFilesOfUser(User user) {
		// TODO Auto-generated method stub
		List<File> files = fileRepo.findAllByUser(user);
		return files;
	}

	@Override
	public int countOfFilesUploadedByUser(User user) {
		// TODO Auto-generated method stub
		int count = fileRepo.countByUser(user);
		return count;

	}

	@Override
	public int countOfRecordsUploadedByUser(User user) {
		// TODO Auto-generated method stub
		List<File> files = fileRepo.findAllByUser(user);
		int totalRecords = 0;
		for (File f : files) {
			totalRecords += fileRecordService.countOfRecordsOfFile(f);
		}
		return totalRecords;
	}

	@Override
	public int countOfRecordsByStatusOfUser(User user, String status) {
		// TODO Auto-generated method stub
		List<File> files = fileRepo.findAllByUser(user);
		int totalRecords = 0;
		for (File f : files) {
			totalRecords += fileRecordService.countOfRecordsByStatusForFile(f, status);
		}
		return totalRecords;
	}

	@Override
	public int countOfUniqueRecordsUploadedByUser(User user) {
		// TODO Auto-generated method stub
		List<File> files = fileRepo.findAllByUser(user);
		List<FileRecord> fileRecords = new ArrayList<>();

		for (File f : files) {
			fileRecords.addAll(fileRecordService.getAllRecordsOfFile(f));
		}
		int uniqueRecords = fileRecords.stream()
				.collect(Collectors.groupingBy(FileRecord::getPan, Collectors.counting())).size();

		return uniqueRecords;
	}

	@Override
	public InputStream load(File file) {
		// TODO Auto-generated method stub
		List<FileRecord> fileRecords = fileRecordService.getAllRecordsOfFile(file);
		ByteArrayInputStream in = FileHelper.fileRecordToCSV(fileRecords);
		return in;
	}

	@Override
	public int countOfFilesUploaded() {
		// TODO Auto-generated method stub
		int fileCount = (int) fileRepo.count();
		return fileCount;
	}

	@Override
	public List<File> getAllFiles() {
		// TODO Auto-generated method stub
		List<File> files = (List<File>) fileRepo.findAll();
		return files;
	}

}
