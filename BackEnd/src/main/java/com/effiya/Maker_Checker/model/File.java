package com.effiya.Maker_Checker.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class File {

	@Id
	@Column(name = "file_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int fileId;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_type")
	private String fileType;

	@Column(name = "comment")
	private String comment;

	@Column(name = "uploaded_on")
	private Timestamp uploadedOn;

	@Column(name = "updated_on")
	private Timestamp updatedOn;

	@Column(name = "file_status")
	private String fileStatus;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@PrePersist
	protected void onCreate() {
		uploadedOn = new Timestamp(System.currentTimeMillis());
	}

	@PreUpdate
	protected void onUpdate() {
		updatedOn = new Timestamp(System.currentTimeMillis());
	}

	public File(String fileName, String fileType, String comment, User user) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.comment = comment;
		this.user = user;
	}

}
