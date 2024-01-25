package com.effiya.Maker_Checker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckerDashDTO {
	private int totalUploadedFiles;
	private int totalUploadedRecords;
	private int totalApprovedRecords;
	private int totalRejectedRecords;
	private int totalPendingRecords;
	private int totalUniqueRecords;
	private int totalMakerCount;
	
	
}


