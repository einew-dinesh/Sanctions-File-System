package com.effiya.Maker_Checker.service;

import java.io.InputStream;
import java.util.List;

import com.effiya.Maker_Checker.model.File;
import com.effiya.Maker_Checker.model.User;

public interface FileService {

	public void save(File file);

	public File getFileById(Integer fileId);

	public List<File> getAllFilesOfUser(User user);

	public int countOfFilesUploadedByUser(User user);

	public int countOfRecordsUploadedByUser(User user);

	public int countOfRecordsByStatusOfUser(User user, String status);

	public int countOfUniqueRecordsUploadedByUser(User user);

	public InputStream load(File file);

	public int countOfFilesUploaded();

	public List<File> getAllFiles();


//	public int getTotalFilesUploadedByUser(User user);
//	public File getFileByListId(Integer listId);
//	public int getTotalRecordsUploadedByUser(User user);
}
