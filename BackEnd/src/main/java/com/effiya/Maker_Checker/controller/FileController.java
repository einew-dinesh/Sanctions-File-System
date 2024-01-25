package com.effiya.Maker_Checker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.effiya.Maker_Checker.dto.CheckerDashDTO;
import com.effiya.Maker_Checker.dto.FileDTO;
import com.effiya.Maker_Checker.dto.MakerDashDTO;
import com.effiya.Maker_Checker.dto.MakerTableDTO;
import com.effiya.Maker_Checker.model.File;
import com.effiya.Maker_Checker.model.User;
import com.effiya.Maker_Checker.payload.ResponseMessage;
import com.effiya.Maker_Checker.repository.FileHelper;
import com.effiya.Maker_Checker.service.FileRecordService;
import com.effiya.Maker_Checker.service.FileService;
import com.effiya.Maker_Checker.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {
	@Autowired
	FileService fileService;
	@Autowired
	FileRecordService fileRecordService;
	@Autowired
	UserService userService;
	
	@CrossOrigin
	@PostMapping("/upload-file/{user_id}")
	public ResponseEntity<ResponseMessage> uploadFile(@PathVariable(name = "user_id") Integer userId,
			@ModelAttribute FileDTO fileDTO,@RequestParam("csv") MultipartFile csv) {

		User user = userService.getUser(userId);
		ResponseMessage responseMessage = new ResponseMessage();

		File file = new File(fileDTO.getFileName(), fileDTO.getFileType(), fileDTO.getComment(), user);

		if (FileHelper.hasCSVFormat(csv)) {
			try {

				fileService.save(file);
				fileRecordService.save(csv, file);
				responseMessage.setMessage("Uploaded the file successfully: " + csv.getOriginalFilename());
				return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
			} catch (Exception e) {
				responseMessage.setMessage("Could not upload the file: " + csv.getOriginalFilename() + "!");
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseMessage);
			}
		}
		responseMessage.setMessage("Please upload a csv file!");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
	}

	
	@GetMapping("/{file_id}")
	public ResponseEntity<File> getFileById(@PathVariable(name = "file_id") Integer fileId) {
		File file = fileService.getFileById(fileId);
		return ResponseEntity.ok(file);
		
	}
	
	@GetMapping("/files-of-user")
	public List<File> getAllFiles(){

		List<File> files = fileService.getAllFiles();
		return files;
	}
	
	
	@GetMapping("/files-of-user/{user_id}")
	public List<File> getAllFilesOfUser(@PathVariable(name = "user_id") Integer userId) {

		User user = userService.getUser(userId);
		List<File> list = fileService.getAllFilesOfUser(user);
		return list;
	}
	

	@GetMapping("/{user_id}/maker-dashboard")
	public ResponseEntity<MakerDashDTO> getMakerDashboardForUser(@PathVariable(name = "user_id") Integer userId) {

		User user = userService.getUser(userId);
		MakerDashDTO makerDashDTO = new MakerDashDTO();

		makerDashDTO.setTotalUploadedFiles(fileService.countOfFilesUploadedByUser(user));
		makerDashDTO.setTotalUploadedRecords(fileService.countOfRecordsUploadedByUser(user));
		makerDashDTO.setTotalApprovedRecords(fileService.countOfRecordsByStatusOfUser(user,"Approved"));
		makerDashDTO.setTotalRejectedRecords(fileService.countOfRecordsByStatusOfUser(user, "Rejected"));
		makerDashDTO.setTotalPendingRecords(fileService.countOfRecordsByStatusOfUser(user, "Pending"));
		makerDashDTO.setTotalUniqueRecords(fileService.countOfUniqueRecordsUploadedByUser(user));
		

		return ResponseEntity.ok(makerDashDTO);
	}
	
	
	@GetMapping("/download/{file_id}")
	public ResponseEntity<Resource> getFile(@PathVariable(name="file_id") Integer fileId){
		File file = fileService.getFileById(fileId);
		String filename = file.getFileName()+".csv";
		InputStreamResource csvFile = new InputStreamResource(fileService.load(file));
		
		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
		        .contentType(MediaType.parseMediaType("application/csv"))
		        .body(csvFile);
		
	}
	
	
	
	@GetMapping("/{user_id}/checker-dashboard")
	public ResponseEntity<CheckerDashDTO> getCheckerDashboardForUser(@PathVariable(name = "user_id") Integer userId){
		CheckerDashDTO checkerDashDTO = new CheckerDashDTO();
		
		checkerDashDTO.setTotalUploadedFiles(fileService.countOfFilesUploaded());
		checkerDashDTO.setTotalUploadedRecords(fileRecordService.countOfRecordsUploaded());
		checkerDashDTO.setTotalApprovedRecords(fileRecordService.countOfRecordsByStatus("approved"));
		checkerDashDTO.setTotalRejectedRecords(fileRecordService.countOfRecordsByStatus("rejected"));
		checkerDashDTO.setTotalPendingRecords(fileRecordService.countOfRecordsByStatus("pending"));
		checkerDashDTO.setTotalUniqueRecords(fileRecordService.countOfUniqueRecordsUploaded());
		checkerDashDTO.setTotalMakerCount(userService.countOfUserByRole("maker"));
		
		return ResponseEntity.ok(checkerDashDTO);
		
	}
	
	@GetMapping("/{user_id}/maker-table") 
	public ResponseEntity<List<MakerTableDTO>> getMakerTableForUser(@PathVariable(name = "user_id") Integer userId){
		
		MakerTableDTO makerTableDTO = new MakerTableDTO();
		
		List<MakerTableDTO> makerTableDTOs = new ArrayList<MakerTableDTO>();
		
		List<User> makers = userService.getUsersByUserRole("maker");
		
		for(User maker:makers) {
			makerTableDTO.setMakerName(maker.getUserName());
			makerTableDTO.setTotalUploadedRecords(fileService.countOfRecordsUploadedByUser(maker));
			makerTableDTO.setTotalApprovedRecords(fileService.countOfRecordsByStatusOfUser(maker,"Approved"));
			makerTableDTO.setTotalPendingRecords(fileService.countOfRecordsByStatusOfUser(maker, "Pending"));
			makerTableDTOs.add(makerTableDTO);
		}
		
		return ResponseEntity.ok(makerTableDTOs);
	}
	

	

}
