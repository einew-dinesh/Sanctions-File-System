package com.effiya.Maker_Checker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effiya.Maker_Checker.dto.FileRecordDTO;
import com.effiya.Maker_Checker.model.File;
import com.effiya.Maker_Checker.model.FileRecord;
import com.effiya.Maker_Checker.service.FileService;
import com.effiya.Maker_Checker.service.FileRecordService;

@RestController
@CrossOrigin
@RequestMapping("/file-record")
public class FileRecordController {
	@Autowired
	FileService fileService;
	@Autowired
	FileRecordService fileRecordService;
	
	@GetMapping("/all-records")
	public List<FileRecord> getAllRecords(){
		List<FileRecord> fileRecords = fileRecordService.getAllRecords();
		
		return fileRecords;
	}	
	
	@GetMapping("/{file_id}")
	public List<FileRecord> getAllRecordsOfFile(@PathVariable(name="file_id") Integer fileId){
		File file = fileService.getFileById(fileId);
		List<FileRecord> fileRecords = fileRecordService.getAllRecordsOfFile(file);
		return fileRecords;
	}
	
	@GetMapping("/update-status/{file_record_id}/{new_status}")
	public FileRecord updateStatusOfFileRecord(@PathVariable(name="file_record_id") Integer fileRecordId,@PathVariable(name="new_status") String newStatus) {
		FileRecord updatedFileRecord = fileRecordService.updateStatus(fileRecordId, newStatus);
		return updatedFileRecord;
	}
	
	@PostMapping("/update-record/{file_record_id}")
	public FileRecord updateFileRecord(@PathVariable(name="file_record_id") Integer fileRecordId, @RequestBody FileRecordDTO fileRecordDTO) {
		FileRecord updatedFileRecord = fileRecordService.update(fileRecordId,fileRecordDTO);
		return updatedFileRecord;
	}
	
	@GetMapping("/delete-record/{file_record_id}")
	public String deleteFileRecord(@PathVariable(name="file_record_id") Integer fileRecordId) {
		String message = fileRecordService.deleteRecord(fileRecordId);
		return message;
	}
	
	
	
	
	
//	@GetMapping("/records")
//	public List<FileRecord> getRecordsOfFile(@PathVariable(name="list_id") Integer listId){
//		File file = fileService.getFileByListId(listId);
//		List<FileRecord> list = recordService.getRecordsOfFile(file);
//		return list;
//	}
}
