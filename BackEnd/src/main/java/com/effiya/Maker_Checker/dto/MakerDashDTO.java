package com.effiya.Maker_Checker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MakerDashDTO {
	private int totalUploadedFiles;
	private int totalUploadedRecords;
	private int totalApprovedRecords;
	private int totalRejectedRecords;
	private int totalPendingRecords;
	private int totalUniqueRecords;

}
