package com.effiya.Maker_Checker.repository;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;

import com.effiya.Maker_Checker.model.File;
import com.effiya.Maker_Checker.model.FileRecord;


public class FileHelper {
	
	public static String TYPE = "text/csv";
	  static String[] HEADERs = { "Name", "Pan","Email"};

	  public static boolean hasCSVFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }
	    	return true;
	  }
	  
	  
	  

	  public static List<FileRecord> csvToFileRecords(InputStream is, File file) {
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	      List<FileRecord> fileRecords = new ArrayList<FileRecord>();
	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
	      
	      System.out.println(csvParser.getCurrentLineNumber());

	      ////////////////////////////////////////////////////
	      for (CSVRecord csvRecord : csvRecords) {
	    	  
	        FileRecord fileRecord = new FileRecord(
	              csvRecord.get(0),
	              csvRecord.get(1),
	              csvRecord.get(2),
	              file
	            );
	        
	        fileRecords.add(fileRecord);
	      }

	      return fileRecords;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	  }
	  
	  
	  
	  public static ByteArrayInputStream fileRecordToCSV(List<FileRecord> fileRecords) {
		    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
		        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
		      for (FileRecord fileRecord : fileRecords) {
		        List<String> data = Arrays.asList(
		              fileRecord.getName(),
		              fileRecord.getPan(),
		              fileRecord.getEmail()
		            );

		        csvPrinter.printRecord(data);
		      }

		      csvPrinter.flush();
		      return new ByteArrayInputStream(out.toByteArray());
		    } catch (IOException e) {
		      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		    }
		  }

}
