package com.effiya.Maker_Checker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakerTableDTO {
	
	private String makerName;
	private int totalUploadedRecords;
	private int totalApprovedRecords;
	private int totalPendingRecords;

}
